package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    
   
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
            ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public String handleHello(){
        return "hello from service";
    }

    public List<User> getALlUsers(){
        return this.userRepository.findAll();
    }
    // public List<User> getALlUsersByEmail(String email){
    //     return this.userRepository.findByEmail(email);
    // }
    public User getUserById(long id){
        return this.userRepository.findUserById(id);
    }
    public User handleSaveUser(User user){
        User eric = this.userRepository.save(user);
        System.out.println(eric);
        return eric;
    }
    public boolean handleDeleteUser(long id){
        try {
            this.userRepository.deleteUserById(id);
            System.out.println("run here");
            return true;
        } catch (Exception e) {
          return false;
        }
      
    }

    public Role getRoleByName(String name){
        return this.roleRepository.findByName(name);
    }

    // register DTO to User

    public User RegisterDTOtoUser(RegisterDTO registerDTO){
        User user = new User();
        user.setFullName(registerDTO.getFirstName()+ "" +registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());

        return user;
    }
    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
      
    }


 

    public User getUserByEmail(String email) {
       return this.userRepository.findByEmail(email);
    }

       // get count for dashboard
    public long countUser() {
        return this.userRepository.count(); // countByEmail(email) if want to count by email
    }
    public long countProduct() {
        return this.productRepository.count();
    }

    public long countOrder() {
         return this.orderRepository.count();
    }
    
}
