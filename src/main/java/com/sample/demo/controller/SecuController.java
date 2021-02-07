package com.sample.demo.controller;

import com.sample.demo.model.SecurityMessage;
import lombok.Getter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhh1056
 * @since 2021/02/07
 */

@RestController
public class SecuController {

//    @Secured("ROLE_USER")       // 권한 설정
//    @GetMapping("/user")
//    public SecurityMessage user() {
//        return SecurityMessage.builder()
//            .message("user page")
//            .authentication(SecurityContextHolder.getContext().getAuthentication())
//            .build();
//    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public SecurityMessage admin() {
        return SecurityMessage.builder()
            .message("admin page")
            .authentication(SecurityContextHolder.getContext().getAuthentication())
            .build();
    }

}
