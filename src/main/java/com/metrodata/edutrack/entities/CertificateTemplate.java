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
    @Column(name = "UniqueID")
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String certificate_url;

    @OneToOne
    @MapsId
    @JoinColumn(name = "UniqueID", nullable = false)
    private SessionDetail sessionDetail;
}
