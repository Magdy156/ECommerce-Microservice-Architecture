package com.ecommerce.shop_microservice.cart_management;

import com.ecommerce.shop_microservice.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class CartItem {
    @Id
    @SequenceGenerator(
        name ="cartItem_sequence",
        sequenceName = "cartItem_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartItem_sequence")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    private int quantity;


    public CartItem(){        
    }


    public CartItem(Long id, Product product, Cart cart, int quantity) {
        Id = id;
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
    }


    public Long getId() {
        return Id;
    }


    public Product getProduct() {
        return product;
    }


    public Cart getCart() {
        return cart;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setId(Long id) {
        Id = id;
    }


    public void setProduct(Product product) {
        this.product = product;
    }


    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
