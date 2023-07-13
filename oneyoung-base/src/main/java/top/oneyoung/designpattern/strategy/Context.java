package top.oneyoung.designpattern.strategy;

/**
 * Context
 *
 * @author oneyoung
 * @since 2023/7/13 17:43
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(){
        strategy.execute();
    }
}
