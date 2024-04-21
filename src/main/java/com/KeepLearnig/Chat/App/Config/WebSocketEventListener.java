package com.KeepLearnig.Chat.App.Config;

import com.KeepLearnig.Chat.App.chat.ChatMessage;
import com.KeepLearnig.Chat.App.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageTemplate;
    @EventListener
    public void handleWebSocketDisconnectEventListener(SessionDisconnectEvent event){
//        TO do
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username= (String) headerAccessor.getSessionAttributes().get("username");
        if(username!=null){
            log.info("user disconnected", username);
            var chatMessage= ChatMessage.builder().messageType(MessageType.LEAVER)
                    .message(username).build();
           messageTemplate.convertAndSend("/topic/public",chatMessage);

        }
    }
}
