package com.metrodata.edutrack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Table(name = "tb_m_sessions")
@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UniqueID")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "need_attendance", nullable = false)
    private Boolean needAttendance;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "UniqueID")
    private Event event;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<SessionDetail> sessionDetails;


}
