package com.metrodata.edutrack.entities;

import com.metrodata.edutrack.entities.enums.Occupation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tb_m_participants")
@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UniqueID")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String university;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Occupation occupation;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "UniqueID")
    private Event event;

    @OneToOne(mappedBy = "participant")
    private SessionRegistrant sessionRegistrant;
}
