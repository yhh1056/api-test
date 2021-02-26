package com.sample.mvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class SimpleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("세션에 데이터가 담겨있다.")
    void session() throws Exception {
        Party newParty = new Party();
        newParty.setName("party");
        newParty.setTime(10);

        mockMvc.perform(get("/party/list")
                .flashAttr("newParty", newParty))
                .andDo(print())
                .andExpect(xpath("//p").nodeCount(2));
    }
}