package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.prodivers.OrangeCrendentialsProvider;
import com.amud.io.aemudapi.services.OrangeSmsService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class OrangeSmsServiceImpl implements OrangeSmsService {

    private static final String ORANGE_API_URL = "https://api.orange.com/smsmessaging/v1/outbound/tel%3A%2B{sender_number}/requests";
    private static final String SENDER_NUMBER = "221782156437";
    private final OrangeCrendentialsProvider credentialsProvider;

    public OrangeSmsServiceImpl(OrangeCrendentialsProvider credentialsProvider) {
        this.credentialsProvider = credentialsProvider;
    }

    @Override
    public String sendSms(String recipientNumber, String message) throws IOException {
        return sendSmsToMultipleRecipients(List.of(recipientNumber), message);
    }

    @Override
    public String sendSmsToMultipleRecipients(List<String> recipientNumbers, String message) throws IOException {
        String url = ORANGE_API_URL.replace("{sender_number}", SENDER_NUMBER);
        String token = credentialsProvider.getOAuthToken();

        StringBuilder responses = new StringBuilder();

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            int counter = 0;
            for (String recipientNumber : recipientNumbers) {
                HttpPost httpPost = getHttpPost(recipientNumber, message, url, token);
                try (CloseableHttpResponse response = client.execute(httpPost)) {
                    responses.append(EntityUtils.toString(response.getEntity(), "UTF-8")).append("\n");
                } catch (UnsupportedEncodingException e) {
                    throw new UnsupportedEncodingException();
                }

                counter++;
                if (counter % 5 == 0) {
                    TimeUnit.SECONDS.sleep(1); // Respect TPS limit of 5 SMS per second
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return responses.toString();
    }

    private HttpPost getHttpPost(String recipientNumber, String message, String url, String token) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Authorization", "Bearer " + token);
        httpPost.setHeader("Content-Type", "application/json");

        String json = "{\"outboundSMSMessageRequest\": {\"address\": \"tel:+"
                + recipientNumber + "\",\"senderAddress\": \"tel:+"
                + SENDER_NUMBER + "\",\"outboundSMSTextMessage\": {\"message\": \""
                + message + "\"}}}";

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        return httpPost;
    }

    public String formatMessage(String message, String recipientNumber) {
        //Optional<Member> member = this.memberRepository.findByNumberPhone(recipientNumber);

        return message;
    }
}
