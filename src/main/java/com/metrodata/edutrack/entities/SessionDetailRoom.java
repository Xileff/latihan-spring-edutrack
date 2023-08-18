package com.metrodata.edutrack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tb_tr_session_detail_rooms")
@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class SessionDetailRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "session_detail_id", referencedColumnName = "id")
    private SessionDetail sessionDetail;
}
