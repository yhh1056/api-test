package com.swagger.demo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yhh1056
 * @since 2021/01/28
 */

@Getter
@Setter
public class MemberDto {

    private String userId;

    private String password;

    public Member toEntity() {
        return new Member(userId, password);
    }
}
