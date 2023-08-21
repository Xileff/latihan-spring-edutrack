package com.metrodata.edutrack.entities.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.metrodata.edutrack.entities.enums.SessionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalTime;

@Data
public class SessionData {
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;

    private String description;
    private Boolean needAttendance;

    @Enumerated(EnumType.STRING)
    private SessionType type;

    private Long eventId;
}
