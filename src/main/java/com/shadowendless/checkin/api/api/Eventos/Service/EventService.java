package com.shadowendless.checkin.api.api.Eventos.Service;


import com.shadowendless.checkin.api.api.Eventos.DTO.EventDTO;
import com.shadowendless.checkin.api.api.Eventos.Entity.Event;
import com.shadowendless.checkin.api.api.Eventos.Repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Event findOrCreate(EventDTO dto) {

        return repository
                .findByNameAndLocationAndStartDate(
                        dto.getName(),
                        dto.getLocation(),
                        dto.getStartDate()
                )
                .orElseGet(() -> {
                    Event e = new Event();
                    e.setName(dto.getName());
                    e.setLocation(dto.getLocation());
                    e.setStartDate(dto.getStartDate());
                    e.setEndDate(dto.getEndDate());

                    return repository.save(e);
                });
    }
}
