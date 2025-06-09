package vn.hoidanit.laptopshop.controller.admin;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;



@Controller
public class UserController{
  private UserService userService ;
  private final UploadService uploadService;
  private final PasswordEncoder passwordEncoder;

    

    public UserController(UserService userService,UploadService uploadService,PasswordEncoder passwordEncoder) {
       this.uploadService = uploadService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
       
    }


    // @RequestMapping("") //default is method get
    // public String getHomePage(Model model){
    //     // List<User> arrUser = this.userService.getALlUsersByEmail("taquanga6dp@gmail.com");
    //     // System.out.println(arrUser);
    
    //     return "hello";
    // }
    @GetMapping("/admin/user")
    public String getUserListPage(Model model){
        List<User> users = this.userService.getALlUsers();
        int num0 = 0;
        model.addAttribute("num0", num0);
        model.addAttribute("users", users);
        //System.out.println(users);
        return "admin/user/show";
    }
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model,@PathVariable("id") long id){
        System.out.println("check path id= "+ id);
        model.addAttribute("id", id);
        User userDetail = this.userService.getUserById(id);
        model.addAttribute("userDetail", userDetail);
        if(userDetail.getAvatar() != ""){
                model.addAttribute("urlImage", userDetail.getAvatar());
            }
        return "admin/user/detail";
       
    }
    
    
    // create user
    @GetMapping("/admin/user/create") //get
    public String getCreateUserPage(Model model){
    
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }
    
    @PostMapping("/admin/user/create")
    public String createUserPage(
        Model model,@ModelAttribute("newUser") @Valid User user,BindingResult newUserBindingResult ,  

        @RequestParam("avatarFile") MultipartFile file)
        
    {
        //validate

        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (error.getField() + " - " + error.getDefaultMessage());
        }
        //validate no redirect because data will lost
        if(newUserBindingResult.hasErrors()){
            return "admin/user/create";
        }
        //
        String avatar = this.uploadService.handleSaveUploadFile(file,"avatar");
        String hashPassword = this.passwordEncoder.encode(user.getPassword());

        user.setAvatar(avatar);
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }
    // update user
    @GetMapping("/admin/user/update/{id}")
    public String getUserUpdatePage(Model model,@PathVariable("id") long id){
        System.out.println("check path id= "+ id);
        model.addAttribute("id", id);
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("currentUser", currentUser);
        return "admin/user/update";
       
    }
    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model,@ModelAttribute("currentUser") User updateUser,@RequestParam("avatarFile") MultipartFile file){
         User checkUserExit = this.userService.getUserById(updateUser.getId());
         if(checkUserExit != null){

           

            if(!file.isEmpty()){
                 String avatar = this.uploadService.handleSaveUploadFile(file,"avatar");
            
                checkUserExit.setAvatar(avatar);
            }
            checkUserExit.setAddress(updateUser.getAddress());
            checkUserExit.setPhone(updateUser.getPhone());
            checkUserExit.setFullName(updateUser.getFullName());
            checkUserExit.setRole(this.userService.getRoleByName(updateUser.getRole().getName()));            
            userService.handleSaveUser(checkUserExit);
         }


        return  "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String confirmDeleteUser(Model model, @PathVariable("id") long id){
        final User user = new User();
        user.setId(id);
        model.addAttribute("willDeleteUser", user);

        return "admin/user/delete";
    }
    @PostMapping("/admin/user/delete")
    public String postDeleteUser(@ModelAttribute("willDeleteUser") User user ) {
        User isExitUser = this.userService.getUserById(user.getId());
        if(isExitUser != null){

            System.out.println(this.userService.handleDeleteUser(user.getId()));
            return "redirect:/admin/user";
        }else{
            return "admin/user/error404";
        }
        
        
    }
    


}

