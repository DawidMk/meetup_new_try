package pl.dma.appka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dma.appka.comment.CommentService;
import pl.dma.appka.dto.AddCommentFormDto;
import pl.dma.appka.dto.AddEventFormDto;
import pl.dma.appka.user.UserService;

import javax.validation.Valid;

//@Controller
public class CommentController {
//
//    private final CommentService commentService;
//    private final UserService userService;
//
//    @Autowired
//    public CommentController(CommentService commentService, UserService userService) {
//        this.commentService = commentService;
//        this.userService = userService;
//    }
//
//
//
//    @PostMapping("/addComment/{eventId}")
//    public String addComment(@RequestParam(value = "comment") String contents,
//                             @PathVariable String eventId,
//                             @ModelAttribute
//                             @Valid AddCommentFormDto addCommentFormDto,
//                             BindingResult result,
//                             Model model) {
//
//        if (result.hasErrors()) {
//            System.out.println(addCommentFormDto.toString());
//            return "eventDetails/{eventId}";
//        }
//
//        Integer loggedUserId = userService.getLoggedUserId();
//        commentService.addComment(addCommentFormDto, contents, eventId, loggedUserId);
//
//
//        return "commentAddedSuccess";
//    }
}
