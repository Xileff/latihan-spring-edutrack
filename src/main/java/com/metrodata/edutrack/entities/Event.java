package com.metrodata.edutrack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Table(name = "tb_m_events")
@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UniqueID")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String slug;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "start_registration", nullable = false)
    private LocalDateTime startRegistration;

    @Column(name = "close_registration", nullable = false)
    private LocalDateTime closeRegistration;

    @Column(nullable = false)
    private int capacity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String location;

    @Column(name = "image_url", columnDefinition = "TEXT", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Sponsor> sponsors;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Participant> participants;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Session> sessions;
}
