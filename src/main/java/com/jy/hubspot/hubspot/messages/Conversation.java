package com.jy.hubspot.hubspot.messages;

public class Conversation {

    private String avatar;

    private String firstName;

    private String lastName;

    private MostRecentMessage mostRecentMessage;

    private int totalMessages;

    private int userId;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MostRecentMessage getMostRecentMessage() {
        return mostRecentMessage;
    }

    public void setMostRecentMessage(MostRecentMessage mostRecentMessage) {
        this.mostRecentMessage = mostRecentMessage;
    }

    public int getTotalMessages() {
        return totalMessages;
    }

    public void setTotalMessages(int totalMessages) {
        this.totalMessages = totalMessages;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
