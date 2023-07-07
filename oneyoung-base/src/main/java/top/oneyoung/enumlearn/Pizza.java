package top.oneyoung.enumlearn;

/**
 * Pizza
 *
 * @author oneyoung
 * @date 2020/8/7 007 17:04
 */


public class Pizza {
    private PizzaStatus status;
    public enum PizzaStatus {
        ORDERED,
        READY,
        DELIVERED
    }

    /**
     *
     * 由于枚举类型确保JVM中仅存在一个常量实例，因此我们可以安全地使用“ ==”运算符比较两个变量，如上例所示；此外，“ ==”运算符可提供编译时和运行时的安全性。
     *
     * 首先，让我们看一下以下代码段中的运行时安全性，其中“ ==”运算符用于比较状态，并且如果两个值均为null 都不会引发 NullPointerException。相反，如果使用equals方法，将抛出 NullPointerException：
     * @return result
     */
    public boolean isDeliverable() {
        return getStatus() == PizzaStatus.READY;
    }

    public int getDeliveryTimeInDays() {
        switch (status) {
            case ORDERED: return 5;
            case READY: return 2;
            case DELIVERED: return 1;
            default: return 0;
        }
    }

    public PizzaStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
    }
}
