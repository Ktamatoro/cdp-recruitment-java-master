package adeo.leroymerlin.cdp.repository;

import adeo.leroymerlin.cdp.entity.Event;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface EventRepository extends Repository<Event, Long> {

    void deleteById(Long eventId);

    List<Event> findAllBy();

    Optional<Event> getById(Long eventID);

    void save(Event event);
}
