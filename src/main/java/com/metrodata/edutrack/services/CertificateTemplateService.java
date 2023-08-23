package com.metrodata.edutrack.services;

import com.metrodata.edutrack.entities.CertificateTemplate;
import com.metrodata.edutrack.entities.models.CertificateTemplateData;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.repositories.CertificateTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificateTemplateService {
    private final CertificateTemplateRepository certificateTemplateRepository;

    @Autowired
    public CertificateTemplateService (CertificateTemplateRepository certificateTemplateRepository) {
        this.certificateTemplateRepository = certificateTemplateRepository;
    }

    public List<CertificateTemplateData> getCertificateTemplates() {
        List<CertificateTemplate> certificateTemplates =  certificateTemplateRepository.findAll();
        return certificateTemplates.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CertificateTemplate getCertificateTemplateById(Long id) {
        return certificateTemplateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Certificate Template with ID : " + id + " not found"));
    }

    public CertificateTemplateData getCertificateTemplateDTOById(Long id) {
        CertificateTemplate certificateTemplate = getCertificateTemplateById(id);
        return convertToDTO(certificateTemplate);
    }

    public ResponseData<CertificateTemplate> insertCertificateTemplate(CertificateTemplateData certificateTemplateData) {
        try {
            CertificateTemplate certificateTemplate = new CertificateTemplate();
            certificateTemplate.setCertificateUrl(certificateTemplateData.getCertificateUrl());
            return new ResponseData<>(certificateTemplateRepository.save(certificateTemplate),
                    "Certificate Template inserted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<CertificateTemplate> updateCertificateTemplate(Long id, CertificateTemplateData certificateTemplateData) {
        try {
            CertificateTemplate certificateTemplate = getCertificateTemplateById(id);
            certificateTemplate.setCertificateUrl(certificateTemplateData.getCertificateUrl());
            return new ResponseData<>(certificateTemplateRepository.save(certificateTemplate),
                    "Certificate Template with ID : " + id + " updated successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<CertificateTemplate> deleteCertificateTemplate(Long id) {
        try {
            CertificateTemplate certificateTemplate = getCertificateTemplateById(id);
            certificateTemplateRepository.delete(certificateTemplate);
            return new ResponseData<>(certificateTemplate,
                    "Certificate Template with ID : " + id + " deleted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private CertificateTemplateData convertToDTO(CertificateTemplate certificateTemplate) {
        CertificateTemplateData certificateTemplateData = new CertificateTemplateData();
        certificateTemplateData.setId(certificateTemplate.getId());
        certificateTemplateData.setCertificateUrl(certificateTemplate.getCertificateUrl());
        return certificateTemplateData;
    }
}
