package top.oneyoung.base.reflex;

/**
 * Par
 *
 * @author oneyoung
 * @since 2023/8/23 18:52
 */
public class Par extends ParP{
    private String name;

    private Integer age;

    public Par() {

    }

    private Par(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private Par(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Par{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
