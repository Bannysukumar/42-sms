package com.fastglobal.sms;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SimpleSmsSender {
    
    public static void main(String[] args) {
        // 42 API Configuration
        String apiUrl = "https://rest.fortytwo.com/1/im";
        String authToken = "1ed72abc-c34c-4407-9450-f92ada34417a";
        
        // SMS Request JSON with enhanced structure
        String smsJson = """
            {
                "destinations": [
                    {
                        "number": "+916301846681"
                    }
                ],
                "job_id": "",
                "sms_content": {
                    "message": "KYC Verification is done",
                    "encoding": "GSM7"
                },
                "im_content": [
                    {
                        "message": "KYC Verification is done",
                        "encoding": "GSM7"
                    }
                ],
                "message_plan": "",
                "callback_url": "",
                "reply_url": "",
                "promotional": true
            }
            """;
        
        System.out.println("Sending SMS via 42 API...");
        System.out.println("API URL: " + apiUrl);
        System.out.println("Message: KYC Verification is done");
        System.out.println("Destination: +916301846681");
        System.out.println();
        
        try {
            // Create HTTP client
            HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
            
            // Create HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Token " + authToken)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(smsJson))
                .build();
            
            // Send request
            HttpResponse<String> response = client.send(request, 
                HttpResponse.BodyHandlers.ofString());
            
            // Display results
            System.out.println("Response Status: " + response.statusCode());
            System.out.println("Response Body:");
            System.out.println(response.body());
            
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                System.out.println("\n✅ SMS sent successfully!");
            } else {
                System.out.println("\n❌ Failed to send SMS. Status: " + response.statusCode());
            }
            
        } catch (IOException | InterruptedException e) {
            System.err.println("❌ Error sending SMS: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
