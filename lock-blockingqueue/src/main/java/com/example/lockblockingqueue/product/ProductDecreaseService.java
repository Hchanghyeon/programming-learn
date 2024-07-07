package com.example.lockblockingqueue.product;

import java.util.concurrent.BlockingQueue;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lockblockingqueue.product.Product;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ProductDecreaseService implements Runnable {

    private final BlockingQueue<Product> productsQueue;
    private final ProductDecreaseTransactionalService productDecreaseTransactionalService;

    @Override
    public void run() {
        while(true) {
            try {
                Product product = productsQueue.take();
                productDecreaseTransactionalService.decreaseProductQuantity(product);
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted");
            }
        }
    }
}
