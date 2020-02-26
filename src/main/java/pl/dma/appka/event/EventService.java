package pl.dma.appka.event;

import org.springframework.stereotype.Service;
import pl.dma.appka.dto.AddEventFormDto;
import pl.dma.appka.exceptions.EventDoesntExistException;

import java.util.List;

@Service
public class EventService {

    private final EventDao eventDao;
    private final DtoToEventConverter dtoToEventConverter;
    private final EventRepository eventRepository;

    public EventService(EventDao eventDao, DtoToEventConverter dtoToEventConverter, EventRepository eventRepository) {
        this.eventDao = eventDao;
        this.dtoToEventConverter = dtoToEventConverter;
        this.eventRepository = eventRepository;
    }
    public void saveEventToDb(AddEventFormDto addEventFormDto){
        eventDao.saveEventToDb(dtoToEventConverter.rewriteDtoToEvent(addEventFormDto));
    }

    public void updateEventInDb(Event event){
        eventDao.saveEventToDb(event);
    }

    public List<Event> findAllEvents(){
        return eventRepository.findAll();
    }

    public List<Event> findEventByName(String input){
        return eventRepository.findByEventName(input);
    }

    public List<Event> findEventLikeInput(String input){
        return eventRepository.findByEventNameContainingIgnoreCase(input);
    }

    public Event findById(Integer id){
        return eventRepository.findById(id).orElseThrow(() -> new EventDoesntExistException("no event found"));
    }
}
