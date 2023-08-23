package top.oneyoung.base.proxy.staic;

/**
 * StaicPayProxy
 *
 * @author oneyoung
 * @since 2023-08-23 23:27
 */

public class StaticPayProxy implements PayService{

    private final PayService payService;

    public StaticPayProxy(PayService payService) {
        this.payService = payService;
    }

    @Override
    public String callback(String outTradeNo) {
        return payService.callback(outTradeNo);
    }

    @Override
    public int save(int userId, int productId) {
        openTransaction();
        int save = payService.save(userId, productId);
        commitTransaction();
        return save;
    }

    public static void openTransaction() {
        System.out.println("open transaction");
    }

    public static void commitTransaction() {
        System.out.println("commit transaction");
    }
}
