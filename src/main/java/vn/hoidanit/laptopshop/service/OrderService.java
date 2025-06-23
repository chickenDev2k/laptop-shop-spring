package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    


    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }


    public List<Order> handleShowOrders(){

        List<Order> orders = this.orderRepository.findAll();
        return orders;

    }

    public Order getOrderById(long id){
        Order order =  this.orderRepository.findById(id) ;
        return order;
    }

    public List<Order> getOrderByUser(User user){
        List<Order> orders = this.orderRepository.findByUser(user);
        return orders; 
    }
}
