package com.rizz.apicalltest.viewmodels.models;

/**
 * Created by Gokul Kalagara (Mr.Psycho) on 28-02-2019.
 * at 14:57
 * Frost-Interactive
 */
public class Message {
    private String message;
    private int type;

    public Message(String message, int type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
