package com.metrodata.edutrack.controllers;

import com.metrodata.edutrack.entities.CertificateTemplate;
import com.metrodata.edutrack.entities.models.CertificateTemplateData;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.services.CertificateTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("certificate-template")
public class CertificateTemplateController {
    private final CertificateTemplateService certificateTemplateService;

    @Autowired
    public CertificateTemplateController(CertificateTemplateService certificateTemplateService) {
        this.certificateTemplateService = certificateTemplateService;
    }

    @GetMapping
    public List<CertificateTemplateData> getCertificateTemplates() {
        return certificateTemplateService.getCertificateTemplates();
    }

    @GetMapping("{id}")
    public CertificateTemplateData getCertificateTemplateById(@PathVariable Long id) {
        return certificateTemplateService.getCertificateTemplateDTOById(id);
    }

    @PostMapping
    public ResponseData<CertificateTemplate> insertCertificateTemplate(@RequestBody CertificateTemplateData certificateTemplateData) {
        return certificateTemplateService.insertCertificateTemplate(certificateTemplateData);
    }

    @PatchMapping("{id}")
    public ResponseData<CertificateTemplate> updateCertificateTemplate(@PathVariable Long id, @RequestBody CertificateTemplateData certificateTemplateData) {
        return certificateTemplateService.updateCertificateTemplate(id, certificateTemplateData);
    }

    @DeleteMapping("{id}")
    public ResponseData<CertificateTemplate> deleteCertificateTemplate(@PathVariable Long id) {
        return certificateTemplateService.deleteCertificateTemplate(id);
    }
}
