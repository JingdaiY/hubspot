package com.jy.hubspot.hubspot.controller;

import com.jy.hubspot.hubspot.messages.Conversation;
import com.jy.hubspot.hubspot.messages.InputDataSet;
import com.jy.hubspot.hubspot.service.DemoService;
import com.jy.hubspot.hubspot.utils.ConversationComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hubspot/")
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    private DemoService demoService;

    public  DemoController(DemoService demoService) {

        this.demoService = demoService;

    }

    @GetMapping(produces = "application/json")
    public String get(@RequestParam Map<String, String> params) {

        InputDataSet inputDataSet = demoService.getInputDataSet();

        List<Conversation> conversationList = demoService.getConversationList(inputDataSet);

        Collections.sort(conversationList, new ConversationComparator());

        return demoService.sendOutputData(conversationList);

    }
}
