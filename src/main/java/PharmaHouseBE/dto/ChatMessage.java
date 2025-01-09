package PharmaHouseBE.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data

public class ChatMessage {
    private String user;
    private String message;

    // Constructeur sans arguments
    public ChatMessage() {}

    // Constructeur avec paramètres
    public ChatMessage(String message, String user) {
        this.message = message;
        this.user = user;
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


    public void setRoomId(String roomId) {
        this.user = roomId;
    }
}
