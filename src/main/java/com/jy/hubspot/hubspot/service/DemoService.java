package com.jy.hubspot.hubspot.service;

import com.jy.hubspot.hubspot.messages.Conversation;
import com.jy.hubspot.hubspot.messages.InputDataSet;

import java.util.List;

public interface DemoService {

    InputDataSet getInputDataSet();

    List<Conversation> getConversationList(InputDataSet inputDataSet);

    String sendOutputData(List<Conversation> invitations);

}