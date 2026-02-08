package com.studyEnglish.entity;

import lombok.Data;

@Data
public class Listen {
    private Integer listenId;
    private String listenName;
    private Integer grade;
    private String path;
    private String content;
}
