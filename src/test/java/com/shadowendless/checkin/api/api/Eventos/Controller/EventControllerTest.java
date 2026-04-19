package com.shadowendless.checkin.api.api.Eventos.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shadowendless.checkin.api.api.Eventos.DTO.SyncRequest;
import com.shadowendless.checkin.api.api.Eventos.Service.EventService;
import com.shadowendless.checkin.api.api.Eventos.Service.VisitorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EventService eventService;

    @MockitoBean
    private VisitorService visitorService;

    @Test
    void shouldReceiveJsonAndCallService() throws Exception {

        String json = """
        {
          "event": {
            "id": 1,
            "name": "Evento Xa",
            "location": "Local Xq",
            "startDate": "2026-04-15T10:00:00",
            "endDate": "2026-04-15T18:00:00"
          },
          "visitors": []
        }
        """;

        mockMvc.perform(post("/events/sync")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("OK!!!"));
    }
}