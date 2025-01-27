package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.Product;
import com.kht.ecommerce.ecommerce_application.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts(){

        return productMapper.getAllProducts();
    }

    @Override
    public void addProduct(Product product) {

        productMapper.addProduct(product);
    }


    @Override
    public Product productDetail(int productId) {

        return productMapper.productDetail(productId);
    }

    @Override
    public void updateProduct(Product product) {

        productMapper.updateProduct(product);
    }

}
