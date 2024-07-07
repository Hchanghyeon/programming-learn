package com.lock.lockpessimisticjpa.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void addProduct(ProductRequest productRequest){
        Product product = new Product(productRequest.name(), productRequest.quantity());

        productRepository.save(product);
    }
}
