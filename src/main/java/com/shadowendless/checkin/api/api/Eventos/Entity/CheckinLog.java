package com.shadowendless.checkin.api.api.Eventos.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "checkin_logs")
public class CheckinLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // Entrada ou saida. eu considero que é melhor usar string do que um int solto

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;
}
