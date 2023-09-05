package com.tago.api.slack.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SlackService{

    @Value("${SLACK_BOT_TOKEN}")
    private String slackToken;

    private static final String SLACK_POST_MESSAGE_URL = "https://slack.com/api/chat.postMessage";

    public void sendMessageToSlack(String channel, String message) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(slackToken);
        headers.add("Content-type", "application/json;charset=utf-8");

        String requestBody = String.format("{\"channel\":\"%s\",\"text\":\"%s\"}", channel, message);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                SLACK_POST_MESSAGE_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to send message to Slack: " + response.getBody());
        }
    }
}
