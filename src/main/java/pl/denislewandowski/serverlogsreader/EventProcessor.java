package pl.denislewandowski.serverlogsreader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.denislewandowski.serverlogsreader.entity.Event;
import pl.denislewandowski.serverlogsreader.entity.EventLog;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EventProcessor {

    private static final Logger logger = LoggerFactory.getLogger(EventProcessor.class);

    public Map<String, Event> process(List<EventLog> logEvents) {
        logger.info(String.format("Started processing of %s logs", logEvents.size()));
        long start = System.currentTimeMillis();
        Map<String, Event> eventMap = logEvents.stream()
                .map(Event::fromEventLog)
                .collect(Collectors.toMap(Event::getId, Function.identity(), EventProcessor::merge));
        logger.info(String.format("Processing finished and took %s ms", System.currentTimeMillis() - start));
        return eventMap;
    }

    private static Event merge(Event existing, Event replacement) {
        Long startEventTimestamp;
        Long endEventTimestamp;

        if (existing.getStartLog() != null) {
            startEventTimestamp = existing.getStartLog().getTimestamp();
            endEventTimestamp = replacement.getEndLog().getTimestamp();
        } else {
            startEventTimestamp = replacement.getStartLog().getTimestamp();
            endEventTimestamp = existing.getEndLog().getTimestamp();
        }

        long duration = endEventTimestamp - startEventTimestamp;
        existing.setEventDuration(duration);
        existing.setAlert(duration > 4);

        return existing;
    }
}
