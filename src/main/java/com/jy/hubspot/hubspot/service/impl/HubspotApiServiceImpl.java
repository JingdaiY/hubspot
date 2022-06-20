package com.jy.hubspot.hubspot.service.impl;

import com.jy.hubspot.hubspot.messages.Conversation;
import com.jy.hubspot.hubspot.messages.InputDataSet;
import com.jy.hubspot.hubspot.messages.OutputDataSet;
import com.jy.hubspot.hubspot.service.HubspotApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class HubspotApiServiceImpl implements HubspotApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HubspotApiServiceImpl.class);

    @Value("${hubspot.api.get.url}")
    private String getUrl = "https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=51ba6a3f9a03a89d0692e69b7737";

    @Value("${hubspot.api.post.url}")
    private String postUrl = "https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=51ba6a3f9a03a89d0692e69b7737";

    @Override
    public InputDataSet getInputDataSet() {
        RestTemplate restTemplate = new RestTemplate();
        InputDataSet result = restTemplate.getForObject(getUrl, InputDataSet.class);
        return result;
    }

    @Override
    public String sendOutputData(List<Conversation> conversations) {
        String response;
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            OutputDataSet outputDataSet = new OutputDataSet();
            outputDataSet.setConversations(conversations);

            HttpEntity<OutputDataSet> request = new HttpEntity<>(outputDataSet, headers);
            RestTemplate restTemplate = new RestTemplate();

            LOGGER.info("Request: " + request.getBody().toString());

            ResponseEntity<String> result = restTemplate.postForEntity(postUrl, request, String.class);
            response = result.getStatusCode().toString();

        } catch (HttpClientErrorException ex) {
            LOGGER.error("Exception: " + ex);
            response = ex.getResponseBodyAsString();
        }
        return response;
    }
}
