package pl.dma.appka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dma.appka.event.Event;
import pl.dma.appka.event.EventService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    private final EventService eventService;

    public SearchController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/search")
    public String getEventSearchResult(@RequestParam(name = "search") String search, @RequestParam(name = "time") String time, Model model) {
        List<Event> searchResultByInput = eventService.findEventLikeInput(search);
        model.addAttribute("q", search);
        model.addAttribute("t", time);

        switch (time) {
            case "current":
                model.addAttribute("foundEvents", searchResultByInput
                        .stream()
                        .filter(e -> e.getStartDate()
                                .isBefore(LocalDate.now()) && e.getEndDate().isAfter(LocalDate.now()))
                        .sorted(Comparator.comparing(Event::getStartDate))
                        .collect(Collectors.toList()));
                break;
            case "current-and-future":
                model.addAttribute("foundEvents", searchResultByInput
                        .stream()
                        .filter(e -> e.getEndDate()
                                .isAfter(LocalDate.now()))
                        .sorted(Comparator.comparing(Event::getStartDate))
                        .collect(Collectors.toList()));
                break;
            case "all":
                model.addAttribute("foundEvents", searchResultByInput);
                break;
        }
        return "searchResultPage.html";
    }
}
