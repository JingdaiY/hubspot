package com.jy.hubspot.hubspot.service;

import com.jy.hubspot.hubspot.messages.Conversation;
import com.jy.hubspot.hubspot.messages.InputDataSet;

import java.util.List;

public interface HubspotApiService {

    InputDataSet getInputDataSet();

    String sendOutputData(List<Conversation> invitationList);
}
