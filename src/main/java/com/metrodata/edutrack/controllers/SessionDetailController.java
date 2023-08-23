package com.metrodata.edutrack.controllers;

import com.metrodata.edutrack.entities.SessionDetail;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.entities.models.SessionDetailData;
import com.metrodata.edutrack.services.SessionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("session-detail")
public class SessionDetailController {
    private final SessionDetailService sessionDetailService;

    @Autowired
    public SessionDetailController (SessionDetailService sessionDetailService) {
        this.sessionDetailService = sessionDetailService;
    }

    @GetMapping
    public List<SessionDetailData> getSessionDetails() {
        return sessionDetailService.getSessionDetails();
    }

    @GetMapping("{id}")
    public SessionDetailData getSessionDetailById(@PathVariable Long id) {
        return sessionDetailService.getSessionDetailDTOById(id);
    }

    @PostMapping
    public ResponseData<SessionDetailData> insertSessionDetail(@RequestBody SessionDetailData sessionDetailData) {
        return sessionDetailService.insertSessionDetail(sessionDetailData);
    }

    @PatchMapping("{id}")
    public ResponseData<SessionDetailData> updateSessionDetail(@PathVariable Long id, @RequestBody SessionDetailData sessionDetailData) {
        return sessionDetailService.updateSessionDetail(id, sessionDetailData);
    }

    @DeleteMapping("{id}")
    public ResponseData<SessionDetail> deleteSessionDetail(@PathVariable Long id) {
        return sessionDetailService.deleteSessionDetail(id);
    }
}
