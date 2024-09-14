package com.ecommerce.shop_microservice.cart_management;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Cart {
    @Id
    @SequenceGenerator(
        name ="cart_sequence",
        sequenceName = "cart_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_sequence")
    private Long id;

    private Long userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items = new ArrayList<>();

    private BigDecimal total; // total price


    public Cart(){
    }


    public Cart(Long id, List<CartItem> items, BigDecimal total) {
        this.id = id;
        this.items = items;
        this.total = total;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setItems(List<CartItem> items) {
        this.items = items;
    }


    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    public Long getId() {
        return id;
    }


    public List<CartItem> getItems() {
        return items;
    }


    public BigDecimal getTotal() {
        return total;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getUserId(){
        return userId;
    }
}
