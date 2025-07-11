package vn.hoidanit.laptopshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import vn.hoidanit.laptopshop.service.validator.StrongPassword;

@Entity
@Table(name = "users")
public class User {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long   id;
  @NotNull
  @Email(message = "Email không hợp lệ", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  @NotEmpty(message = "Xin vui lòng điền email")
  private String email;
  @NotNull
  @Size(min=3,message = "Password phải có tối thiểu 3 ký tự ")
  private String password;
  @NotNull
  @Size(min = 3,message = "FullName cần từ 3 ký tự trở lên")
  private String fullName;
  private String address;
  private String phone;
  private String avatar;

  //roleId
  //User many to one role
  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Order> order;

  @OneToOne(mappedBy = "user")
  private Cart cart;

  @Override
public String toString() {
    return "User [id=" + id + ", email=" + email + ", password=" + password + ", fullName=" + fullName + ", address="
            + address + ", phone=" + phone + ", avatar=" + avatar + "]";
}

  public long getId() {
    return id;
  }
  public String getEmail() {
    return email;
  }
  public String getPassword() {
    return password;
  }
  public String getFullName() {
    return fullName;
  }
  public String getAddress() {
    return address;
  }
  public String getPhone() {
    return phone;
  }
  public void setId(long id) {
    this.id = id;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getAvatar() {
    return avatar;
  }
  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
  public Role getRole() {
    return role;
  }
  public void setRole(Role role) {
    this.role = role;
  }
  public List<Order> getOrder() {
    return order;
  }
  public void setOrder(List<Order> order) {
    this.order = order;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  

    
}
