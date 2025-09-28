package com.fastglobal.sms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastglobal.sms.model.SmsRequest;
import com.fastglobal.sms.model.SmsResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SmsService {
    
    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);
    private static final String API_URL = "https://rest.fortytwo.com/1/im";
    
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String authToken;
    
    public SmsService(String authToken) {
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
        this.authToken = authToken;
    }
    
    public SmsResponse sendSms(SmsRequest smsRequest) throws IOException {
        logger.info("Sending SMS to destinations: {}", 
            smsRequest.getDestinations().stream()
                .map(SmsRequest.Destination::getNumber)
                .toArray());
        
        HttpPost httpPost = new HttpPost(API_URL);
        
        // Set headers
        httpPost.setHeader("Authorization", "Token " + authToken);
        httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
        httpPost.setHeader("Accept", "application/json");
        
        // Convert request to JSON
        String jsonRequest = objectMapper.writeValueAsString(smsRequest);
        logger.debug("Request JSON: {}", jsonRequest);
        
        // Set request body
        StringEntity entity = new StringEntity(jsonRequest, StandardCharsets.UTF_8);
        httpPost.setEntity(entity);
        
        // Execute request
        HttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        
        logger.info("Response status code: {}", statusCode);
        
        // Parse response
        HttpEntity responseEntity = response.getEntity();
        String responseBody = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
        
        logger.debug("Response body: {}", responseBody);
        
        if (statusCode >= 200 && statusCode < 300) {
            SmsResponse smsResponse = objectMapper.readValue(responseBody, SmsResponse.class);
            smsResponse.setSuccess(true);
            return smsResponse;
        } else {
            logger.error("SMS sending failed with status code: {} and response: {}", statusCode, responseBody);
            SmsResponse errorResponse = new SmsResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Failed to send SMS. Status: " + statusCode + ", Response: " + responseBody);
            return errorResponse;
        }
    }
    
    public void close() throws IOException {
        if (httpClient instanceof org.apache.http.impl.client.CloseableHttpClient) {
            ((org.apache.http.impl.client.CloseableHttpClient) httpClient).close();
        }
    }
}
