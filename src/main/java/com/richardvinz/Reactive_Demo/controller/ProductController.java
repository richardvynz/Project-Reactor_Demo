package com.richardvinz.Reactive_Demo.controller;


import com.richardvinz.Reactive_Demo.dto.ProductDTO;
import com.richardvinz.Reactive_Demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Flux<ProductDTO>>getProducts(){
        return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<ProductDTO>> getProduct(@PathVariable String id){
        System.out.println("in product controller...");
        return new ResponseEntity<>(productService.getProductsById(id), HttpStatus.OK);
    }

    @GetMapping("/product-range")
    public ResponseEntity<Flux <ProductDTO>> getProductBetweenRange(@RequestParam("min") double min,
                                                                   @RequestParam("max") double max){
        Flux<ProductDTO> productRange = productService.getProductRange(min, max);
        return new ResponseEntity<>(productRange, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Mono<ProductDTO>> saveProduct(@RequestBody Mono<ProductDTO> product){
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mono<ProductDTO>> updateProduct(@RequestBody Mono<ProductDTO> product,@PathVariable String id){
        return new ResponseEntity<>(productService.updateProduct(product,id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mono<Void>> deleteProduct(@PathVariable String id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }


}
