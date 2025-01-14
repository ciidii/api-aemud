package org.aemudapi.notification.prodivers;

import org.aemudapi.config.AppConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrangeCrendentialsProvider {
    private final AppConfig appConfig;

    public OrangeCrendentialsProvider(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public String getOAuthToken() throws IOException {
        String url = "https://api.orange.com/oauth/v3/token";
        HttpPost httpPost = new HttpPost(url);

        String authHeader = "Basic " + appConfig.getOrangeApiKey();

        httpPost.setHeader("Authorization", authHeader);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Accept", "application/json");

        StringEntity entity = new StringEntity("grant_type=client_credentials");
        httpPost.setEntity(entity);

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(httpPost)) {
            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            JSONObject jsonResponse = new JSONObject(responseString);
            return jsonResponse.getString("access_token");
        }
    }

}
