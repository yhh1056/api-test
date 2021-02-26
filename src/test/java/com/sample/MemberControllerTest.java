package com.sample;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class MemberControllerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestdocsConfig docs;

    @Autowired
    RestDocumentationResultHandler document;

    MockMvc mockMvc;


    @BeforeEach
    void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(docs.write())
                .build();
    }

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
                .andExpect(status().isOk())
                .andDo(document.document(
                        responseFields(
                                fieldWithPath("[].id").description("유저 식별자"),
                                fieldWithPath("[].name").description("유저 이름"),
                                fieldWithPath("[].email").description("유저 이메일"),
                                fieldWithPath("[].address").description("유저 주소"),
                                fieldWithPath("[].createAt").description("생성일")
                        )
                ));
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

//        Member save = memberRepository.save(member);

//        mockMvc.perform(MockMvcRequestBuilders.get("/members/{id}", save.getId()))
//                .andExpect(jsonPath("id").isNumber())
//                .andExpect(jsonPath("name").value("tester"))
//                .andExpect(jsonPath("email").value("test@mail.com"))
//                .andExpect(jsonPath("address").value("seoul"))
//                .andDo(print())
//                .andExpect(status().isOk());
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