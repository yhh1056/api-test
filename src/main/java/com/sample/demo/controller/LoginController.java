package com.sample.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yhh1056
 * @since 2021/02/05
 */

@Controller
public class LoginController {

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public String hidden() {
        return "info";
    }

}
