package com.fastglobal.sms;

import com.fastglobal.sms.model.SmsRequest;
import com.fastglobal.sms.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SmsApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(SmsApplication.class);
    
    public static void main(String[] args) {
        // API Token - Replace with your actual token
        String authToken = "1ed72abc-c34c-4407-9450-f92ada34417a";
        
        // Create SMS service
        SmsService smsService = new SmsService(authToken);
        
        try {
            // Create SMS request
            SmsRequest smsRequest = createSmsRequest();
            
            // Send SMS
            logger.info("Starting SMS sending process...");
            var response = smsService.sendSms(smsRequest);
            
            if (response.isSuccess()) {
                logger.info("SMS sent successfully!");
                logger.info("Response: {}", response.getMessage());
                
                if (response.getData() != null) {
                    response.getData().forEach(result -> 
                        logger.info("SMS ID: {}, Status: {}, Number: {}", 
                            result.getId(), result.getStatus(), result.getNumber()));
                }
            } else {
                logger.error("Failed to send SMS: {}", response.getMessage());
            }
            
        } catch (IOException e) {
            logger.error("Error sending SMS: {}", e.getMessage(), e);
        } finally {
            try {
                smsService.close();
            } catch (IOException e) {
                logger.error("Error closing SMS service: {}", e.getMessage());
            }
        }
    }
    
    private static SmsRequest createSmsRequest() {
        // Create destination
        SmsRequest.Destination destination = new SmsRequest.Destination("35812387568");
        List<SmsRequest.Destination> destinations = Arrays.asList(destination);
        
        // Create SMS content
        SmsRequest.SmsContent smsContent = new SmsRequest.SmsContent(
            "KYC Verification is done", 
            "GSM7"
        );
        
        // Create and return SMS request
        return new SmsRequest(destinations, smsContent);
    }
}
