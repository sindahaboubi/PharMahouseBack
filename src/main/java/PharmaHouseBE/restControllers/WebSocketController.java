package PharmaHouseBE.restControllers;

import PharmaHouseBE.dto.ChatMessage;
import PharmaHouseBE.model.ChatMessageModel;
import PharmaHouseBE.services.Implementation.ChatSocketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@CrossOrigin("*")
public class WebSocketController {


    @Autowired
    private ChatSocketServiceImpl chatSocketService;

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message) {
        System.out.println(message);

        ChatMessageModel chatMessageModel = new ChatMessageModel();
        chatMessageModel.setUser(message.getUser());
        chatMessageModel.setMessage(message.getMessage());
        chatMessageModel.setRoomId(roomId);

        chatSocketService.save(chatMessageModel);

        return new ChatMessage(message.getMessage(), message.getUser());
    }

@GetMapping("/api/chat/{roomId}")
public ResponseEntity<List<ChatMessageModel>> getAllChatMessages(@PathVariable String roomId) {
    List<ChatMessageModel> result = chatSocketService.findByRoomId(roomId);
    return ResponseEntity.ok(result);
}
}
