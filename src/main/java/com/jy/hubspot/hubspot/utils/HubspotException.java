package com.jy.hubspot.hubspot.utils;

public class HubspotException extends  Exception{

    private int code;

    private String message;

    public HubspotException(String message) {
        super(message);
    }

}
