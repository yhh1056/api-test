package com.sample.mvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@DisplayName("MVC 테스트")
class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("이벤트를 폼을 가져온다.")
    void getEvent() throws Exception{
        mockMvc.perform(get("/events/form"))
                .andDo(print())
                .andExpect(view().name("/events/form"))
                .andExpect(model().attributeExists("event"));
    }

    @Test
    @DisplayName("이벤트를 등록한다.")
    void postEvent() throws Exception {
        mockMvc.perform(post("/events")
                .param("name", "mvc event")
                .param("limit", "10"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("name").value("mvc event"))
                .andExpect(jsonPath("limit").value(10));
    }

    @Test
    @DisplayName("Evnet의 Limit Value를 바인딩이 되지 않는 값을 보낸다.")
    // 에러를 BindingResult가 가져가서 에러가 발생하지 않고 0값이 저장된다
    void postInvalidBindingEvent() throws Exception {
        mockMvc.perform(post("/events")
                .param("name", "mvc event")
                .param("limit", "invalid value"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("name").value("mvc event"))
                .andExpect(jsonPath("limit").value(0));
    }

    @Test
    @DisplayName("Evnet의 Limit Value를 음수로 보낸다.")
    void postBindingEventInvalidValue() throws Exception {
        mockMvc.perform(post("/events")
                .param("name", "mvc event")
                .param("limit", "-1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(model().hasErrors());
    }


}