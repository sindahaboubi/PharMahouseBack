package PharmaHouseBE.configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentRequest {
    private Double amount;
    private String note;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String returnUrl;
    private String cancelUrl;
    private String webhookUrl;
    private String orderId;

    // Getters and setters
}
