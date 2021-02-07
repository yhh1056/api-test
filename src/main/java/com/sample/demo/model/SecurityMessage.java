package com.sample.demo.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.Authentication;

/**
 * @author yhh1056
 * @since 2021/02/07
 */

@Getter
@Builder
public class SecurityMessage {

    private String message;

    private Authentication authentication;

}
