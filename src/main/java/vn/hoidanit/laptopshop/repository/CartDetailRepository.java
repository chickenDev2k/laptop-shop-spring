package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail,Long> {
    boolean existsByCartAndProduct(Cart cart,Product product);
    CartDetail findByCartAndProduct(Cart cart, Product product);
    CartDetail findById(long id);
    public List<CartDetail> findAllByCart(Cart cart);
    
    
    
}

