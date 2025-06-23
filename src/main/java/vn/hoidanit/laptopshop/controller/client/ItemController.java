package vn.hoidanit.laptopshop.controller.client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.CartUpdateDTO;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.service.CartService;
import vn.hoidanit.laptopshop.service.OrderService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ItemController {

    private final OrderService orderService;
    private final ProductService productService;
    private final CartService cartService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    

    //constructor method to di/dc
    public ItemController(
            ProductService productService,
            CartService cartService,
            UserService userService,
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository, OrderService orderService)

     {
        this.productService = productService;
        this.cartService = cartService;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderService = orderService;
    }

    @GetMapping("product/{id}")
    public String getProductDetail(@PathVariable("id") long id,Model model){
        Product pr = this.productService.getProductById(id);
        model.addAttribute("product", pr);
        model.addAttribute("id", id);
        return "client/product/detail";
    }    

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id,HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        long productId = id;
        String email =(String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email,productId,session);
      return "redirect:/";
        
    }

    @GetMapping("/cart")
    public String getCartPage(Model model,HttpSession session) {
        long id = (long) session.getAttribute("id");
        User user = this.userService.getUserById(id);
        Cart cart = this.cartService.getCartByUser(user) != null ?this.cartService.getCartByUser(user):new Cart();
        List<CartDetail> allProductInCart = cart.getCartDetail() != null? cart.getCartDetail():new ArrayList<>();

        long totalPrice = 0;
        for(CartDetail c : allProductInCart){
            totalPrice += (c.getPrice() * c.getQuantity());
        
        }
        if(allProductInCart.size() == 0){
            session.setAttribute("sum", 0);
        }
        model.addAttribute("allProductInCart", allProductInCart);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cart);
        return "client/cart/cart";
    }

    @PostMapping("/cart/delete/{id}")
    public String deleteCartItem(@PathVariable long id,HttpSession session ){
        CartDetail cartDetail = this.cartService.getCartDetailById(id);
        Cart cart = cartDetail.getCart();
        
        if(cart.getCartDetail().size() < 1 ){
            session.setAttribute("sum", 0);
        }
        cart.getCartDetail().remove(cartDetail);
        this.cartService.saveCart(cart);

        int sum = cart.getSum();
        sum -= 1;
        sum = sum >0 ?sum:0;
        cart.setSum(sum);
        session.setAttribute("sum", sum);
        return "redirect:/cart";
    }


    // check out 

    @GetMapping("/checkout")
    public String getCheckOutPage(Model model,HttpServletRequest request,@ModelAttribute("newCart") Cart newCart) {
        User currentUser = new User();
        HttpSession session = request.getSession(false);
        long id = (long)session.getAttribute("id");
        currentUser.setId(id);
        Cart cart = this.cartService.getCartByUser(currentUser);
        List<CartDetail> cartDetails = cart==null? new ArrayList<>():cart.getCartDetail();
        double totalPrice = 0;
        for(CartDetail cd:cartDetails){
            totalPrice += cd.getPrice() * cd.getQuantity();
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);

        return "client/cart/checkout";
    }

    // update quantity in cart now not work 

    @PostMapping("/cart/update")
    @ResponseBody // Trả về dữ liệu JSON/XML trực tiếp
    public ResponseEntity<?> updateCartItemQuantity(@RequestBody CartUpdateDTO updateDto, HttpSession session) {
        try {
            // Lấy CartDetail từ database
            CartDetail cartDetail = this.cartService.getCartDetailById(updateDto.getId());

            if (cartDetail == null) {
                return ResponseEntity.badRequest().body(new ApiResponse(false, "Cart item not found."));
            }

            // Cập nhật số lượng
            cartDetail.setQuantity(updateDto.getQuantity());
            this.cartService.saveCartDetail(cartDetail); // Bạn cần phương thức này trong CartService

            // Cập nhật tổng số lượng trong session (nếu cần)
            // Cách tốt nhất là tính lại tổng từ Cart sau khi cập nhật CartDetail
            User user = (User) session.getAttribute("user"); // Hoặc lấy user bằng ID từ session
            if (user == null) {
                long userId = (long) session.getAttribute("id");
                user = userService.getUserById(userId);
            }
            Cart userCart = this.cartService.getCartByUser(user);
            if (userCart != null) {
                long newSum = userCart.getCartDetail().stream()
                                    .mapToLong(CartDetail::getQuantity)
                                    .sum();
                userCart.setSum((int)newSum); // Cập nhật sum trong Cart entity nếu bạn có
                this.cartService.saveCart(userCart); // Lưu lại Cart nếu đã cập nhật sum trong Cart entity
                session.setAttribute("sum", newSum); // Cập nhật session attribute
            }


            return ResponseEntity.ok(new ApiResponse(true, "Cart item quantity updated successfully."));

        } catch (Exception e) {
            // Log lỗi
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ApiResponse(false, "Error updating cart item: " + e.getMessage()));
        }
}

        // Lớp ApiResponse đơn giản để trả về JSON
        public class ApiResponse {
            private boolean success;
            private String message;

            public ApiResponse(boolean success, String message) {
                this.success = success;
                this.message = message;
            }

            public boolean isSuccess() { return success; }
            public String getMessage() { return message; }
            // Có thể thêm setters nếu cần deserialization
        }


    // update quantity in cart now not work 
  

    @PostMapping("/confirm-checkout")
    public String handlePlaceOrder(
        HttpServletRequest request,
        @RequestParam("receiverName") String receiverName,
        @RequestParam("receiverAddress") String receiverAddress,
        @RequestParam("receiverPhone") String receiverPhone

    ) {
        HttpSession session = request.getSession(false);
        User currentUser = new User();
        long id = (long)session.getAttribute("id");
        currentUser.setId(id);
        this.cartService.handlePlaceOrder(currentUser, session,receiverName,receiverAddress,receiverPhone);
        return "redirect:/thanks";
    }







    @GetMapping("/thanks")
    public String getThankYouPage(Model model) {
        return "client/cart/thanks";
    }


    // history controller 

    @GetMapping("/client/history")
    public String getHistoryPage(Model model,HttpSession session) {
        long id = (long)session.getAttribute("id");
        List<Order> orders =this.orderService.getOrderByUser(this.userService.getUserById(id));
        List<OrderDetail> orderDetails = new ArrayList() ;
        for(Order od: orders){
            orderDetails.addAll(od.getOrderDetail());
        }
        model.addAttribute("orderDetails", orderDetails);
        return "client/cart/history";
    }
    





    
    
    

    
    
    

}
