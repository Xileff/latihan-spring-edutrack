package com.metrodata.edutrack.entities.models;

import lombok.Data;

@Data
public class SessionDetailData {
    private String name;
    private Integer capacity;
    private String description;
    private Long sessionId;
    private String certificateUrl;
}
