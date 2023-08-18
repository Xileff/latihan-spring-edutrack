package com.metrodata.edutrack.entities;

import com.metrodata.edutrack.entities.enums.Status;
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
    @Column(name = "UniqueID")
    private Long id;

    @Column(name = "attended_at")
    private LocalDateTime attendedAt;

    @Column(name = "is_attended")
    private Boolean isAttended;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    @Column(name = "is_reminder_sent", nullable = false)
    private Boolean isReminderSent;

    @ManyToOne
    @JoinColumn(name = "participant_id", referencedColumnName = "UniqueID")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "session_detail_id", referencedColumnName = "UniqueID")
    private SessionDetail sessionDetail;
}
