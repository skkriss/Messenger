package com.example.mockdemo.app;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;
import mockit.Expectations;
import mockit.FullVerifications;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.mockdemo.messenger.ConnectionStatus.FAILURE;
import static com.example.mockdemo.messenger.ConnectionStatus.SUCCESS;
import static com.example.mockdemo.messenger.SendingStatus.SENDING_ERROR;
import static com.example.mockdemo.messenger.SendingStatus.SENT;
import static org.junit.Assert.assertEquals;

@RunWith(JMockit.class)
public class MessengerAppJmockitTest {

    @Mocked
    MessageService messageService;


    Messenger messenger;
    private String VALID_SERVER;
    private String INVALID_SERVER;
    private String VALID_MESSAGE;
    private String INVALID_MESSAGE;
    ConnectionStatus CSSuccess = SUCCESS;
    ConnectionStatus CSFaillure = FAILURE;
    SendingStatus SSSent = SENT;
    SendingStatus SSError = SENDING_ERROR;

    @Before
    public void init(){
        messenger = new Messenger(messageService);
    }
    @Test
    public void checkConnectionStatusJmockito(){

        VALID_SERVER = "inf.ug.edu.pl";
        new Expectations(){{
            messageService.checkConnection(VALID_SERVER);
            result = CSSuccess;
        }};

        int actual = messenger.testConnection(VALID_SERVER);

        assertEquals(0,actual);
        new FullVerifications(messageService){};
    }

    @Test
    public void checkConnectioNotVaidJmockito(){
        INVALID_SERVER = "aa..a.pl";

        new Expectations(){{
            messageService.checkConnection(INVALID_SERVER);
            result = CSFaillure;
        }};
        int actual = messenger.testConnection(INVALID_SERVER);

        assertEquals(1,actual);
        new FullVerifications(messageService){};
    }

    @Test
    public void checkSendMessageJmockito() throws MalformedRecipientException {
        INVALID_SERVER = "adas..asda";
        INVALID_MESSAGE ="as";

        new Expectations(){{
            messageService.send(INVALID_SERVER,INVALID_MESSAGE);
            result = new MalformedRecipientException();
        }};

        int actual = messenger.sendMessage(INVALID_SERVER,INVALID_MESSAGE);

        assertEquals(2,actual);
        new FullVerifications(messageService){};
    }

    @Test
    public void checkSendMessageDifrentWayJmockito() throws MalformedRecipientException {
        VALID_SERVER = "ads.asa.pl";
        INVALID_MESSAGE ="ashhh";

        new Expectations(){{
            messageService.send(anyString,anyString); minTimes = 2;

        }};

        messenger.sendMessage("","");
        messenger.sendMessage("","");


    }

    @Test
    public void check() throws MalformedRecipientException {
        VALID_SERVER = "mat.ug.edu.pl";

        messenger.sendMessage(VALID_SERVER,"");
        new Verifications() {
            {
                String a, b;
                messageService.send(a = withCapture(), b = withCapture());

                assertEquals(VALID_SERVER,a);
            }
        };
    }


}
