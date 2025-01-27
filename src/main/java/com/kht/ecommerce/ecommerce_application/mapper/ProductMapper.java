package com.kht.ecommerce.ecommerce_application.mapper;

import com.kht.ecommerce.ecommerce_application.dto.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product>  getAllProducts();

    void addProduct(Product product);

    Product productDetail(int productId);

    void updateProduct(Product product);



}
