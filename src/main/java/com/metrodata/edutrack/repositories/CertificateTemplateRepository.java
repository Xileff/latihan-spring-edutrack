package com.metrodata.edutrack.repositories;

import com.metrodata.edutrack.entities.CertificateTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateTemplateRepository extends JpaRepository<CertificateTemplate, Long> {
//    Query method
    CertificateTemplate findTemplateById(Long id);

//    Custom Query JPQL
    @Query("SELECT c FROM CertificateTemplate c WHERE c.id = ?1")
    CertificateTemplate getTemplateById(Long id);

//    Custom Query SQL
    @Query(value = "SELECT * FROM tb_m_certificate_templates WHERE id = ?1", nativeQuery = true)
    CertificateTemplate getTemplateByIdNative(Long id);
}
