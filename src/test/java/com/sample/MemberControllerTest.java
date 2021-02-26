package com.sample;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("유저 전체 조회")
    void getAll() throws Exception {
        Member member = Member.builder()
                .name("tester")
                .email("test@mail.com")
                .address("seoul")
                .createAt(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        mockMvc.perform(MockMvcRequestBuilders.get("/members"))
                .andExpect(jsonPath("$..name").value("tester"))
                .andExpect(jsonPath("$..email").value("test@mail.com"))
                .andExpect(jsonPath("$..address").value("seoul"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("유저 단건 조회")
    void getMember() throws Exception {
        Member member = Member.builder()
                .name("tester")
                .email("test@mail.com")
                .address("seoul")
                .createAt(LocalDateTime.now())
                .build();

        Member save = memberRepository.save(member);

        mockMvc.perform(MockMvcRequestBuilders.get("/members/{id}", save.getId()))
                .andExpect(jsonPath("id").isNumber())
                .andExpect(jsonPath("name").value("tester"))
                .andExpect(jsonPath("email").value("test@mail.com"))
                .andExpect(jsonPath("address").value("seoul"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("유저 정상 등록")
    void save() throws Exception {
        Member member = Member.builder()
                .name("tester")
                .email("test@mail.com")
                .address("seoul")
                .createAt(LocalDateTime.now())
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/members")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(member)))
                .andExpect(jsonPath("id").isNumber())
                .andExpect(jsonPath("name").value("tester"))
                .andExpect(jsonPath("email").value("test@mail.com"))
                .andExpect(jsonPath("address").value("seoul"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}