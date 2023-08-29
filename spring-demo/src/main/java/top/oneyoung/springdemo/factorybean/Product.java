package top.oneyoung.springdemo.factorybean;

import lombok.Data;

/**
 * Product
 *
 * @author oneyoung
 * @since 2023/8/29 10:24
 */
@Data
public class Product {

    private String name;

    private String color;

    private int price;

    public void init() {
        System.out.println("Product init");
    }
}
