package com.metrodata.edutrack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.metrodata.edutrack.entities.enums.Occupation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "tb_m_participants")
@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @JsonBackReference
    private Event event;

    @OneToMany(mappedBy = "participant", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<SessionRegistrant> sessionRegistrant;
}
