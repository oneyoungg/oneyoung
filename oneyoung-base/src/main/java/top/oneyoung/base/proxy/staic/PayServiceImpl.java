package top.oneyoung.base.proxy.staic;

/**
 * PayServiceImpl
 *
 * @author oneyoung
 * @since 2023-08-23 23:23
 */

public class PayServiceImpl implements PayService {

    @Override
    public String callback(String outTradeNo) {
        System.out.println("callback success");
        return outTradeNo;
    }

    @Override
    public int save(int userId, int productId) {
        System.out.println("save success");
        return productId;
    }
}
