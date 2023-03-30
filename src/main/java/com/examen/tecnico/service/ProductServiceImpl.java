package com.examen.tecnico.service;

import com.examen.tecnico.model.ProductEntity;
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
    @Autowired
    public final RestTemplate restTemplate;
    public ProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductEntity> getAllProduct(){
        ProductEntity[] response = restTemplate.getForObject(productUrl + "/products", ProductEntity[].class);
        return Arrays.asList(response);
    }

    public ProductEntity getProductById(Long id){
        return null;
    }

    //public void saveAllProduct(List<ProductEntity> products){
    //}
}
