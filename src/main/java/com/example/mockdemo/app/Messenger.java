package com.example.mockdemo.app;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;

public class Messenger {

    private MessageService ms;

    public Messenger(MessageService ms) {
        this.ms = ms;
    }


    public int testConnection(String server) {

        ConnectionStatus status;
        try {
            status = ms.checkConnection(server);
        } catch (IllegalArgumentException e) {
            return 1;
        }

        if( status == ConnectionStatus.SUCCESS)
            return 0;
        else
            return 1;
    }

    public int sendMessage(String server, String message) {
        SendingStatus status;

        try {

            status = ms.send(server, message);

        } catch (MalformedRecipientException e) {
            return 2;
        }

        if(status == SendingStatus.SENT)
            return 0;
        else if (status == SendingStatus.SENDING_ERROR )
            return 1;
        return 1;
    }
}
