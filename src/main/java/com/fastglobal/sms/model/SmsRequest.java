package com.fastglobal.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SmsRequest {
    
    @JsonProperty("destinations")
    private List<Destination> destinations;
    
    @JsonProperty("sms_content")
    private SmsContent smsContent;
    
    public SmsRequest() {}
    
    public SmsRequest(List<Destination> destinations, SmsContent smsContent) {
        this.destinations = destinations;
        this.smsContent = smsContent;
    }
    
    public List<Destination> getDestinations() {
        return destinations;
    }
    
    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }
    
    public SmsContent getSmsContent() {
        return smsContent;
    }
    
    public void setSmsContent(SmsContent smsContent) {
        this.smsContent = smsContent;
    }
    
    public static class Destination {
        @JsonProperty("number")
        private String number;
        
        public Destination() {}
        
        public Destination(String number) {
            this.number = number;
        }
        
        public String getNumber() {
            return number;
        }
        
        public void setNumber(String number) {
            this.number = number;
        }
    }
    
    public static class SmsContent {
        @JsonProperty("message")
        private String message;
        
        @JsonProperty("encoding")
        private String encoding;
        
        public SmsContent() {}
        
        public SmsContent(String message, String encoding) {
            this.message = message;
            this.encoding = encoding;
        }
        
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
        
        public String getEncoding() {
            return encoding;
        }
        
        public void setEncoding(String encoding) {
            this.encoding = encoding;
        }
    }
}
