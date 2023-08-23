package top.oneyoung.base.proxy.staic;

/**
 * ProxyTest
 *
 * @author oneyoung
 * @since 2023-08-23 23:25
 */

public class ProxyTest {
    public static void main(String[] args) {
//        PayService payService = new PayServiceImpl();
//
//        payService = new StaticPayProxy(payService);
//
//        payService.save(12,1);
        PayService proxyInstance = (PayService) new JdkProxy().getProxyInstance(new PayServiceImpl());
        proxyInstance.save(11,12);
    }

    public static void openTransaction() {
        System.out.println("open transaction");
    }

    public static void commitTransaction() {
        System.out.println("commit transaction");
    }
}
