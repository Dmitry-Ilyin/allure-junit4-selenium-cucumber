package org.example.framework.Classes;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private static List<Product> products = new ArrayList<>();

    public static  List<Product>  getListProducts() {
        return products;
    }
}
