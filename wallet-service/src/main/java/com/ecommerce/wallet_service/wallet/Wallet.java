package com.ecommerce.wallet_service.wallet;

import java.math.BigDecimal;

import com.ecommerce.wallet_service.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Wallet {
    @Id
    @SequenceGenerator(
        name ="wallet_sequence",
        sequenceName = "wallet_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;
    
    private BigDecimal balance;
    

    public Wallet(){
    }

    public Wallet(Long id, User user, BigDecimal balance){
        this.id = id;
        this.user = user;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
