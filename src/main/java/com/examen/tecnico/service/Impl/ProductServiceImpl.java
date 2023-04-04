package com.examen.tecnico.service.Impl;

import com.examen.tecnico.model.ProductEntity;
import com.examen.tecnico.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl {
    @Value("${service.product.url}")
    private String productUrl;
    private final ProductRepository productRepository;
    public final RestTemplate restTemplate;
    @Autowired
    public ProductServiceImpl(RestTemplate restTemplate, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.productRepository = productRepository;
    }

    public List<ProductEntity> getAllProduct(){
        ProductEntity[] response = restTemplate.getForObject(productUrl + "/products", ProductEntity[].class);
        return Arrays.asList(response);
    }

    public ProductEntity getProductById(Long id){
        ProductEntity product = restTemplate.getForObject(productUrl + "/products/" + id, ProductEntity.class);
        return product;
    }

    //public void saveAllProduct(List<ProductEntity> products){
    //}
}
