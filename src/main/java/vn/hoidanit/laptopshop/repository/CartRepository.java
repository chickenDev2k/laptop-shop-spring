package vn.hoidanit.laptopshop.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.User;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    public Cart findByUser(User user);
    public Cart findById(long id);

    
}
