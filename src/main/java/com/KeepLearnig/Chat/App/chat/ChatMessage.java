package com.KeepLearnig.Chat.App.chat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    private String content;
    private String message;
    private MessageType messageType;
}
