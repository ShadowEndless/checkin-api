package com.shadowendless.checkin.api.api.Eventos.Controller;


import com.shadowendless.checkin.api.api.Eventos.DTO.SyncRequest;
import com.shadowendless.checkin.api.api.Eventos.Entity.Event;
import com.shadowendless.checkin.api.api.Eventos.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping({"/eventos"})
public class ControllerEventos {

    @GetMapping
    public String introducao(){
        return "<H1>hello world put two jsons inside each other</H1>";
    }


    @PostMapping("/sync")
    public String sync(@RequestBody SyncRequest request) {

        System.out.println("===== EVENTO =====");
        System.out.println("Nome: " + request.getEvent().getName());
        System.out.println("Local: " + request.getEvent().getLocation());
        System.out.println("Inicio: " + request.getEvent().getStartDate());
        System.out.println("Fim: " + request.getEvent().getEndDate());

        System.out.println("===== VISITANTES =====");

        request.getVisitors().forEach(v -> {
            System.out.println("Nome: " + v.getName());
            System.out.println("Entrou: " + v.getEntered());
            System.out.println("Entrada: " + v.getEntryDate());
            System.out.println("Saida: " + v.getExitDate());
            System.out.println("-------------------");
        });

        return "Recebido com sucesso!!!";
    }

    @Autowired
    private EventRepository repository;

    @PostMapping("/test-event")
    public Event test() {
        Event e = new Event();
        e.setName("Evento Teste");
        e.setLocation("Local Teste");
        e.setStartDate(LocalDateTime.now());

        return repository.save(e);
    }

}
