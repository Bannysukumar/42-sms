package com.fastglobal.sms.config;

public class Config {
    
    // 42 API Configuration
    public static final String API_URL = "https://rest.fortytwo.com/1/im";
    public static final String AUTH_TOKEN = "1ed72abc-c34c-4407-9450-f92ada34417a";
    
    // SMS Configuration
    public static final String DEFAULT_ENCODING = "GSM7";
    public static final int CONNECTION_TIMEOUT = 30000; // 30 seconds
    public static final int READ_TIMEOUT = 60000; // 60 seconds
    
    // Logging Configuration
    public static final String LOG_LEVEL = "INFO";
}
