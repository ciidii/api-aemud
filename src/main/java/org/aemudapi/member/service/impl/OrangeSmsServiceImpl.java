package org.aemudapi.member.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.notification.prodivers.OrangeCrendentialsProvider;
import org.aemudapi.notification.services.OrangeSmsService;
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

@Slf4j
@Service
@AllArgsConstructor
public class OrangeSmsServiceImpl implements OrangeSmsService {

    private static final String ORANGE_API_URL = "https://api.orange.com/smsmessaging/v1/outbound/tel%3A%2B{sender_number}/requests";
    private static final String SENDER_NUMBER = "221782156437";
    private final OrangeCrendentialsProvider credentialsProvider;
    private final MemberRepository memberRepository;

    @Override
    public String sendSms(String recipientNumber, String message) throws IOException {
        return sendSmsToMultipleRecipients(List.of(recipientNumber), message);
    }

    @Override
    public String sendSmsToMultipleRecipients(List<String> recipientNumbers, String message) throws IOException {
        String url = ORANGE_API_URL.replace("{sender_number}", SENDER_NUMBER);
        message = replaceSpecialCharacters(message);
        String token = credentialsProvider.getOAuthToken();

        StringBuilder responses = new StringBuilder();

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            int counter = 0;
            for (String recipientNumber : recipientNumbers) {
                HttpPost httpPost = this.getHttpPost(recipientNumber, message, url, token);

                try (CloseableHttpResponse response = client.execute(httpPost)) {
                    int statusCode = response.getStatusLine().getStatusCode();
                    String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                    log.debug(responseBody);
                    if (statusCode >= 200 && statusCode < 300) {
                        responses.append(responseBody).append("\n");
                    } else {
                        throw new RuntimeException("Failed to send SMS. Status Code: " + statusCode + ", Response: " + responseBody);
                    }
                }
                counter++;
                if (counter % 5 == 0) {
                    TimeUnit.SECONDS.sleep(1);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return responses.toString();
    }

    private HttpPost getHttpPost(String recipientNumber, String message, String url, String token)
            throws UnsupportedEncodingException {

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization", "Bearer " + token);
        httpPost.setHeader("Content-Type", "application/json");

        String json = "{"
                + "\"outboundSMSMessageRequest\": {"
                + "\"address\": \"tel:+" + recipientNumber + "\","
                + "\"senderAddress\": \"tel:+" + SENDER_NUMBER + "\","
                + "\"outboundSMSTextMessage\": {"
                + "\"message\": \"" + message + "\""
                + "}"
                + "}"
                + "}";

        log.debug("Request JSON: {}", json); // ✅ Debug du contenu envoyé
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        return httpPost;
    }

    public String formatMessage(String message, Member member) {

        return message
                .replace("%member_name%", member.getPersonalInfo().getName().toUpperCase() + member.getPersonalInfo().getName().toUpperCase())
                .replace("%member_bourse%", "" + member.getBourse().getMontant())
                ;
    }

    private String replaceSpecialCharacters(String message) {
        return message
                .replace("é", "e")
                .replace("è", "e")
                .replace("ê", "e")
                .replace("ë", "e")
                .replace("à", "a")
                .replace("â", "a")
                .replace("î", "i")
                .replace("ï", "i")
                .replace("ô", "o")
                .replace("ö", "o")
                .replace("ù", "u")
                .replace("û", "u")
                .replace("ç", "c")
                .replace("'", " ")
                ;
    }

}
