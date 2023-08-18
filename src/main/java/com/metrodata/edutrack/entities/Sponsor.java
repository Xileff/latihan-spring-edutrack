package com.metrodata.edutrack.entities;


import com.metrodata.edutrack.entities.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "tb_m_sponsors")
@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UniqueID")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "logo_url", columnDefinition = "TEXT", nullable = false)
    private String logoUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL)
    private List<Speaker> speakers;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "UniqueID")
    private Event event;
}
