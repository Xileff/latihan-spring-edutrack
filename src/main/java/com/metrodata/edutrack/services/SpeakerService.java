package com.metrodata.edutrack.services;

import com.metrodata.edutrack.entities.Speaker;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.entities.models.SpeakerData;
import com.metrodata.edutrack.repositories.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeakerService {
    private final SpeakerRepository speakerRepository;
    private final SponsorService sponsorService;

    @Autowired
    public SpeakerService(SpeakerRepository speakerRepository, SponsorService sponsorService) {
        this.speakerRepository = speakerRepository;
        this.sponsorService = sponsorService;
    }

    public List<SpeakerData> getSpeakers() {
        return speakerRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Speaker getSpeakerById(Long id) {
        return speakerRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Speaker with ID : " + id + " not found."));
    }

    public SpeakerData getSpeakerDTOById(Long id) {
        Speaker speaker = getSpeakerById(id);
        return convertToDTO(speaker);
    }

    public ResponseData<Speaker> insertSpeaker(SpeakerData speakerData) {
        try {
            Speaker speaker = new Speaker();
            speaker.setName(speakerData.getName());
            speaker.setPhotoUrl(speakerData.getPhotoUrl());
            speaker.setJobTitle(speakerData.getJobTitle());
            speaker.setCompany(speakerData.getCompany());
            speaker.setSponsor(sponsorService.getSponsorById(speakerData.getSponsorId()));
            return new ResponseData<>(speakerRepository.save(speaker), "Speaker inserted successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<Speaker> updateSpeaker(Long id, SpeakerData speakerData) {
        try {
            Speaker speaker = getSpeakerById(id);
            speaker.setName(speakerData.getName());
            speaker.setPhotoUrl(speakerData.getPhotoUrl());
            speaker.setJobTitle(speakerData.getJobTitle());
            speaker.setCompany(speakerData.getCompany());
            speaker.setSponsor(sponsorService.getSponsorById(speakerData.getSponsorId()));
            return new ResponseData<>(speakerRepository.save(speaker), "Speaker inserted successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<Speaker> deleteSpeaker(Long id) {
        try {
            Speaker speaker = getSpeakerById(id);
            speakerRepository.delete(speaker);
            return new ResponseData<>(speaker, "Speaker with ID : " + id + " deleted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private SpeakerData convertToDTO(Speaker speaker) {
        SpeakerData speakerData = new SpeakerData();
        speakerData.setId(speaker.getId());
        speakerData.setName(speaker.getName());
        speakerData.setJobTitle(speaker.getJobTitle());
        speakerData.setCompany(speaker.getCompany());
        speakerData.setPhotoUrl(speaker.getPhotoUrl());
        speakerData.setSponsorId(speaker.getSponsor().getId());
        return speakerData;
    }
}
