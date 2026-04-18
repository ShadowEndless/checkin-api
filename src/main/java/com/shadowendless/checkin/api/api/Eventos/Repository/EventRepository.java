package com.shadowendless.checkin.api.api.Eventos.Repository;

import com.shadowendless.checkin.api.api.Eventos.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByNameAndLocationAndStartDate(
            String name,
            String location,
            LocalDateTime startDate
    );
}
