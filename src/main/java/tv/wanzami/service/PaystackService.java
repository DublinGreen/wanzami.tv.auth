package tv.wanzami.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaystackService {

    private final String PAYSTACK_SECRET_KEY = "sk_test_c01819341104a5e68df9577d9d8964085119b165"; // Replace with your secret key
    private final String PAYSTACK_VERIFY_URL = "https://api.paystack.co/transaction/verify/";

    public String verifyPayment(String reference) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + PAYSTACK_SECRET_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                PAYSTACK_VERIFY_URL + reference,
                HttpMethod.GET,
                entity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody(); // You can parse and check for status == 'success'
        } else {
            throw new RuntimeException("Payment verification failed with status: " + response.getStatusCode());
        }
    }
}
