package com.jy.hubspot.hubspot.utils;

import com.jy.hubspot.hubspot.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class HubspotUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HubspotUtil.class);

    /**
     * Generate country to available dates map as following:
     * 1. Generate a userMap to record User info
     * 2. Generate a userId to conversation map to store conversation info, key-value -> userId-Conversation object
     * 3. For each unique message, loop and do the following:
     * 3  a. check it is a message between who and you
     * 3. b. check if it is the latest message to update the most recent messages
     * 3. c. count + 1
     * 4. return the value list
     * @param inputDataSet
     * @return List<Conversation>
     * @throws Exception
     */
    public static List<Conversation> getMessages(InputDataSet inputDataSet) {

        Map<Integer, User> userMap = new HashMap<>();

        Map<Integer, Conversation> userIdToConversation = new HashMap<>();

        for (User user : inputDataSet.getUsers()) {

            userMap.putIfAbsent(user.getId(), user);

        }

        int userId_self = inputDataSet.getUserId();

        for (Message message : inputDataSet.getMessages()) {

            if (userId_self == message.getFromUserId() && userId_self == message.getToUserId()) {

                LOGGER.error("Sending to yourself is not allowed, invalid data");

                break;

            }

            if (userId_self != message.getFromUserId() && userId_self != message.getToUserId()) {

                LOGGER.error("This message is not related to you");

                break;

            }

            int friend_id = 0;

            if (userId_self == message.getFromUserId()) {

                friend_id = message.getToUserId();

            } else {

                friend_id = message.getFromUserId();
            }


            Conversation conversation = userIdToConversation.get(friend_id);

            if (conversation == null || conversation.getMostRecentMessage().getTimestamp() < message.getTimestamp()) {

                if (conversation == null) {

                    conversation = new Conversation();

                    User user = userMap.get(friend_id);

                    conversation.setAvatar(user.getAvatar());

                    conversation.setFirstName(user.getFirstName());

                    conversation.setLastName(user.getLastName());

                    conversation.setTotalMessages(0);

                    conversation.setUserId(friend_id);

                }

                MostRecentMessage mostRecentMessage = new MostRecentMessage();

                mostRecentMessage.setContent(message.getContent());
                mostRecentMessage.setTimestamp(message.getTimestamp());
                mostRecentMessage.setUserId(message.getFromUserId());

                conversation.setMostRecentMessage(mostRecentMessage);

            }

            conversation.setTotalMessages(conversation.getTotalMessages() + 1);

            userIdToConversation.put(friend_id, conversation);


        }

        return new ArrayList<>(userIdToConversation.values());

    }

}
