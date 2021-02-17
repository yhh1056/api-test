package com.sample.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.sample.demo.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author yhh1056
 * @since 2021/02/07
 */

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SecurityConfig securityConfig;

    @DisplayName("비회원은 hello와 info에 접근할 수없다")
    @Test
    @WithMockUser
    void anonymousUserTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
            .andExpect(status().isOk());
    }
}