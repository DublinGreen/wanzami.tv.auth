package tv.wanzami.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ZohoOAuth {

    private static final String TOKEN_URL = "https://accounts.zoho.com/oauth/v2/token";
    private static final String CLIENT_ID = "1000.0U0J5V1KOF88WF3Y6JD41PCN910WFE";
    private static final String CLIENT_SECRET = "19a821b1f8112b1216ccf6e2c15adec5dfee7487cc";
    private static final String REDIRECT_URL = "https://wanzami.tv";
    private static String CODE = "1000.2aa9483be18cdde08630c493b348580f.591de873d73c3e20dff96923675d32b0";
        
    public static void getAccessCode() throws IOException, InterruptedException {
    	
        System.out.println("Status code: ");
        System.out.println("Response body:");
        
    	String link = "https://accounts.zoho.com/oauth/v2/auth?scope=ZohoMail.messages.CREATE&"
    			+ "client_id=" + CLIENT_ID
    			+ "&response_type=code&access_type=offline&"
    			+ "redirect_uri=" + REDIRECT_URL;
    	
    	HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(link))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response);
    	
    }
    
    public static void testMail(String code) throws Exception {
        String accessToken = code;
        String accountId = CLIENT_ID;

        String endpoint = "https://mail.zoho.com/api/accounts/" + accountId + "/messages";
        System.out.println(endpoint);

        Map<String, Object> emailPayload = Map.of(
            "fromAddress", "mail@wanzami.tv",
            "toAddress", new String[]{"greendublin007@gmail.com"},
            "subject", "Test Email from Zoho Mail API",
            "content", "This is a test email sent using Zoho Mail API.",
            "mailFormat", "text"  // Use "html" if needed
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(emailPayload);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Authorization", "Zoho-oauthtoken " + accessToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Response: " + response.body());
    }
}
