package pl.denislewandowski.serverlogsreader.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Event {
    @Id
    private String id;
    private Long eventDuration;
    private String type;
    private String host;
    private boolean alert;

    @Transient
    private EventLog startLog;
    @Transient
    private EventLog endLog;

    public static Event fromEventLog(EventLog eventLog) {
        Event event = new Event();
        event.setId(eventLog.getId());
        event.setType(eventLog.getType());
        event.setHost(eventLog.getHost());
        if(EventState.STARTED.equals(eventLog.getState()))
            event.setStartLog(eventLog);
        else
            event.setEndLog(eventLog);

        return event;
    }
}
