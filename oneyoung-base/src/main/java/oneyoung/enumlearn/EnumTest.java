package oneyoung.enumlearn;

/**
 * EnumTest
 *
 * @author oneyoung
 * @date 2020/8/7 007 17:00
 */


public class EnumTest {
    public static void main(String[] args) {
        System.out.println(PizzaStatus.ORDERED);
        System.out.println(PizzaStatus.ORDERED.name());
        System.out.println(PizzaStatus.ORDERED.ordinal());
        Pizza pizza = new Pizza();
        pizza.setStatus(Pizza.PizzaStatus.DELIVERED);
        System.out.println(pizza.isDeliverable());
    }

}
