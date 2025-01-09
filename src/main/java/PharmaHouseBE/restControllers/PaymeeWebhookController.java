package PharmaHouseBE.restControllers;

import PharmaHouseBE.configuration.PaymentStatus;
import PharmaHouseBE.entities.Commande;
import PharmaHouseBE.entities.Medicament;
import PharmaHouseBE.entities.Payment;
import PharmaHouseBE.repositories.CommandeRepository;
import PharmaHouseBE.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import PharmaHouseBE.configuration.CheckSumUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/paymee-webhook")
public class PaymeeWebhookController {

    private final String apiKey = "46c755955f7ff6a4b19e87e87712e019c9b330af";

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CommandeRepository commandeRepository;


    @PostMapping("/calculate-checksum")
    public ResponseEntity<Map<String, String>> calculateChecksum(@RequestBody PaymentStatus paymentStatus) {
        String token = paymentStatus.getToken();
        boolean paymentStatusValue = paymentStatus.isPayment_status();

        // Calculate checksum
        String calculatedCheckSum = CheckSumUtil.calculateCheckSum(token, paymentStatusValue, apiKey);

        // Return the checksum in a JSON object
        Map<String, String> response = new HashMap<>();
        response.put("checksum", calculatedCheckSum);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/handle")
    public ResponseEntity<Map<String, Object>> handlePaymentWebhook(@RequestBody PaymentStatus paymentStatus) {
        String token = paymentStatus.getToken();
        String paymentStatusStr = Boolean.toString(paymentStatus.isPayment_status());

        // Calculate checksum
        String calculatedCheckSum = CheckSumUtil.calculateCheckSum(token, paymentStatus.isPayment_status(), apiKey);

        // Logging for debugging
        System.out.println("Received Checksum: " + paymentStatus.getCheck_sum());
        System.out.println("Calculated Checksum: " + calculatedCheckSum);

        Map<String, Object> response = new HashMap<>();

        // Compare checksums
        if (calculatedCheckSum.equals(paymentStatus.getCheck_sum())) {
            // Check payment status
            boolean paymentSuccessful = paymentStatus.isPayment_status();

            // Fetch the related Commande entity
            Commande commande = commandeRepository.findById(paymentStatus.getOrder_id())
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            // Create and save the Payment entity
            Payment payment = new Payment();
            payment.setToken(token);
            payment.setPaymentStatus(paymentSuccessful);
            payment.setChecksum(calculatedCheckSum);
            payment.setOrderId(paymentStatus.getOrder_id());
            payment.setCommande(commande);

            paymentRepository.save(payment);

            if (paymentSuccessful) {
                // Payment was successful, process accordingly
                System.out.println("Payment was successful. Order ID: " + paymentStatus.getOrder_id());
                response.put("status", "success");
                response.put("message", "Payment was successful");
                response.put("order_id", paymentStatus.getOrder_id());
            } else {
                // Payment was not successful
                System.out.println("Payment failed. Order ID: " + paymentStatus.getOrder_id());
                response.put("status", "failed");
                response.put("message", "Payment failed");
                response.put("order_id", paymentStatus.getOrder_id());
            }

            // Respond with success message
            return ResponseEntity.ok(response);
        } else {
            // Respond with invalid checksum error
            response.put("status", "error");
            response.put("message", "Invalid checksum");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
