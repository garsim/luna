package com.luna.datamodel;

import java.util.Collection;

public class ProductWrapper {

    private final Collection<Product> products;

    public ProductWrapper(Collection<Product> products){
        this.products = products;
    }

    /**
     *
     * @return
     */
    public Collection<Product> getProducts() {
        return products;
    }
}
