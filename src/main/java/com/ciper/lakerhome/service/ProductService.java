package com.ciper.lakerhome.service;

import com.ciper.lakerhome.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductMapper productMapper){
        this.productMapper = productMapper;
    }

}
