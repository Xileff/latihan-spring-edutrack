package com.metrodata.edutrack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name="certificate_url", nullable = false, columnDefinition = "TEXT")
    private String certificateUrl;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private SessionDetail sessionDetail;
}
