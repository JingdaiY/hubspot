package com.jy.hubspot.hubspot.utils;

import com.jy.hubspot.hubspot.messages.Conversation;

import java.util.Comparator;

public class ConversationComparator implements Comparator<Conversation> {
    @Override
    public int compare(Conversation a, Conversation b) {

        return Long.compare(b.getMostRecentMessage().getTimestamp(), a.getMostRecentMessage().getTimestamp());

    }
}