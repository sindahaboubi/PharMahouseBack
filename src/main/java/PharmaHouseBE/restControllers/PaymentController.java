package PharmaHouseBE.restControllers;

import PharmaHouseBE.configuration.PaymentRequest;
import PharmaHouseBE.services.Implementation.PaymeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymeeService paymeeService;

    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestBody  PaymentRequest paymentRequest) {
        try {
            String paymentUrl = paymeeService.createPayment(paymentRequest);
            return ResponseEntity.ok(paymentUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
