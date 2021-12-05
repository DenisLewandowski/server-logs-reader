package pl.denislewandowski.serverlogsreader;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.denislewandowski.serverlogsreader.entity.Event;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class EventWriter {

    private static final Logger logger = LoggerFactory.getLogger(EventWriter.class);
    private final EventRepository eventRepository;

    public void write(Collection<Event> events) {
        logger.info(String.format("Started writing of %s logs", events.size()));
        long start = System.currentTimeMillis();
        eventRepository.saveAll(events);
        logger.info(String.format("Writing finished and took %s ms", System.currentTimeMillis() - start));
    }
}
