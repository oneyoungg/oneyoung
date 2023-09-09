package top.ooneyoung.rabbit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ooneyoung.rabbit.producter.MyProducter;

/**
 * Mycontrooler
 *
 * @author oneyoung
 * @since 2023/9/8 14:13
 */
@RestController
public class MyControoler {

    @Autowired
    MyProducter myProducter;

    @GetMapping("send")
    public String send(String message,String exchange, String routeKey) {
        myProducter.send(message,exchange,  routeKey);
        return "success";
    }
}
