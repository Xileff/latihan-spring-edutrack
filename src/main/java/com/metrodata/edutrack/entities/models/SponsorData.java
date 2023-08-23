package com.metrodata.edutrack.entities.models;

import com.metrodata.edutrack.entities.enums.SponsorCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class SponsorData {
    private Long id;
    private String name;
    private String logoUrl;

    @Enumerated(EnumType.STRING)
    private SponsorCategory sponsorCategory;

    private Long eventId;
}
