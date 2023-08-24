package com.metrodata.edutrack.services;

import com.metrodata.edutrack.entities.models.LoginData;
import com.metrodata.edutrack.entities.models.LoginResponse;
import com.metrodata.edutrack.utilities.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate;

    public Map<String, Object> login(LoginData loginData, HttpServletResponse response) {
        LoginResponse loginResponse = restTemplate.postForObject("https://dev.services.metrodataacademy.id/metrodataacademy/user/login", loginData, LoginResponse.class);
        Map<String, Object> result = new LinkedHashMap<>();

        if (loginResponse.getToken() != null && !loginResponse.getToken().isEmpty()) {
            String subject = jwtUtil.extractUsername(loginResponse.getToken());
            String newAccessToken = jwtUtil.generateToken(loginResponse.getToken(), subject);
            String refreshToken = jwtUtil.generateRefreshToken(loginResponse.getToken(), subject);
            setRefreshTokenToCookie(refreshToken, response);

            result.put("token", newAccessToken);
            result.put("exp", new Date(System.currentTimeMillis() + 604800000));
        } else {
            result.put("status", loginResponse.getStatus());
        }
        return result;
    }

    public void setRefreshTokenToCookie(String token, HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", token)
                .maxAge(604800)
                .sameSite("None")
                .secure(true)
                .path("/")
                .httpOnly(true)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
