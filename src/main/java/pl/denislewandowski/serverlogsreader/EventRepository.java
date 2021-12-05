package pl.denislewandowski.serverlogsreader;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.denislewandowski.serverlogsreader.entity.Event;

public interface EventRepository extends JpaRepository<Event, String> {}
