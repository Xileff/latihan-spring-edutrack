package com.metrodata.edutrack.services;

import com.metrodata.edutrack.controllers.SessionDetailController;
import com.metrodata.edutrack.entities.CertificateTemplate;
import com.metrodata.edutrack.entities.SessionDetail;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.entities.models.SessionDetailData;
import com.metrodata.edutrack.repositories.SessionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionDetailService {
    private final SessionDetailRepository sessionDetailRepository;
    private final SessionService sessionService;
    @Autowired
    public SessionDetailService (SessionDetailRepository sessionDetailRepository, SessionService sessionService) {
        this.sessionDetailRepository = sessionDetailRepository;
        this.sessionService = sessionService;
    }

    public List<SessionDetailData> getSessionDetails() {
        return sessionDetailRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public SessionDetail getSessionDetailById(Long id) {
        return sessionDetailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Session with ID : " + id + " not found."));
    }

    public SessionDetailData getSessionDetailDTOById(Long id) {
        SessionDetail sessionDetail = getSessionDetailById(id);
        return convertToDTO(sessionDetail);
    }

    public ResponseData<SessionDetailData> insertSessionDetail(SessionDetailData sessionDetailData) {
        try {
            SessionDetail sessionDetail = new SessionDetail();
            sessionDetail.setName(sessionDetailData.getName());
            sessionDetail.setCapacity(sessionDetailData.getCapacity());
            sessionDetail.setDescription(sessionDetailData.getDescription());
            sessionDetail.setSession(sessionService.getSessionById(sessionDetailData.getSessionId()));

            CertificateTemplate certificateTemplate = new CertificateTemplate();
            certificateTemplate.setCertificateUrl(sessionDetailData.getCertificateUrl());
            certificateTemplate.setSessionDetail(sessionDetail);

            sessionDetail.setCertificateTemplate(certificateTemplate);
            sessionDetailRepository.save(sessionDetail);

            return new ResponseData<>(sessionDetailData, "Session inserted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<SessionDetailData> updateSessionDetail(Long id, SessionDetailData sessionDetailData) {
        try {
            SessionDetail sessionDetail = getSessionDetailById(id);
            sessionDetail.setName(sessionDetailData.getName());
            sessionDetail.setCapacity(sessionDetailData.getCapacity());
            sessionDetail.setDescription(sessionDetailData.getDescription());
            sessionDetail.setSession(sessionService.getSessionById(sessionDetailData.getSessionId()));

            sessionDetailRepository.save(sessionDetail);
            return new ResponseData<>(sessionDetailData, "Session with ID : " + id + "updated successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<SessionDetail> deleteSessionDetail(Long id) {
        try {
            SessionDetail sessionDetail = getSessionDetailById(id);
            sessionDetailRepository.delete(sessionDetail);
            return new ResponseData<>(sessionDetail, "Session with ID : " + id + " deleted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private SessionDetailData convertToDTO(SessionDetail sessionDetail) {
        SessionDetailData sessionDetailData = new SessionDetailData();
        sessionDetailData.setCapacity(sessionDetail.getCapacity());
        sessionDetailData.setSessionId(sessionDetail.getSession().getId());
        sessionDetailData.setName(sessionDetail.getName());
        sessionDetailData.setDescription(sessionDetail.getDescription());
        return sessionDetailData;
    }
}
