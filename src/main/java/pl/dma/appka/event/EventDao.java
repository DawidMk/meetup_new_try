package pl.dma.appka.event;

import org.springframework.stereotype.Repository;

@Repository
public class EventDao {

    private final EventRepository eventRepository;

    public EventDao(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void saveEventToDb(Event event) {
        eventRepository.save(event);
    }
}
