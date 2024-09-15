package com.ecommerce.inventory_microservice.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Inventory {
    

    @Id
    @SequenceGenerator(
        name ="inventory_sequence",
        sequenceName = "inventory_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_sequence")
    private Long id;
    
    private Long productId;
    
    private int quantity;


    public Inventory(){
    }

    

    public Inventory(Long id, Long productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }



    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public void setProductId(Long productId) {
        this.productId = productId;
    }



    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
