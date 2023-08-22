package com.metrodata.edutrack.services;

import com.metrodata.edutrack.entities.SessionDetail;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.entities.models.SessionDetailData;
import com.metrodata.edutrack.repositories.SessionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SessionDetailService {
    private final SessionDetailRepository sessionDetailRepository;
    private final SessionService sessionService;
    @Autowired
    public SessionDetailService (SessionDetailRepository sessionDetailRepository, SessionService sessionService) {
        this.sessionDetailRepository = sessionDetailRepository;
        this.sessionService = sessionService;
    }

    public List<SessionDetail> getSessionDetails() {
        return sessionDetailRepository.findAll();
    }

    public SessionDetail getSessionDetailById(Long id) {
        return sessionDetailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Session with ID : " + id + " not found."));
    }

    public ResponseData<SessionDetail> insertSessionDetail(SessionDetailData sessionDetailData) {
        try {
            SessionDetail sessionDetail = new SessionDetail();
            sessionDetail.setName(sessionDetailData.getName());
            sessionDetail.setCapacity(sessionDetailData.getCapacity());
            sessionDetail.setDescription(sessionDetailData.getDescription());
            sessionDetail.setSession(sessionService.getSessionById(sessionDetailData.getSessionId()));
            return new ResponseData<>(sessionDetailRepository.save(sessionDetail), "Session inserted successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseData<SessionDetail> updateSessionDetail(Long id, SessionDetailData sessionDetailData) {
        try {
            SessionDetail sessionDetail = getSessionDetailById(id);
            sessionDetail.setName(sessionDetailData.getName());
            sessionDetail.setCapacity(sessionDetailData.getCapacity());
            sessionDetail.setDescription(sessionDetailData.getDescription());
            sessionDetail.setSession(sessionService.getSessionById(sessionDetailData.getSessionId()));
            return new ResponseData<>(sessionDetailRepository.save(sessionDetail), "Session with ID : " + id + "updated successfully.");
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
}
