package com.metrodata.edutrack.entities.models;

import lombok.Data;

@Data
public class LoginResponse {
    private String exp, token, status;
}
