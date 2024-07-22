package com.richardvinz.Reactive_Demo.util;

import com.richardvinz.Reactive_Demo.dto.ProductDTO;
import com.richardvinz.Reactive_Demo.entity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils  {

    public static ProductDTO entityToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product,productDTO);
        return productDTO;
    }

    public static Product ProductDTOToEntity(ProductDTO productDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO,product);
        return product;
    }
}
