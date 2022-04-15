package top.oneyoung.portal.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oneyoung.common.result.Result;
import top.oneyoung.converter.Converter;
import top.oneyoung.portal.entity.CallRequest;
import top.oneyoung.portal.entity.Request;
import top.oneyoung.portal.entity.Response;
import top.oneyoung.portal.exception.PortalException;

import javax.validation.Valid;

/**
 * TestController
 *
 * @author oneyoung
 * @since 2021/4/22 10:46
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

    @GetMapping("convert")
    public Result<Response> convert(Request request) {
        return Result.success(Converter.directConvert(request, Response.class));
    }
}
