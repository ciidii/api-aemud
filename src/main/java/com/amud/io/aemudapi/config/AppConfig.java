package com.amud.io.aemudapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${GOOGLE_CRENDTIAL_PATH}")
    private String googleCredentialsPath;

    @Value("${SPREAD_ID}")
    private String spreadIDs;

    @Value("${ORANGE_API_TOKEN}")
    private static String orangeApiToken;

    @Value("${orange.api.key}")
    private String orangeApiKey;

    @Value("${orange.api.secret}")
    private String orangeApiSecret;

    public String getGoogleCredentialsPath() {
        return googleCredentialsPath;
    }
    public String getSpreadID() {
        return spreadIDs;
    }
    public String getOrangeApiToken() {
        return orangeApiToken;
    }
    public String getOrangeApiKey() {
        return orangeApiKey;
    }

    public String getOrangeApiSecret() {
        return orangeApiSecret;
    }
}
