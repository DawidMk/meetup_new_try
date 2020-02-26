package pl.dma.appka.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByEventName(String eventName);

    @Query("select e from Event e where e.eventName like concat('%', :input,'%')")
    List<Event> findCurrentEvents(String input);

    List<Event> findByEventNameContainingIgnoreCase(String input);


}

