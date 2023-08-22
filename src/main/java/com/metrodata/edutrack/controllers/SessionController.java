package com.metrodata.edutrack.controllers;

import com.metrodata.edutrack.entities.Session;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.entities.models.SessionData;
import com.metrodata.edutrack.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("session")
public class SessionController {
    private final SessionService sessionService;
    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<Session> getSessions() {
        return sessionService.getSessions();
    }

    @GetMapping("{id}")
    public Session getSessionById(@PathVariable Long id) {
        return sessionService.getSessionById(id);
    }

    @PostMapping
    public ResponseData<Session> insertSession(@RequestBody SessionData sessionData) {
        return sessionService.insertSession(sessionData);
    }

    @PatchMapping("{id}")
    public ResponseData<Session> updateSession(@PathVariable Long id, @RequestBody SessionData sessionData) {
        return sessionService.updateSession(id, sessionData);
    }

    @DeleteMapping("{id}")
    public ResponseData<Session> deleteSession(@PathVariable Long id) {
        return sessionService.deleteSession(id);
    };
}
