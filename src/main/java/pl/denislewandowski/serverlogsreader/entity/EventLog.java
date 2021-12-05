package pl.denislewandowski.serverlogsreader.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventLog {
    private String id;
    private EventState state;
    private Long timestamp;
    private String type;
    private String host;
}
