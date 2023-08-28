package top.oneyoung.sms.starter;

/**
 * SmsService
 *
 * @author oneyoung
 * @since 2023/8/28 14:52
 */
public interface SmsService {

    void send(String mobile, String code);
}
