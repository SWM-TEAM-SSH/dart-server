package com.ssh.dartserver.domain.chat.dto;

import lombok.Data;
@Data
public class ChatMessageRequest {
    private String chatMessageType;
    private Long chatRoomId;
    private Long senderId; // 발신자
    private String content;
}
