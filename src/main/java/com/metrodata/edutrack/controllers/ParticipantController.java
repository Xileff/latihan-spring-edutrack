package com.metrodata.edutrack.controllers;

import com.metrodata.edutrack.entities.Participant;
import com.metrodata.edutrack.entities.models.ParticipantData;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.services.ParticipantService;
import org.apache.tomcat.util.modeler.ParameterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("participant")
public class ParticipantController {
    private final ParticipantService participantService;

    @Autowired
    public ParticipantController (ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public List<Participant> getParticipants() {
        return participantService.getParticipants();
    }

    @GetMapping("{id}")
    public Participant getParticipantById(@PathVariable Long id) {
        return participantService.getParticipantById(id);
    }

    @PostMapping
    public ResponseData<Participant> insertParticipant(@RequestBody ParticipantData participantData) {
        System.out.println("ERRORNYA DI SINI");
        return participantService.insertParticipant(participantData);
    }

    @PatchMapping("{id}")
    public ResponseData<Participant> updateParticipant(@PathVariable Long id, @RequestBody ParticipantData participantData) {
        return participantService.updateParticipant(id, participantData);
    }

    @DeleteMapping("{id}")
    public ResponseData<Participant> deleteParticipant(@PathVariable Long id) {
        return participantService.deleteParticipant(id);
    }
}
