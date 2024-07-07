package com.lock.lockpessimisticjpa.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long quantity;

    public Product(final String name, final Long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void decreaseQuantity(){
        if(this.quantity <= 0){
            throw new RuntimeException("this product is sold out");
        }

        this.quantity -= 1;
    }
}
