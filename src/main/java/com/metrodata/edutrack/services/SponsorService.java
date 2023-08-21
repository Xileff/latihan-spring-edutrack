package com.metrodata.edutrack.services;

import com.metrodata.edutrack.entities.Sponsor;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.entities.models.SponsorData;
import com.metrodata.edutrack.repositories.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SponsorService {
    private SponsorRepository sponsorRepository;
    private EventService eventService;

    @Autowired
    public SponsorService(SponsorRepository sponsorRepository, EventService eventService) {
        this.sponsorRepository = sponsorRepository;
        this.eventService = eventService;
    }

    public List<Sponsor> getSponsors() {
        return sponsorRepository.findAll();
    }

    public Sponsor getSponsorById(Long id) {
        return sponsorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sponsor with ID : " + id + " not found."));
    }

    public ResponseData<Sponsor> insertSponsor(SponsorData sponsorData) {
        try {
            Sponsor sponsor = new Sponsor();
            sponsor.setName(sponsorData.getName());
            sponsor.setLogoUrl(sponsorData.getLogoUrl());
            sponsor.setSponsorCategory(sponsorData.getSponsorCategory());
            sponsor.setEvent(eventService.getEventById(sponsorData.getEventId()));
            return new ResponseData<>(sponsorRepository.save(sponsor), "Sponsor inserted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<Sponsor> updateSponsor(Long id, SponsorData sponsorData) {
        try {
            Sponsor sponsor = getSponsorById(id);
            sponsor.setName(sponsorData.getName());
            sponsor.setLogoUrl(sponsorData.getLogoUrl());
            sponsor.setSponsorCategory(sponsorData.getSponsorCategory());
            sponsor.setEvent(eventService.getEventById(sponsorData.getEventId()));
            return new ResponseData<>(sponsorRepository.save(sponsor), "Sponsor inserted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<Sponsor> deleteSponsor(Long id) {
        try {
            Sponsor sponsor = getSponsorById(id);
            sponsorRepository.delete(sponsor);
            return new ResponseData<>(sponsor, "Sponsor with ID : " + id + " deleted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
