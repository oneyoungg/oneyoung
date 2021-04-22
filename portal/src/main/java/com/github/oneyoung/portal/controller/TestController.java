package com.github.oneyoung.portal.controller;

import com.oneyoung.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author oneyoung
 * @date 2021/4/22 10:46
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("hello")
    public Result<String> checkHealthy(@RequestParam String name) {
        String message = "Hello " + name;
        return Result.success(message);
    }
}
