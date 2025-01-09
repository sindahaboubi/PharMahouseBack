package PharmaHouseBE.services.Implementation;

import PharmaHouseBE.configuration.PaymentRequest;
import PharmaHouseBE.configuration.PaymentResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymeeService {

    private final String apiKey = "46c755955f7ff6a4b19e87e87712e019c9b330af";
    private final String paymeeUrl = "https://sandbox.paymee.tn/api/v2/payments/create";

    public String createPayment(PaymentRequest paymentRequest) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Token " + apiKey);

        HttpEntity<PaymentRequest> request = new HttpEntity<>(paymentRequest, headers);

        ResponseEntity<PaymentResponse> response = restTemplate.postForEntity(paymeeUrl, request, PaymentResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getData().getPaymentUrl();
        } else {
            throw new RuntimeException("Failed to create payment");
        }
    }
}
