package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoidanit.laptopshop.domain.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    
}
