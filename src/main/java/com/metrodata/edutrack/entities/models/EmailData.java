package com.metrodata.edutrack.entities.models;

import lombok.Data;

@Data
public class EmailData {
    private String to, subject, body;
}
