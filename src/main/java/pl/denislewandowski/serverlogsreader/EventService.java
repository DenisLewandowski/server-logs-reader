package pl.denislewandowski.serverlogsreader;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.denislewandowski.serverlogsreader.entity.Event;
import pl.denislewandowski.serverlogsreader.entity.EventLog;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventLogReader reader;
    private final EventProcessor processor;
    private final EventWriter writer;

    public void run(String inputPath) {
        String file = EventLogReader.getCompleteLogFilePath(inputPath);

        List<EventLog> logEvents = reader.readInput(file);
        Map<String, Event> eventMap = processor.process(logEvents);
        writer.write(eventMap.values());
    }

}
