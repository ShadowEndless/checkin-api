package com.shadowendless.checkin.api.api.Eventos.DTO;


import java.util.List;

public class SyncRequest{

    private EventDTO event;
    private List<VisitorDTO> visitors;

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public List<VisitorDTO> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<VisitorDTO> visitors) {
        this.visitors = visitors;
    }
}
