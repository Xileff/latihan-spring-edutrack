package com.metrodata.edutrack.controllers;

import com.metrodata.edutrack.entities.Session;
import com.metrodata.edutrack.entities.models.ResponseData;
import com.metrodata.edutrack.entities.models.SessionData;
import com.metrodata.edutrack.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("session")
public class SessionController {

    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseData<Session> insertSession(@RequestBody SessionData sessionData) {
        return sessionService.insertSession(sessionData);
    }
}
