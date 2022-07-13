package com.luna.datahandler;

import com.luna.datamodel.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductHandler {

    private final static int HOW_MANY = 15;

    /**
     * Requirement: the 15 most expensive products
     * Requirement: furniture already assembled, sorted by alphabetical order, taking care to remove the duplicates
     *
     * @param retrievedData
     * @return
     */
    public static Product[] removeDuplicatesAndGetSome(final Product[] retrievedData){

        //remove duplicates and get only assembled products - no requirement to remove product when price is null
        Set<Product> products = Arrays.stream(retrievedData).
                filter(product -> product.isAssembled())
                .collect(Collectors.toSet());

        //sort by price and return only 15 elements
        List<Product> result = new ArrayList<>(products);
        return (result.stream()
                .sorted(Comparator.comparing(Product::getPrice, Comparator.nullsLast(Comparator.reverseOrder())))
                .limit(HOW_MANY).collect(Collectors.toUnmodifiableList()))
                .toArray(new Product[HOW_MANY]);

    }

}
