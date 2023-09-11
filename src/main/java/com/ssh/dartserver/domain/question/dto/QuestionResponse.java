package com.ssh.dartserver.domain.question.dto;

import lombok.Data;

@Data
public class QuestionResponse {
    private Long questionId;
    private QuestionCategoryResponse category;
    private String content;
    private String icon;
    private String targetGender;
}
