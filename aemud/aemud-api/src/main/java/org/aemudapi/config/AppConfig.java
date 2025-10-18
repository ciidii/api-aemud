package org.aemudapi.config;

import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class AppConfig {

    public String getOrangeApiKey() {
        String clientId = "QHUsNGeilvflSCqQWXgmQXrbnyboGeHs";
        String clientSecret = "r8Ej1DlcrUcEJIrUJzegyXBF05JV4tzlQvCOkyBGIhbM";
        String credentials = clientId + ":" + clientSecret;

        // Encode credentials in Base64
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}