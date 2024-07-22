package com.richardvinz.Reactive_Demo.service;

import com.richardvinz.Reactive_Demo.dto.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductDTO>getProducts();
    Mono<ProductDTO> getProductsById(String id);
    Flux<ProductDTO>getProductRange(double min, double max);
    Mono<ProductDTO>saveProduct(Mono<ProductDTO> productDTOMono);
    Mono<ProductDTO> updateProduct(Mono<ProductDTO>productDTOMono,String id);
    Mono<Void>deleteProduct(String id);
}
