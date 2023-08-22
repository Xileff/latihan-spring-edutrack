package com.metrodata.edutrack.controllers;

import com.metrodata.edutrack.entities.Sponsor;
import com.metrodata.edutrack.entities.enums.SponsorCategory;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.entities.models.SponsorData;
import com.metrodata.edutrack.services.SponsorService;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sponsor")
public class SponsorController {
    private final SponsorService sponsorService;

    @Autowired
    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping
    public List<Sponsor> getSponsors() {
        return sponsorService.getSponsors();
    }

    @GetMapping("{id}")
    public Sponsor getSponsorById(@PathVariable Long id) {
        return sponsorService.getSponsorById(id);
    }

    @PostMapping
    public ResponseData<Sponsor> insertSponsor(@RequestBody SponsorData sponsorData) {
        return sponsorService.insertSponsor(sponsorData);
    }

    @PatchMapping("{id}")
    public ResponseData<Sponsor> updateSponsor(@PathVariable Long id, @RequestBody SponsorData sponsorData) {
        return sponsorService.updateSponsor(id, sponsorData);
    }

    @DeleteMapping("{id}")
    public ResponseData<Sponsor> deleteSponsor(@PathVariable Long id) {
        return sponsorService.deleteSponsor(id);
    }
}
