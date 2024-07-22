package com.richardvinz.Reactive_Demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PRODUCT_DETAILS")
public class Product {
    @Id
    private String id;
    private String name;
    private int quantity;
    private double price;
}