package com.metrodata.edutrack.services;

import com.metrodata.edutrack.entities.Event;
import com.metrodata.edutrack.entities.models.EventData;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventData> getEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event with ID : " + id + " not found"));
    }

    public EventData getEventDTOById(Long id) {
        Event event = getEventById(id);
        return convertToDTO(event);
    }

    public ResponseData<Event> insertEvent(EventData eventData) {
        try {
            Event event = new Event();
            event.setName(eventData.getName());
            event.setSlug(eventData.getSlug());
            event.setStartDate(eventData.getStartDate());
            event.setStartTime(eventData.getStartTime());
            event.setEndDate(eventData.getEndDate());
            event.setStartRegistration(eventData.getStartRegistration());
            event.setCloseRegistration(eventData.getCloseRegistration());
            event.setCapacity(eventData.getCapacity());
            event.setDescription(eventData.getDescription());
            event.setLocation(eventData.getLocation());
            event.setImageUrl(eventData.getImageUrl());
            event.setIsPublished(eventData.getIsPublished());
            return new ResponseData<>(eventRepository.save(event), "Event inserted successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<Event> updateEvent(Long id, EventData eventData) {
        try {
            Event event = getEventById(id);
            event.setName(eventData.getName());
            event.setSlug(eventData.getSlug());
            event.setStartDate(eventData.getStartDate());
            event.setStartTime(eventData.getStartTime());
            event.setEndDate(eventData.getEndDate());
            event.setStartRegistration(eventData.getStartRegistration());
            event.setCloseRegistration(eventData.getCloseRegistration());
            event.setCapacity(eventData.getCapacity());
            event.setDescription(eventData.getDescription());
            event.setLocation(eventData.getLocation());
            event.setImageUrl(eventData.getImageUrl());
            event.setIsPublished(eventData.getIsPublished());
            return new ResponseData<>(eventRepository.save(event), "Successfully updated Event with ID : " +  id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<Event> deleteEvent(Long id) {
        try {
            Event event = getEventById(id);
            eventRepository.delete(event);
            return new ResponseData<>(event, "Successfully deleted Event with ID : " + id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private EventData convertToDTO(Event event) {
        EventData eventData = new EventData();
        eventData.setId(event.getId());
        eventData.setName(event.getName());
        eventData.setSlug(event.getSlug());
        eventData.setStartTime(event.getStartTime());
        eventData.setStartDate(event.getStartDate());
        eventData.setEndDate(event.getEndDate());
        eventData.setStartRegistration(event.getStartRegistration());
        eventData.setCloseRegistration(event.getCloseRegistration());
        eventData.setCapacity(event.getCapacity());
        eventData.setDescription(event.getDescription());
        eventData.setLocation(event.getLocation());
        eventData.setImageUrl(event.getImageUrl());
        eventData.setIsPublished(event.getIsPublished());

        return eventData;
    }
}
