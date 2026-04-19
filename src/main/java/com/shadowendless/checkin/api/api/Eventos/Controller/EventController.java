package com.shadowendless.checkin.api.api.Eventos.Controller;


import com.shadowendless.checkin.api.api.Eventos.DTO.SyncRequest;
import com.shadowendless.checkin.api.api.Eventos.Entity.Event;
import com.shadowendless.checkin.api.api.Eventos.Entity.Visitor;
import com.shadowendless.checkin.api.api.Eventos.Repository.EventRepository;
import com.shadowendless.checkin.api.api.Eventos.Service.EventService;
import com.shadowendless.checkin.api.api.Eventos.Service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping({"/events"})
public class EventController {

    @GetMapping
    public String introducao(){
        return "<H1>hello world put two jsons inside each other</H1>" +
                "guide:<br>" +
                "{\n" +
                "  \"event\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Evento X\",\n" +
                "    \"location\": \"Local X\",\n" +
                "    \"startDate\": \"2026-04-15T10:00:00\",\n" +
                "    \"endDate\": \"2026-04-15T18:00:00\"\n" +
                "  },\n" +
                "  \"visitors\": [\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"name\": \"João\",\n" +
                "      \"entered\": true,\n" +
                "      \"eventId\": 1,\n" +
                "      \"eventName\": \"Evento X\",\n" +
                "      \"entryDate\": \"2026-04-15T10:10:00\",\n" +
                "      \"exitDate\": \"2026-04-15T12:00:00\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    private final EventService eventService;
    private final VisitorService visitorService;

    public EventController(EventService eventService, VisitorService visitorService) {
        this.eventService = eventService;
        this.visitorService = visitorService;
    }


    @PostMapping("/sync")
    public String sync(@RequestBody SyncRequest request) {

        var event = eventService.findOrCreate(request.getEvent());

        request.getVisitors().forEach(v ->
                visitorService.merge(v, event)
        );

        return "OK!!!";
    }

}
