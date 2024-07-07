package com.example.lockblockingqueue.order;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lockblockingqueue.product.Product;
import com.example.lockblockingqueue.product.ProductDecreaseService;
import com.example.lockblockingqueue.product.ProductDecreaseTransactionalService;
import com.example.lockblockingqueue.product.ProductRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final int WAIT_QUEUE_CAPACITY = 1000;

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductDecreaseTransactionalService productDecreaseTransactionalService;
    private final BlockingQueue<Product> productsQueue = new ArrayBlockingQueue<>(WAIT_QUEUE_CAPACITY);

    @PostConstruct
    public void init(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new ProductDecreaseService(productsQueue, productDecreaseTransactionalService));
    }

    @Transactional
    public void orderItem(final OrderRequest orderRequest) throws InterruptedException {
        Long itemId = orderRequest.itemId();

        Product product = productRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException());
        productsQueue.put(product);

        Order order = new Order(product);
        orderRepository.save(order);
    }
}
