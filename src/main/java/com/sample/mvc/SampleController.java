package com.sample.mvc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping(
            value = "hello",
            //JSON 요청만 받는다.
            consumes = MediaType.APPLICATION_JSON_VALUE,
            //JSON 응답을 한다.
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String hello() {
        return "hello";
    }
}

