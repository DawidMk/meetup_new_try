package pl.dma.appka.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.dma.appka.dto.AddEventFormDto;
import pl.dma.appka.user.User;
import pl.dma.appka.user.UserRepository;
import pl.dma.appka.user.UserService;

@Component
public class DtoToEventConverter {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public DtoToEventConverter(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    private User loggedUser() {
        String loggedUserName = userService.getLoggedUserName();
        return userRepository.findByName(loggedUserName).get(0);
    }

    public Event rewriteDtoToEvent(AddEventFormDto addEventFormDto) {
        return Event.builder()
                .eventName(addEventFormDto.getEventName())
                .description(addEventFormDto.getDescription())
                .startDate(addEventFormDto.getStartDate())
                .endDate(addEventFormDto.getEndDate())
                .eventPicture(addEventFormDto.getPicture())
                .owner(loggedUser())
                .build();
    }
}
