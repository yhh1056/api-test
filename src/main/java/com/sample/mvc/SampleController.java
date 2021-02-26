package com.sample.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("event")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SampleController {

    private final List<Event> events = new ArrayList<>();

    @GetMapping("/events/form")
    public String eventsForm(Model model, SessionStatus sessionStatus) {
        Event event = new Event();
        model.addAttribute("event", event);

        sessionStatus.isComplete();     // 세션이 언제 종료될지
        return "/events/form";
    }

    @PostMapping("/events")
    public String addEvent(@Valid @ModelAttribute Event event, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> System.out.println(e.toString()));
            return "/events/form";
        }
        events.add(event);
        model.addAttribute("events", events);

        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model) {
        model.addAttribute("events", events);
        return "/events/list";
    }

    @GetMapping("/events/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable Long id, @MatrixVariable String name) {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        System.out.println(name);
        return event;
    }

    @DeleteMapping("/events/{id}")
    public String remove(@PathVariable int id) {
        return "events";
    }
}

