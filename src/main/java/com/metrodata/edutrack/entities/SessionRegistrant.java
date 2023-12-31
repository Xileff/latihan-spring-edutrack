package com.metrodata.edutrack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.metrodata.edutrack.entities.enums.ParticipantStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "tb_tr_session_registrants")
@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class SessionRegistrant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attended_at")
    private LocalDateTime attendedAt;

    @Column(name = "is_attended")
    private Boolean isAttended;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParticipantStatus participantStatus;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    @Column(name = "is_reminder_sent", nullable = false)
    private Boolean isReminderSent;

    @ManyToOne
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    @JsonBackReference
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "session_detail_id", referencedColumnName = "id")
    @JsonBackReference
    private SessionDetail sessionDetail;
}
