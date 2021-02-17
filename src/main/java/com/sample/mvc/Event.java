package com.sample.mvc;

import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice.Local;

@Getter
@Setter
public class Event {
    private Long id;

    private String name;

    @Min(0)
    private int limit;

    private LocalDateTime createAt;
//
//    public Event() {
//        this.createAt = LocalDateTime.now();
//    }
}
