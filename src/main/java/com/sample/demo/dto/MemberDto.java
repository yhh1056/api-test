package com.sample.demo.dto;

import com.sample.demo.model.Member;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yhh1056
 * @since 2021/01/28
 */

@Getter
@Setter
public class MemberDto {

    private String username;

    private String password;

    private int age;

    private String address;

    public Member toEntity() {
        return new Member(username, password, age, address);
    }
}
