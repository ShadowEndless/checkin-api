package com.shadowendless.checkin.api.api.Eventos.DTO;

import java.time.LocalDateTime;

public class VisitorDTO {

    private Long id;
    private String name;
    private Boolean entered;
    private Long eventId;
    private String eventName;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getEntered() {
        return entered;
    }

    public Long getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEntered(Boolean entered) {
        this.entered = entered;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }
}