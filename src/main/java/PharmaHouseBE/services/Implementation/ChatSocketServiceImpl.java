package PharmaHouseBE.services.Implementation;

import PharmaHouseBE.dto.ChatMessage;
import PharmaHouseBE.model.ChatMessageModel;
import PharmaHouseBE.services.Interfaces.IChatSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class ChatSocketServiceImpl implements IChatSocket {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(ChatMessageModel chatMessageModel) {
        String sql = "INSERT INTO chats (user_name, message, room_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, chatMessageModel.getUser(), chatMessageModel.getMessage(), chatMessageModel.getRoomId());
    }


    @Override
    public List<ChatMessageModel> findByRoomId(String roomId) {
        String SQL = "SELECT * FROM chats WHERE room_id = ?";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(
                ChatMessageModel.class
        ), roomId);
    }

}
