package adeo.leroymerlin.cdp.service.impl;

import adeo.leroymerlin.cdp.entity.Band;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.entity.Member;
import adeo.leroymerlin.cdp.repository.EventRepository;
import adeo.leroymerlin.cdp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAllBy();
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAllBy();
        if (query.isEmpty()){
            return events;
        }
        return events.stream()
                .filter(event -> event.getBands().stream()
                        .anyMatch(band -> band.getMembers().stream()
                                .anyMatch(member -> member.getName().matches("(?i).*" + query + ".*"))))
                .collect(Collectors.toList());

        // Filter the events list in pure JAVA here

    }

    @Override
    public void addEvent(Event event) {
        Optional<Event> eventOptional = eventRepository.getById(event.getId());
        eventOptional.ifPresent(event1 -> {
            event1.setComment(event.getComment());
            event1.setNbStars(event.getNbStars());
            this.eventRepository.save(event1);
        });


    }

    private boolean exist(Set<Band> bands, String query){
        for (Band band: bands) {
            for (Member member: band.getMembers()) {
                if (member.getName().matches("(?i).*" + query + ".*")){
                    return true;
                }
            }
        }
        return false;
    }


}
