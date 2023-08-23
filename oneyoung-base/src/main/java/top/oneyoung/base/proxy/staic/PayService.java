package top.oneyoung.base.proxy.staic;

/**
 * Start
 *
 * @author oneyoung
 * @since 2023-08-23 23:21
 */

public interface PayService {

    String callback(String outTradeNo);

    int save(int userId, int productId);

}
