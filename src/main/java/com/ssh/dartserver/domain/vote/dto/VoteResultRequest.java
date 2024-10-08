package com.ssh.dartserver.domain.vote.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class VoteResultRequest {
    @NotNull(message = "질문 id는 null 일 수 없습니다")
    private Long questionId;
    @NotNull(message = "선택된 사용자 id는 null 일 수 없습니다")
    private Long pickedUserId;
    @Size(min = 4, max = 4, message = "후보자는 반드시 4명이어야 합니다.")
    private List<Long> candidateIds;
}

