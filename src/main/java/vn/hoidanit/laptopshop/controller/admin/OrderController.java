package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.service.OrderService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class OrderController {

    private final OrderService orderService;

    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    


    public OrderController(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderService = orderService;
    }




    @GetMapping("/admin/order")
    public String getDashboard(Model model,@RequestParam("page") Optional<String> optionalPage){

        int page = 1;
        try {
            page = Integer.parseInt(optionalPage.get());
        } catch (Exception e) {
            // TODO: handle exception
            page =1;
        }
        Pageable pageable = PageRequest.of(page-1, 10);
        Page<Order> ordersPage = this.orderRepository.findAll(pageable);
        List<Order> orders = ordersPage.getContent();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ordersPage.getTotalPages());
        model.addAttribute("orders",orders);
        return "admin/order/show";
    }

    // view order detail

    @GetMapping("/admin/order/{id}")
    public String getOrderDetail(Model model,@PathVariable long id) {

        Order order =this.orderService.getOrderById(id);
       List< OrderDetail> orderDetails = order.getOrderDetail();
        
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        return "admin/order/detail";
    }

    //view update page
    @GetMapping("/admin/order/update/{id}")
    public String getUpdatePage(Model model,@PathVariable long id) {
        Order updateOrder = this.orderService.getOrderById(id);
        User user = updateOrder.getUser();

        model.addAttribute("user", user);
        model.addAttribute("orderUpdate", updateOrder);
        return "admin/order/update";
    }

    // update logic

    @PostMapping("/admin/order/update")
    public String updateOrderStatus(@ModelAttribute("orderUpdate") Order order) {
        
        Order exitOrder = this.orderRepository.findById(order.getId());
        exitOrder.setStatus(order.getStatus());
        this.orderRepository.save(exitOrder);
        
        return "redirect:/admin/order";
    }


    //get delete page

    @GetMapping("/admin/order/delete/{id}")
    public String getDeletePage(@PathVariable long id,Model model){
        Order deleteOrder = this.orderRepository.findById(id);
        
        model.addAttribute("deleteOrder",deleteOrder);
        return "admin/order/delete";
    }
    
    @PostMapping("/admin/order/delete")
    public String deleteOrder(@ModelAttribute("toDeleteOrder") Order order ){
        
        Order existOrder = this.orderRepository.findById(order.getId());
        if(existOrder != null){
            // List<OrderDetail> orderDetails =existOrder.getOrderDetail();
            // for(OrderDetail od : orderDetails){
            //     this.orderDetailRepository.delete(od);
            // }
            this.orderRepository.delete(existOrder);
        }
    
        return "redirect:/admin/order";
    }
    



}
