package com.sample.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("party")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SimpleController {

    private List<Party> parties = new ArrayList<>();

    @GetMapping("/party/form/name")
    public String eventsFormName(Model model) {
        model.addAttribute("party", new Party());
        return "/party/form-name";
    }

    @PostMapping("/party/form/name")
    public String addEventName(@Valid @ModelAttribute Party party, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "/party/form-name";
        }
        parties.add(party);
        model.addAttribute("party", party);

        return "redirect:/party/form/time";
    }

    @GetMapping("/party/form/time")
    public String eventsFormTime(Model model, SessionStatus sessionStatus) {
        model.addAttribute("party", new Party());

        sessionStatus.isComplete();
        return "/party/form-time";
    }

    @PostMapping("/party/form/time")
    public String addEventTime(@Valid @ModelAttribute Party party,
                BindingResult bindingResult,
                SessionStatus sessionStatus,
                RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "/party/form-time";
        }
        parties.add(party);

        sessionStatus.isComplete();
        attributes.addFlashAttribute("newParty", party);

        return "redirect:/party/list";
    }

    @GetMapping("/party/list")
    public String getEvents(Model model) {
        final Party newParty = (Party) model.asMap().get("newParty");
        parties.add(newParty);
        model.addAttribute("party", parties);
        return "/party/list";
    }
}

