package com.jy.hubspot.hubspot.service.impl;

import com.jy.hubspot.hubspot.messages.Conversation;
import com.jy.hubspot.hubspot.messages.InputDataSet;
import com.jy.hubspot.hubspot.service.HubspotApiService;
import com.jy.hubspot.hubspot.service.DemoService;
import com.jy.hubspot.hubspot.utils.HubspotUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DemoServiceImpl implements DemoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    private HubspotApiService hubspotApiService;

    public DemoServiceImpl(HubspotApiService hubspotApiService) {
        this.hubspotApiService = hubspotApiService;
    }

    @Override
    public InputDataSet getInputDataSet() {
        InputDataSet inputDataSet = hubspotApiService.getInputDataSet();
        return inputDataSet;
    }

    @Override
    public List<Conversation> getConversationList(InputDataSet inputDataSet) {
        return HubspotUtil.getMessages(inputDataSet);
    }

    @Override
    public String sendOutputData(List<Conversation> invitations) {
        return hubspotApiService.sendOutputData(invitations);
    }

}
