package com.shadowendless.checkin.api.api.Eventos.Entity;

import com.shadowendless.checkin.api.api.Eventos.DTO.EventDTO;
import com.shadowendless.checkin.api.api.Eventos.Entity.Event;
import com.shadowendless.checkin.api.api.Eventos.Service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class EventServiceIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15-alpine")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private EventService eventService;

    @Test
    void shouldCreateEventInRealDatabase() {

        EventDTO dto = new EventDTO();
        dto.setName("Evento Teste");
        dto.setLocation("Local Teste");
        dto.setStartDate(LocalDateTime.now());

        Event event = eventService.findOrCreate(dto);

        assertNotNull(event.getId());
    }
}
