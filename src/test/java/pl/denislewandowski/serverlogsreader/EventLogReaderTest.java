package pl.denislewandowski.serverlogsreader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventLogReaderTest {

    private static final String EXPECTED_PATH = "C:/test-path/logfile.txt";

    @Test
    void shouldCreateProperPathWithoutSlash() {
        String inputPath = "C:/test-path";
        assertEquals(EXPECTED_PATH, EventLogReader.getCompleteLogFilePath(inputPath));
    }

    @Test
    void shouldCreateProperPathWithSlash() {
        String inputPath = "C:/test-path/";
        assertEquals(EXPECTED_PATH, EventLogReader.getCompleteLogFilePath(inputPath));
    }

    @Test
    void shouldCreateProperPathWhenInsertedDirectFileLocation() {
        String inputPath = "C:/test-path/logfile.txt";
        assertEquals(EXPECTED_PATH, EventLogReader.getCompleteLogFilePath(inputPath));
    }
}
