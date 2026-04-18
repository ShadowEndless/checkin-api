package com.shadowendless.checkin.api.api.Eventos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "visitors",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "event_id"})
        }
)
@Getter
@Setter
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean entered;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @Column(name = "exit_date")
    private LocalDateTime exitDate;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
