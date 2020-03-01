package pl.dma.appka.event;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dma.appka.dto.AddEventFormDto;
import pl.dma.appka.user.UserRepository;
import pl.dma.appka.user.UserService;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

public class DtoToEventConverterTest {

    @Mock
    UserService userService;

    @Mock
    UserRepository userRepository;

    private AddEventFormDto dto;
    private Event event;
    private  DtoToEventConverter dtoToEventConverter = new DtoToEventConverter(userService, userRepository);

    @Before
    public void setUp(){
        dto = new AddEventFormDto();
        dto.setDescription("desc");
        dto.setEventName("name");
        dto.setStartDate(LocalDate.parse("2020-01-01"));
        dto.setEndDate(LocalDate.parse("2020-01-02"));

        event = Event.builder().eventName("name").description("desc").startDate(LocalDate.parse("2020-01-01")).endDate(LocalDate.parse("2020-01-02")).build();
    }

    @Test
    public void checkIfConverterConvertsCorrectly(){

        Event eventFromDto = dtoToEventConverter.rewriteDtoToEvent(dto);
        Assert.assertEquals(event.getEventName(), eventFromDto.getEventName());
    }

}