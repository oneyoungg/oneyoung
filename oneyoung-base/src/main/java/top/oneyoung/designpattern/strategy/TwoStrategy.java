package top.oneyoung.designpattern.strategy;

/**
 * TwoStrategy
 *
 * @author oneyoung
 * @since 2023/7/13 17:43
 */
public class TwoStrategy implements Strategy{
    @Override
    public void execute() {
        System.out.println("TwoStrategy");
    }
}
