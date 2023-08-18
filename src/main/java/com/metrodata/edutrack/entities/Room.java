package com.metrodata.edutrack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "tb_m_rooms")
@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<SessionDetailRoom> sessionDetailRooms;
}
