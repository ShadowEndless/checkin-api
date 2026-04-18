package com.shadowendless.checkin.api.api.Eventos.Service;

import com.shadowendless.checkin.api.api.Eventos.DTO.VisitorDTO;
import com.shadowendless.checkin.api.api.Eventos.Entity.Event;
import com.shadowendless.checkin.api.api.Eventos.Entity.Visitor;
import com.shadowendless.checkin.api.api.Eventos.Repository.VisitorRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {

    private final VisitorRepository repository;

    public VisitorService(VisitorRepository repository) {
        this.repository = repository;
    }

    public Visitor merge(VisitorDTO dto, Event event) {

        return repository
                .findByNameAndEventId(dto.getName(), event.getId())
                .map(existing -> mergeData(existing, dto))
                .orElseGet(() -> createNew(dto, event));
    }

    private Visitor createNew(VisitorDTO dto, Event event) {
        Visitor v = new Visitor();
        v.setName(dto.getName());
        v.setEntered(dto.getEntered());
        v.setEntryDate(dto.getEntryDate());
        v.setExitDate(dto.getExitDate());
        v.setEvent(event);

        return repository.save(v);
    }

    private Visitor mergeData(Visitor existing, VisitorDTO dto) {

        if (Boolean.TRUE.equals(dto.getEntered())) {
            existing.setEntered(true);
        }

        if (dto.getEntryDate() != null &&
                (existing.getEntryDate() == null ||
                        dto.getEntryDate().isBefore(existing.getEntryDate()))) {

            existing.setEntryDate(dto.getEntryDate());
        }

        if (dto.getExitDate() != null &&
                (existing.getExitDate() == null ||
                        dto.getExitDate().isAfter(existing.getExitDate()))) {

            existing.setExitDate(dto.getExitDate());
        }

        return repository.save(existing);
    }
}
