package com.ssh.dartserver.domain.question.dto;

import lombok.Data;

@Data
public class QuestionCategoryResponse {
    private Long id;
    private String name;
    private String description;
    private String icon;
}
