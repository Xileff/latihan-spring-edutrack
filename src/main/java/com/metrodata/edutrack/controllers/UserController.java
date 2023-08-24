package com.metrodata.edutrack.controllers;

import com.metrodata.edutrack.entities.models.LoginData;
import com.metrodata.edutrack.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public Map<String, Object> login(@RequestBody LoginData loginData, HttpServletResponse response) {
        return userService.login(loginData, response);
    }
}
