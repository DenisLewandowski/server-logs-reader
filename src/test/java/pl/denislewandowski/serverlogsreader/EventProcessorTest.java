package pl.denislewandowski.serverlogsreader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.denislewandowski.serverlogsreader.entity.Event;
import pl.denislewandowski.serverlogsreader.entity.EventLog;
import pl.denislewandowski.serverlogsreader.entity.EventState;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EventProcessorTest {

    EventProcessor eventProcessor = new EventProcessor();
    EventLog startLog;
    EventLog endLog;

    @BeforeEach
    void setUp() {
        startLog = EventLog.builder()
                .id("EVENT_1")
                .host("HOST")
                .timestamp(10L)
                .type("APPLICATION_LOG")
                .state(EventState.STARTED)
                .build();
        endLog = EventLog.builder()
                .id("EVENT_1")
                .host("HOST")
                .timestamp(16L)
                .type("APPLICATION_LOG")
                .state(EventState.FINISHED)
                .build();
    }

    @Test
    void shouldCountDurationProperly() {
        startLog.setTimestamp(60L);
        endLog.setTimestamp(100L);
        List<EventLog> eventLogs = Arrays.asList(startLog, endLog);
        Map<String, Event> eventMap = eventProcessor.process(eventLogs);
        assertEquals(40L, eventMap.get("EVENT_1").getEventDuration());
    }

    @Test
    void shouldFlagEventAsTooLong() {
        List<EventLog> eventLogs = Arrays.asList(startLog, endLog);
        Map<String, Event> eventMap = eventProcessor.process(eventLogs);
        assertTrue(eventMap.get("EVENT_1").isAlert());
    }

    @Test
    void shouldNotFlagEventAsTooLong() {
        startLog.setTimestamp(10L);
        endLog.setTimestamp(12L);
        List<EventLog> eventLogs = Arrays.asList(startLog, endLog);
        Map<String, Event> eventMap = eventProcessor.process(eventLogs);
        assertFalse(eventMap.get("EVENT_1").isAlert());
    }

}
