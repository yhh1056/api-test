package com.swagger.demo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

/**
 * @author yhh1056
 * @since 2021/01/28
 */

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String password;

    public Member(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public Member() {
    }

    @Override
    public String toString() {
        return "Member{" +
            "userId='" + userId + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
