package com.lock.lockpessimisticjpa.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lock.lockpessimisticjpa.product.Product;
import com.lock.lockpessimisticjpa.product.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void orderItem(final OrderRequest orderRequest) {
        Long itemId = orderRequest.itemId();

        Product product = productRepository.findByIdWithPessimisticLock(itemId)
                .orElseThrow(() -> new EntityNotFoundException());
        product.decreaseQuantity();

        Order order = new Order(product);
        orderRepository.save(order);
    }
}
