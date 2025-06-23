package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;

@Service
public class CartService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private CartRepository cartRepository;
    private CartDetailRepository cartDetailRepository;
    
    public CartService(CartRepository cartRepository,CartDetailRepository cartDetailRepository, OrderRepository orderRepository,OrderDetailRepository orderDetailRepository) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Cart getCartByUser(User user ){
        
        return this.cartRepository.findByUser(user);

    }
    public void saveCart(Cart cart){
        this.cartRepository.save(cart);
    }
    
    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails){
        for(CartDetail cartDetail : cartDetails){
            Optional<CartDetail> cdOptional = Optional.ofNullable(this.cartDetailRepository.findById(cartDetail.getId()));
            if(cdOptional.isPresent()){
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }


    // cart detail service
    public List<CartDetail> handleToGetCartDetails(Cart cart){
        List<CartDetail> cartDetails = this.cartDetailRepository.findAllByCart(cart);
        return cartDetails;
    }
    public CartDetail getCartDetailById(long id ){
        return this.cartDetailRepository.findById(id) ;
    }
    public void saveCartDetail(CartDetail cartDetail) {
       this.cartDetailRepository.save(cartDetail);
    }

    // order service

    public void handlePlaceOrder(User user, HttpSession session,String receiverName,
    String receiverAddress,String receiverPhone){

        // step 1 get cart by user
        Cart cart = this.cartRepository.findByUser(user);
        if(cart != null){
            List<CartDetail> cartDetails = cart.getCartDetail();
            if(cartDetails != null){


                // create order
                Order order = new Order();
                order.setUser(user);
                order.setReciiverName(receiverName);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverPhone(receiverPhone);
                order.setStatus("PENDING");
                double sum = 0;
                for(CartDetail cd:cartDetails){
                    sum += cd.getPrice();
                }
                order.setTotalPrice(sum);
                order = this.orderRepository.save(order);

                // create orderDetail
                for(CartDetail cd : cartDetails){
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());
                    orderDetail.setPrice(cd.getPrice());
                    orderDetail.setQuantity(cd.getQuantity());
                    this.orderDetailRepository.save(orderDetail);
                }
            }

            // step 2 delete cart_detail and cart
            for(CartDetail cd : cartDetails){
                this.cartDetailRepository.deleteById(cd.getId());
            }
            this.cartRepository.deleteById(cart.getId());

            // step 3 update session

            session.setAttribute("sum", 0);
        }

        

    }

    
}
