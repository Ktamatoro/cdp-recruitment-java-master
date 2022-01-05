package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.entity.Event;

import java.util.List;

/**
 * @Author kerekunuri TAMATORO
 * @Date 04/01/2022
 */
public interface EventService {

    List<Event> getEvents();

    void delete(Long id);

    List<Event> getFilteredEvents(String query);

    void addEvent(Event event);
}
