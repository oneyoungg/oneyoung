package top.oneyoung.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oneyoung.sms.starter.SmsService;

/**
 * SmsController
 *
 * @author oneyoung
 * @since 2023/8/28 16:05
 */
@RestController
public class SmsController {

    @Autowired
    private SmsService smsService;

    @GetMapping("/sendSms")
    public void sendSms(String mobile, String code) {
        smsService.send(mobile, code);
    }
}
