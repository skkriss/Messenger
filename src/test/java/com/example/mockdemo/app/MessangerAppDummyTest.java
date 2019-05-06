package com.example.mockdemo.app;

import com.example.mockdemo.messenger.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class MessangerAppDummyTest {

    private MessageService messageService;
    private Messenger messenger ;
    private String VALID_SERVER;
    private String INVALID_SERVER;
    private String VALID_MESSAGE;
    private String INVALID_MESSAGE;


    @BeforeEach
    void init(){
        messageService = new MessageServiceFake();
        messenger = new Messenger(messageService);
    }

    @Test
    public void checkConnectionStatusValidReturns0(){

        VALID_SERVER = "inf.ug.edu.pl";

        int result = messenger.testConnection(VALID_SERVER);

        assertEquals(0,result);

    }

    @Test
    public void checkConnectionStatusInvalidReturns1(){

        INVALID_SERVER = "testhostn....ame";

        int result = messenger.testConnection(INVALID_SERVER);

        assertNotEquals(0,result);

    }

    @Test
    public void checkConnectionStatusNullValueReturns1(){

        INVALID_SERVER = null;

        int result = messenger.testConnection(INVALID_SERVER);

        assertSame(1,result);
    }

    @Test
    public void checkSendMessageValidValuesReturns0(){
        VALID_SERVER = "www.intel.org.com";
        VALID_MESSAGE = "Hello from the other said";

        int result = messenger.sendMessage(VALID_SERVER,VALID_MESSAGE);

        assertEquals(0,result);
    }

    @Test
    public void checkSendMessageInValidValuesReturns0(){
        INVALID_SERVER = "www..";
        INVALID_MESSAGE = "He";

        int result = messenger.sendMessage(INVALID_SERVER,INVALID_MESSAGE);

        assertEquals(2,result);
    }

    @Test
    public void checkSendMessageProblemsWithSendValuesReturns1(){
        VALID_SERVER = "problems with send";
        VALID_MESSAGE = "Hello from the other said";

        int result = messenger.sendMessage(VALID_SERVER,VALID_MESSAGE);

        assertEquals(2,result);
    }

    @Test
    public void checkSendServerExceptionsOnServer() {
        VALID_SERVER = null;
        VALID_MESSAGE = "Całkiem fajna wiadmość";

        Executable closureContainingCodeToTest = () -> {
            messageService.send(VALID_SERVER, VALID_MESSAGE);
        };

        assertThrows(NullPointerException.class, closureContainingCodeToTest, "a message");
    }

    @Test
    public void checkSendExceptionsOnMessage() {
        VALID_SERVER = "asd.ad.pl";
        VALID_MESSAGE = null;

        Executable closureContainingCodeToTest = () -> {
            messageService.send(VALID_SERVER, VALID_MESSAGE);
        };

        assertThrows(NullPointerException.class, closureContainingCodeToTest, "a message");
    }

    @Test
    public void checkConnectionServerExceptionsOnMessage() {
        VALID_SERVER = null;

        Executable closureContainingCodeToTest = () -> {
            messageService.send(VALID_SERVER, VALID_MESSAGE);
        };

        assertThrows(NullPointerException.class, closureContainingCodeToTest, "a message");
    }
}
