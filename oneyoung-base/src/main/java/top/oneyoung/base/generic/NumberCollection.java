package top.oneyoung.base.generic;

/**
 * NumberCollection
 *
 * @author oneyoung
 * @since 2023/8/23 17:54
 */
public class NumberCollection<T extends Number> {

    private final T t;

    public NumberCollection(T t) {
        this.t = t;
    }

    public void print() {
        System.out.println(t.getClass());
        System.out.println(t);
    }

    public static void print1(NumberCollection<? extends Number> numberCollection) {
        System.out.println(numberCollection.t.getClass());
        System.out.println(numberCollection.t);
    }

    public static void main(String[] args) {
        NumberCollection<Integer> numberCollection = new NumberCollection<>(1);
        print1(numberCollection);

        NumberCollection<Double> numberCollection1 = new NumberCollection<>(1.0);
        print1(numberCollection1);
    }

}
