package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.hoidanit.laptopshop.service.UserService;



@Controller
public class DashboardController {

    private final UserService userService;

    public DashboardController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getDashboard(Model model){
        model.addAttribute("countUser", userService.countUser());
        model.addAttribute("countProduct", userService.countProduct());
        model.addAttribute("countOrder", userService.countOrder());
        
        return "admin/dashboard/show";
    }
}
