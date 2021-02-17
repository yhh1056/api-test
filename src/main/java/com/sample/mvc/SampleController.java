package com.sample.mvc;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SampleController {

    @GetMapping("/events/form")
    public String getEvents(Model model) {
        model.addAttribute("event", new Event());
        return "/events/form";
    }

    @PostMapping("/events")
    public String addEvent(@Valid @ModelAttribute Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> System.out.println(e.toString()));
            return "/events/form";
        }
        return "/events/list";
    }

    @GetMapping("/events/{id}")
    public String getEvent(@PathVariable Integer id, @MatrixVariable String name) {
        return "events";
    }

    @DeleteMapping("/events/{id}")
    public String remove(@PathVariable int id) {
        return "events";
    }
}

