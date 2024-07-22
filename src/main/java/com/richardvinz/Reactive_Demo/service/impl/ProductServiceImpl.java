package com.richardvinz.Reactive_Demo.service.impl;

import com.richardvinz.Reactive_Demo.dto.ProductDTO;
import com.richardvinz.Reactive_Demo.exception.ProductAlreadyExistException;
import com.richardvinz.Reactive_Demo.exception.ProductNotFoundException;
import com.richardvinz.Reactive_Demo.repository.ProductRepository;
import com.richardvinz.Reactive_Demo.service.ProductService;
import com.richardvinz.Reactive_Demo.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Flux<ProductDTO> getProducts() {
        System.out.println("in product Service...");
        return productRepository.findAll()
                .map(AppUtils::entityToProductDTO);
    }

    @Override
    public Mono<ProductDTO> getProductsById(String  id) {
      return productRepository.findById(id)
              .map(AppUtils::entityToProductDTO)
              .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found with id " + id)));
    }

    @Override
    public Flux<ProductDTO>getProductRange(double min, double max){
        return productRepository
                .findByPriceBetween(Range.closed(min, max));
    }

    @Override
    public Mono<ProductDTO> saveProduct(Mono<ProductDTO> productDTOMono) {
        return productDTOMono.map(AppUtils::ProductDTOToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToProductDTO);
    }


    public Mono<ProductDTO> updateProduct(Mono<ProductDTO>productDTOMono,String id){
        return productRepository.findById(id)
                .flatMap(product -> productDTOMono.map(AppUtils::ProductDTOToEntity))
                .doOnNext(e->e.setId(id))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToProductDTO)
                .onErrorResume(e->{
                    System.err.println("Error Updating Product with ID " + id);
                    return Mono.empty();
                });
    }

    public Mono<Void>deleteProduct(String id){
        return productRepository.deleteById(id);
    }
}
