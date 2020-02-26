package pl.dma.appka.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.dma.appka.event.Event;
import pl.dma.appka.event.EventService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    private final EventService eventService;

    public IndexController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("events", eventService.findAllEvents()
        .stream()
        .filter(e -> e.getEndDate()
        .isAfter(LocalDate.now()))
        .sorted(Comparator.comparing(Event::getStartDate))
        .collect(Collectors.toList()));

        return "index";
    }

    @GetMapping("/restricted")
    public String restrictedPage(Authentication authentication){
        authentication.getName();
        return "restrictedPage";
    }

}
