package com.example.lockblockingqueue.product;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductDecreaseTransactionalService {

    private final ProductRepository productRepository;

    @Transactional
    public void decreaseProductQuantity(Product product) {
        Product persistentProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        persistentProduct.decreaseQuantity();
    }
}
