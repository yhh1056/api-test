package com.sample.mvc;

import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {

    private Long id;

    @NotNull
    private String name;

    @Min(0)
    private int limit;

    private LocalDateTime createAt;
//
//    public Event() {
//        this.createAt = LocalDateTime.now();
//    }
}
