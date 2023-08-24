package com.metrodata.edutrack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "tb_m_session_details")
@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class SessionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(mappedBy = "sessionDetail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private CertificateTemplate certificateTemplate;

    @OneToMany(mappedBy = "sessionDetail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<SessionDetailRoom> sessionDetailRooms;

    @OneToMany(mappedBy = "sessionDetail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<SessionSpeaker> sessionSpeakers;

    @OneToMany(mappedBy = "sessionDetail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<SessionRegistrant> sessionRegistrants;

    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    @JsonBackReference
    private Session session;
}
