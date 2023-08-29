package top.oneyoung.springdemo.factorybean;

import lombok.Data;

/**
 * Order
 *
 * @author oneyoung
 * @since 2023/8/29 11:20
 */
@Data
public class Order {

    private String name;

    private String color;

    private int price;

    public void init() {
        System.out.println("order init");
    }
}
