package com.metrodata.edutrack.controllers;

import com.metrodata.edutrack.entities.Speaker;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.entities.models.SpeakerData;
import com.metrodata.edutrack.services.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("speaker")
public class SpeakerController {
    private final SpeakerService speakerService;

    @Autowired
    public SpeakerController (SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping
    public List<Speaker> getSpeakers() {
        return speakerService.getSpeakers();
    }

    @GetMapping("{id}")
    public Speaker getSpeakerById(@PathVariable Long id) {
        return speakerService.getSpeakerById(id);
    }

    @PostMapping
    public ResponseData<Speaker> insertSpeaker(@RequestBody SpeakerData speakerData) {
        return speakerService.insertSpeaker(speakerData);
    }

    @PatchMapping("{id}")
    public ResponseData<Speaker> updateSpeaker(@PathVariable Long id, @RequestBody SpeakerData speakerData) {
        return speakerService.updateSpeaker(id, speakerData);
    }

    @DeleteMapping("{id}")
    public ResponseData<Speaker> deleteSpeaker(@PathVariable Long id) {
        return speakerService.deleteSpeaker(id);
    }
}
