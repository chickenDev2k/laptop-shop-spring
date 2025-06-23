package vn.hoidanit.laptopshop.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(value = 0)
    private int sum;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL, // Cascade tất cả các thao tác (persist, merge, remove)
               orphanRemoval = true )
    List<CartDetail> cartDetail;
    

    
    public int getSum() {
        return sum;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<CartDetail> getCartDetail() {
        return cartDetail;
    }
    public void setCartDetail(List<CartDetail> cartDetail) {
        this.cartDetail = cartDetail;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    
    
}
