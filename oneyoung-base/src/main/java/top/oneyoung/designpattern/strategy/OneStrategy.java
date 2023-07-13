package top.oneyoung.designpattern.strategy;

/**
 * OneStrategy
 *
 * @author oneyoung
 * @since 2023/7/13 17:43
 */
public class OneStrategy implements Strategy{
    @Override
    public void execute() {
        System.out.println("OneStrategy");
    }
}
