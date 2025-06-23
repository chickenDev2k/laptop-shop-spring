package vn.hoidanit.laptopshop.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double totalPrice;

    private String reciiverName;

    private String receiverAddress;
    private String receiverPhone;
    private String status;

    // userID
    // order many -> one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy="order",cascade = CascadeType.ALL, // Quan trọng: Khi Order bị xóa, các OrderDetail liên quan cũng bị xóa
               orphanRemoval = true ) // Quan trọng: Nếu một OrderDetail bị ngắt kết nối khỏi Order, nó sẽ bị xóa)
    private List<OrderDetail> OrderDetail;


    


    public Order() {
    }

    public Order(long id, double totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
    }
    
    public Order(long id, double totalPrice, String reciiverName, String receiverAddress, String receiverPhone,
            String status, User user, List<vn.hoidanit.laptopshop.domain.OrderDetail> orderDetail) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.reciiverName = reciiverName;
        this.receiverAddress = receiverAddress;
        this.receiverPhone = receiverPhone;
        this.status = status;
        this.user = user;
        OrderDetail = orderDetail;
    }

    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    @Override
    public String toString() {
        return "Order [id=" + id + ", totalPrice=" + totalPrice + ", reciiverName=" + reciiverName
                + ", receiverAddress=" + receiverAddress + ", receiverPhone=" + receiverPhone + ", status=" + status
                + ", user=" + user + ", OrderDetail=" + OrderDetail + "]";
    }

    public String getReciiverName() {
        return reciiverName;
    }

    public void setReciiverName(String reciiverName) {
        this.reciiverName = reciiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetail> getOrderDetail() {
        return OrderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        OrderDetail = orderDetail;
    }

    
    

}
