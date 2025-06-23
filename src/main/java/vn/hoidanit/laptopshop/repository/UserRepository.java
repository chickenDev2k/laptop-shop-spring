package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import vn.hoidanit.laptopshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Page<User> findAll(Pageable pageable);

    User save(User user);
    List<User> findByEmailAndAddress(String email,String address);
    User findByEmail(String email);
    User findUserById(long id);
    void deleteById(long id);
    boolean existsByEmail(String email);
    

@Transactional
    void deleteUserById(long id);
    

}
