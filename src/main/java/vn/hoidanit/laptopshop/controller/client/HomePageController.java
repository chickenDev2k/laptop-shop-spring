package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class HomePageController {
    private final ProductService productService;
    private final UserService userService;
     private final PasswordEncoder passwordEncoder;
    

    public HomePageController(ProductService productService,UserService userService,PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/")
    public String getHomePage(Model model,HttpServletRequest request){
        Pageable pageable = PageRequest.of(0, 10);
     
       Page<Product> productsPage = this.productService.getAllProduct(pageable);
       List<Product> products = productsPage.getContent();
        
        
        model.addAttribute("products", products);
        HttpSession session = request.getSession(false);
        
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }
    
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerUser") @Valid RegisterDTO registerDTO, BindingResult bindingResult) 
    {
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors){
            System.out.println(">>>" + error.getField() + "_" + error.getDefaultMessage());
        }
        
        if(bindingResult.hasErrors()){
            return "client/auth/register";
        }

       User user = (User) this.userService.RegisterDTOtoUser(registerDTO);
       String hashPassword = this.passwordEncoder.encode(user.getPassword());
       user.setPassword(hashPassword);
       user.setRole(this.userService.getRoleByName("USER"));
       this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        User loginUser = new User();
        model.addAttribute("loginUser", loginUser);
        return "client/auth/login";
    }
    @GetMapping("/access-deny")
    public String getDenyPage(Model model) {
       
        return "client/auth/deny";
    }
    
    
}
