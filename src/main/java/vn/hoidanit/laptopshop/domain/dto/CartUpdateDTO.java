package vn.hoidanit.laptopshop.domain.dto;

public class CartUpdateDTO {
    private Long id; // cartDetailId
    private int quantity;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getters and Setters
   
}
