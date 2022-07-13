package com.luna.datahandler;

import com.luna.datamodel.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


class ProductHandlerTest {

    private static final int FILTER_LIMIT = 15;
    private static final int PRICE_LIMIT = 10000;
    private static final BigDecimal MAX_PRICE = new BigDecimal(PRICE_LIMIT+1);

    private Product generateProduct(int position){
        BigDecimal price;
        // position to store max price
        if (position == 4){
            price = MAX_PRICE;
        } else {
            price = new BigDecimal(BigInteger.valueOf(new Random().nextInt(PRICE_LIMIT)), 2);
        }
        return new Product(position,null,null,price, "name", "description", position%2==0, null, null);

    }

    private Product[] createDataList(int howMany){
        Product[] products = new Product[howMany];
        for (int i = 0; i<howMany; i++){
            products[i] = generateProduct(i);
        }
        return products;
    }

    @Test
    void removeDuplicatesAndGetSome() {
        Product[] products = createDataList(100);
        Product[] filteredProducts =ProductHandler.removeDuplicatesAndGetSome(products);

        assertNotEquals(products.length, FILTER_LIMIT);
        assertTrue(filteredProducts.length>0);
        assertEquals(filteredProducts.length, FILTER_LIMIT);

        Product productWithMaxPrice = filteredProducts[0];
        Product firstReceivedProduct = products[0];

        assertNotEquals(productWithMaxPrice.getId(), firstReceivedProduct.getId());
        assertEquals(productWithMaxPrice.getPrice(), MAX_PRICE);
    }
}