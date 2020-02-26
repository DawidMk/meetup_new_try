package pl.dma.appka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dma.appka.event.Event;
import pl.dma.appka.event.EventService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EventRESTController {

    @Autowired
    private EventService eventService;


    @GetMapping("/events")
    public List<Event> getEvents(){
//    public Event getEvents(){
        return eventService.findAllEvents();
//        return eventService.findById(1);
//                .stream().filter(e -> e.getStartDate().isAfter(LocalDate.now())).collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public List<Event> filterEvents(@RequestParam(name = "from") String from, @RequestParam(name = "to") String to){
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return eventService.findAllEvents().stream().filter(e -> e.getStartDate().compareTo(fromDate) >= 0 && e.getEndDate().compareTo(toDate) <= 0 ).collect(Collectors.toList());
    }
}
