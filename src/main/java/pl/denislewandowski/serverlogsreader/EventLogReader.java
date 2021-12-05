package pl.denislewandowski.serverlogsreader;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.denislewandowski.serverlogsreader.entity.EventLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventLogReader {

    private static final Logger logger = LoggerFactory.getLogger(EventLogReader.class);
    public static final String LOG_FILE_NAME = "logfile.txt";
    private final Gson gson = new Gson();

    public List<EventLog> readInput(String file) {
        List<EventLog> logEvents = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            logger.info(String.format("Started reading form file: %s", file));
            long start = System.currentTimeMillis();
            String currentLine = reader.readLine();
            while (currentLine != null) {
                logEvents.add(gson.fromJson(currentLine, EventLog.class));
                currentLine = reader.readLine();
            }
            logger.info(String.format("Reading finished and took %s ms", System.currentTimeMillis() - start));
        } catch (IOException e) {
            logger.error("Error! The file is invalid or does not exist!");
        }

        return logEvents;
    }

    public static String getCompleteLogFilePath(String inputArgumentPath) {
        if (inputArgumentPath.endsWith(LOG_FILE_NAME))
            return inputArgumentPath;

        return inputArgumentPath.endsWith("/") ? inputArgumentPath + LOG_FILE_NAME : inputArgumentPath + "/" + LOG_FILE_NAME;
    }
}
