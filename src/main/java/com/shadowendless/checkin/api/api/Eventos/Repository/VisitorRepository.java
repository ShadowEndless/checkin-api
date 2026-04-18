package com.shadowendless.checkin.api.api.Eventos.Repository;

import com.shadowendless.checkin.api.api.Eventos.Entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    Optional<Visitor> findByNameAndEventId(String name, Long eventId);
}
