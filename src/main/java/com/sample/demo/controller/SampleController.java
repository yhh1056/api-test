package com.sample.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yhh1056
 * @since 2021/02/05
 */

@Controller
public class SampleController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/info")
    public String hidden() {
        return "info";
    }

}
