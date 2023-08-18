package com.metrodata.edutrack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tb_tr_session_speakers")
@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class SessionSpeaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "speaker_id", referencedColumnName = "id")
    private Speaker speaker;

    @ManyToOne
    @JoinColumn(name = "session_detail_id", referencedColumnName = "id")
    private SessionDetail sessionDetail;
}
