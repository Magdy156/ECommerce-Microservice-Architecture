package com.ecommerce.wallet_service.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ecommerce.wallet_service.wallet.Wallet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Transaction {
    @Id
    @SequenceGenerator(
        name ="transaction_sequence",
        sequenceName = "transaction_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    private String type;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public Transaction(){
    }

    public Transaction(Long id, Wallet wallet, String type, BigDecimal amount, LocalDateTime timestamp) {
        this.id = id;
        this.wallet = wallet;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    
}
