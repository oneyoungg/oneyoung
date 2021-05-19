package com.github.oneyoung.portal.controller;

import com.github.oneyoung.common.result.Result;
import com.github.oneyoung.portal.entity.CallRequest;
import com.github.oneyoung.portal.exception.PortalException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * TestController
 *
 * @author oneyoung
 * @date 2021/4/22 10:46
 */
@Validated
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("hello")
    public Result<String> checkHealthy(@RequestParam String name) {
        String message = "Hello " + name;
        return Result.success(message);
    }

    @PostMapping("exception")
    public Result<String> exception(@Valid @RequestBody CallRequest name) {
        throw new PortalException("PORTAL_EXCEPTION");
    }

    @GetMapping("c")
    public Result<String> exception() {
        throw new PortalException("TEST_EXCEPTION");
    }
}
