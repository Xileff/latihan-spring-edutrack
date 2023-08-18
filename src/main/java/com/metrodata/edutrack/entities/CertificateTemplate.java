package com.metrodata.edutrack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tb_m_certificate_templates")
@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class CertificateTemplate {
    @Id
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String certificate_url;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private SessionDetail sessionDetail;
}
