package com.fastglobal.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SmsResponse {
    
    @JsonProperty("success")
    private boolean success;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("data")
    private List<SmsResult> data;
    
    public SmsResponse() {}
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public List<SmsResult> getData() {
        return data;
    }
    
    public void setData(List<SmsResult> data) {
        this.data = data;
    }
    
    public static class SmsResult {
        @JsonProperty("id")
        private String id;
        
        @JsonProperty("status")
        private String status;
        
        @JsonProperty("number")
        private String number;
        
        public SmsResult() {}
        
        public String getId() {
            return id;
        }
        
        public void setId(String id) {
            this.id = id;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
        
        public String getNumber() {
            return number;
        }
        
        public void setNumber(String number) {
            this.number = number;
        }
    }
}
