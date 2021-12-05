package pl.denislewandowski.serverlogsreader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ServerLogsReaderApplication {
    private static final Logger logger = LoggerFactory.getLogger(ServerLogsReaderApplication.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.error("Put a path to the 'logfile.txt' as an input!");
        } else {
            ApplicationContext applicationContext = SpringApplication.run(ServerLogsReaderApplication.class, args);
            EventService service = applicationContext.getBean(EventService.class);
            service.run(args[0]);
        }
    }
}
