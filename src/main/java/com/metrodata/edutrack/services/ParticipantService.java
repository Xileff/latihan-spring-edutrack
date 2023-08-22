package com.metrodata.edutrack.services;

import com.metrodata.edutrack.entities.Participant;
import com.metrodata.edutrack.entities.models.ParticipantData;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final EventService eventService;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository, EventService eventService) {
        this.participantRepository = participantRepository;
        this.eventService = eventService;
    }

    public List<Participant> getParticipants() {
        return participantRepository.findAll();
    }

    public Participant getParticipantById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant with ID : " + id + "not found."));
    }

    public ResponseData<Participant> insertParticipant(ParticipantData participantData) {
        try {
            Participant participant = new Participant();
            participant.setName(participantData.getName());
            participant.setEmail(participantData.getEmail());
            participant.setPhoneNumber(participantData.getPhoneNumber());
            participant.setUniversity(participantData.getUniversity());
            participant.setAddress(participantData.getAddress());
            participant.setOccupation(participantData.getOccupation());
            participant.setEvent(eventService.getEventById(participantData.getEventId()));
            return new ResponseData<>(participantRepository.save(participant), "Participant inserted successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<Participant> updateParticipant(Long id, ParticipantData participantData) {
        try {
            Participant participant = getParticipantById(id);
            participant.setName(participantData.getName());
            participant.setEmail(participantData.getEmail());
            participant.setPhoneNumber(participantData.getPhoneNumber());
            participant.setUniversity(participantData.getUniversity());
            participant.setAddress(participantData.getAddress());
            participant.setOccupation(participantData.getOccupation());
            participant.setEvent(eventService.getEventById(participantData.getEventId()));
            return new ResponseData<>(participantRepository.save(participant), "Participant inserted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<Participant> deleteParticipant(Long id) {
        try {
            Participant participant = getParticipantById(id);
            participantRepository.delete(participant);
            return new ResponseData<>(participant, "Participant with ID : " + id + " deleted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
