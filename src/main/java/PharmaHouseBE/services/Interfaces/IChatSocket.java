package PharmaHouseBE.services.Interfaces;

import PharmaHouseBE.dto.ChatMessage;
import PharmaHouseBE.model.ChatMessageModel;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IChatSocket {


        void save(ChatMessageModel chatMessageModel);




    public List<ChatMessageModel> findByRoomId(String roomId);
}
