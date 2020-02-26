package pl.dma.appka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dma.appka.comment.Comment;
import pl.dma.appka.comment.CommentService;
import pl.dma.appka.dto.AddCommentFormDto;
import pl.dma.appka.dto.AddEventFormDto;
import pl.dma.appka.event.Event;
import pl.dma.appka.event.EventService;
import pl.dma.appka.user.User;
import pl.dma.appka.user.UserService;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class EventController {

    private final EventService eventService;
    private final CommentService commentService;
    private final UserService userService;
//    private final StorageSe

    @Autowired
    public EventController(EventService eventService, CommentService commentService, UserService userService) {
        this.eventService = eventService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/addEvent")
    public String getAddEventPage(Model model) {
        model.addAttribute(new AddEventFormDto());
        return "addEventForm";
    }

    @GetMapping("/eventAddSuccess")
    public String eventAddedSuccessPage() {
        return "eventAddSuccess";
    }

    @PostMapping("/addEvent")
    public String addEvent(
            @ModelAttribute
            @Valid AddEventFormDto addEventFormDto,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            //todo logger
            System.out.println(addEventFormDto.toString());
            return "addEventForm";
        }
        eventService.saveEventToDb(addEventFormDto);
        return "redirect:/eventAddSuccess";
    }

    @GetMapping("/eventDetails/{id}")
    public String getEventDetails(@PathVariable String id, Model model) {
        Event event = eventService.findById(Integer.valueOf(id));
        Set<User> attendants = event.getAttendants();
        List<Comment> comments = event.getComments().stream().sorted(Comparator.comparing(Comment::getId)).collect(Collectors.toList());
        model.addAttribute("event", event);
        model.addAttribute("attendants", attendants);
        model.addAttribute("comments", comments);
        model.addAttribute(new AddCommentFormDto());

        return "eventDetails";
    }

    @PostMapping("/addComment/{eventId}")
    public String addComment(
            @PathVariable String eventId,
            @ModelAttribute
            @Valid AddCommentFormDto addCommentFormDto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            System.out.println(addCommentFormDto.toString());
            return "eventDetails/{eventId}";
        }

        Integer loggedUserId = userService.getLoggedUserId();
        commentService.addComment(addCommentFormDto, eventId, loggedUserId);

        return "commentAddedSuccess";
    }

    @GetMapping("/signup/{eventId}")
    public String signup(@PathVariable String eventId) {
        User loggedUser = userService.getLoggedUser();
        Event event = eventService.findById(Integer.valueOf(eventId));
        Set<User> attendants = event.getAttendants();
        attendants.add(loggedUser);
        eventService.updateEventInDb(event);
        return "signedupSuccess";

    }

    @GetMapping("/unsign/{eventId}")
    public String unsign(@PathVariable String eventId) {
        User loggedUser = userService.getLoggedUser();
        Event event = eventService.findById(Integer.valueOf(eventId));
        Set<User> attendants = event.getAttendants();
        attendants.remove(loggedUser);
        eventService.updateEventInDb(event);
        return "unsignedSuccess";

    }
}
