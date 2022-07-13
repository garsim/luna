package com.luna.controller;

import java.util.*;

import com.luna.datahandler.ProductHandler;
import com.luna.datamodel.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final static String TOKEN ="garsim@gmail.com:1465a739830c8df0";
    private final static String PRODUCTS_URL = "https://lunda.address";
    private final static String HEADER_NAME = "X-API-LunaHeader";

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/allproducts")
    public ResponseEntity<Product[]> getProducts() {

        ResponseEntity<Product[]> respEntity= getResponseEntity();

        LOGGER.info("has body {}", respEntity.hasBody());
        if (!respEntity.hasBody()){
            return ResponseEntity.noContent().build();
        }
        return respEntity;
    }

    @GetMapping("/products")
    @CrossOrigin(origins = "http://localhost:3000/")
    public ResponseEntity<Product[]> getFilteredProducts() {

        ResponseEntity<Product[]> respEntity= getResponseEntity();

        LOGGER.info("has body {}", respEntity.hasBody());
        if (!respEntity.hasBody()){
            return ResponseEntity.noContent().build();
        }
        Product[] products = ProductHandler.removeDuplicatesAndGetSome(respEntity.getBody());

        return ResponseEntity.accepted().body(products);
    }

    /**
     *
     * @param product
     */
    private void logProduct(Product product) {
        LOGGER.info("Received new Product: {} ", product);
    }

    /**
     *
     * @return
     */
    private HttpEntity<String> builEntitydHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 Firefox/26.0");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HEADER_NAME, TOKEN);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return entity;
    }

    /**
     *
     * @return
     */
    private ResponseEntity<Product[]> getResponseEntity(){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = builEntitydHeaders();
        ResponseEntity<Product[]> respEntity= restTemplate.exchange(PRODUCTS_URL, HttpMethod.GET, entity, Product[].class);

        return respEntity;
    }

}
