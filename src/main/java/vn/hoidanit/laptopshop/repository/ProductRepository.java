package vn.hoidanit.laptopshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.Product;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,JpaSpecificationExecutor<Product> {

    Product findById(long id);
    Product save(Product product);
    Page<Product> findAll(Pageable page);
    Page<Product> findAll(Specification<Product> spec,Pageable page);
  
} 