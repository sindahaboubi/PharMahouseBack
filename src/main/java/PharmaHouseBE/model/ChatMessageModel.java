package PharmaHouseBE.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
public class ChatMessageModel {
    private String user;
    private String message;
    private String roomId;



    // Constructeur avec paramètres
    public ChatMessageModel(String user, String message, String roomId) {
        this.user = user;
        this.message = message;
        this.roomId = roomId;
    }
    // Constructeur sans arguments
    public ChatMessageModel() {

    }

    // Getters et setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}