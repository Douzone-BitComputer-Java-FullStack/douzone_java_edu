package edu.douzone.bitc.modernjava.chapter9.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class ProductFactory {

    final static Map<String, Supplier<Product>> map = new HashMap<>();

    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    /**
     * 일반 Factory Method Pattern
     *
     * @param name 파라미터를 통해 넘겨받을 객체를 생성하기 위한 이름
     * @return Product
     */
    public static Product createProduct(String name) {
        switch (name) {
            case "loan":
                return new Loan();
            case "stock":
                return new Stock();
            case "bond":
                return new Bond();
            default:
                throw new RuntimeException("No Such product " + name);
        }
    }

    /**
     * Lambda 를 이용한 Factory Method Pattern
     *
     * @param name 파라미터를 통해 넘겨받을 객체를 생성하기 위한 이름
     * @return Product
     */
    public static Product createProductLambda(String name) {
        Supplier<Product> p = map.get(name);
        if (p != null) {
            return p.get();
        }

        throw new IllegalArgumentException("No Such Product " + name);
    }
}
