package com.metrodata.edutrack.controllers;

import com.metrodata.edutrack.entities.Event;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.services.EventService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event")
public class EventController {
    private final EventService eventService;
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public ResponseData<Event> insertEvent(@RequestBody Event event) {
        return eventService.insertEvent(event);
    }

    @PatchMapping("{id}")
    public ResponseData<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("{id}")
    public ResponseData<Event> deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }
}
