package pl.dma.appka.comment;

import org.springframework.stereotype.Service;
import pl.dma.appka.dto.AddCommentFormDto;
import pl.dma.appka.event.EventRepository;
import pl.dma.appka.exceptions.EventDoesntExistException;
import pl.dma.appka.exceptions.UserDinnaeExistsException;
import pl.dma.appka.user.UserRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public void addComment(AddCommentFormDto addCommentFormDto, String eventId, Integer loggedUserId) {
        Comment comment = Comment.builder()
                .author(userRepository.findById(loggedUserId).orElseThrow(() -> new UserDinnaeExistsException("no such user")))
//                .commentedEvent(eventRepository.findById(Integer.valueOf(eventId)).orElseThrow(() -> new EventDoesntExistException("no such event")))
                .commentedEvent(eventRepository.findById(Integer.valueOf(eventId)).orElseThrow(() -> new EventDoesntExistException("no such event")))
                .contents(addCommentFormDto.getContents())
                .build();

        commentRepository.save(comment);
    }
}
