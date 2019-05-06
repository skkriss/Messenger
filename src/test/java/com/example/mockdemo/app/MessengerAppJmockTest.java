package com.example.mockdemo.app;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;
import junit.framework.TestCase;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import static com.example.mockdemo.messenger.ConnectionStatus.FAILURE;
import static com.example.mockdemo.messenger.ConnectionStatus.SUCCESS;
import static com.example.mockdemo.messenger.SendingStatus.SENDING_ERROR;
import static com.example.mockdemo.messenger.SendingStatus.SENT;

public class MessengerAppJmockTest extends TestCase{

    Mockery context = new Mockery();
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

    }

    @Test
    public void testTestConnectionValid(){
        VALID_SERVER = "asd.asa.pl";
        final MessageService messageService = context.mock(MessageService.class);
        messenger = new Messenger(messageService);
        context.checking(new Expectations(){{
            oneOf (messageService).checkConnection(VALID_SERVER); will(returnValue(CSSuccess));
        }});

        messenger.testConnection(VALID_SERVER);

        context.assertIsSatisfied();
    }

    @Test
    public void testTestConnectionInValid(){
        INVALID_SERVER = "asd...asa.pl";
        final MessageService messageService = context.mock(MessageService.class);
        messenger = new Messenger(messageService);
        context.checking(new Expectations(){{
            oneOf (messageService).checkConnection(INVALID_SERVER); will(returnValue(CSFaillure));
        }});

        int actual = messenger.testConnection(INVALID_SERVER);

        assertEquals(1,actual);

        //context.assertIsSatisfied();
    }

    @Test
    public void testSendValid() throws MalformedRecipientException {
        VALID_SERVER = "asd.asa.pl";
        VALID_MESSAGE = "asd";
        final MessageService messageService = context.mock(MessageService.class);
        messenger = new Messenger(messageService);
        context.checking(new Expectations(){{
            oneOf (messageService).send(VALID_SERVER,VALID_MESSAGE); will(returnValue(SSSent));
        }});

        messenger.sendMessage(VALID_SERVER,VALID_MESSAGE);

        context.assertIsSatisfied();
    }

    @Test
    public void testSendCountValid() throws MalformedRecipientException {
        VALID_SERVER = "asd.asa.pl";
        VALID_MESSAGE = "asd";
        final MessageService messageService = context.mock(MessageService.class);
        messenger = new Messenger(messageService);
        context.checking(new Expectations(){{
            exactly(2).of (messageService).send(with(any(String.class)),with(any(String.class))); will(returnValue(SSSent));
        }});

        messenger.sendMessage(VALID_SERVER,VALID_MESSAGE);
        messenger.sendMessage("","");

        context.assertIsSatisfied();
    }

    @Test
    public void testSendNeverCallValid() throws MalformedRecipientException {
        VALID_SERVER = "asd.asa.pl";
        VALID_MESSAGE = "asd";
        final MessageService messageService = context.mock(MessageService.class);
        messenger = new Messenger(messageService);
        context.checking(new Expectations(){{
            never (messageService).send(with(any(String.class)),with(any(String.class)));
            oneOf(messageService).checkConnection(with(any(String.class)));
        }});

        messenger.testConnection(VALID_SERVER);

        context.assertIsSatisfied();
    }

}
