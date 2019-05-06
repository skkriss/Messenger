package com.example.mockdemo.app;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;
import org.easymock.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

import static com.example.mockdemo.messenger.ConnectionStatus.FAILURE;
import static com.example.mockdemo.messenger.ConnectionStatus.SUCCESS;
import static com.example.mockdemo.messenger.SendingStatus.SENDING_ERROR;
import static com.example.mockdemo.messenger.SendingStatus.SENT;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(EasyMockExtension.class)
public class MessengerAppEasyMockTest extends EasyMockSupport {

    @Mock(type = MockType.NICE)
    private MessageService messageService;

    Messenger messenger;

    private String VALID_SERVER;
    private String INVALID_SERVER;
    private String VALID_MESSAGE;
    private String INVALID_MESSAGE;
    ConnectionStatus CSSuccess = SUCCESS;
    ConnectionStatus CSFaillure = FAILURE;
    SendingStatus SSSent = SENT;
    SendingStatus SSError = SENDING_ERROR;

    @BeforeEach
    void init() {
        messenger = new Messenger(messageService);
    }

    @Test
    public void checkConnectionValidReturns0() {
        VALID_SERVER = "asd.ad.org.pl";
        expect(messageService.checkConnection(VALID_SERVER)).andReturn(CSSuccess);

        replay(messageService);

        int result = messenger.testConnection(VALID_SERVER);
        assertEquals(0, result);
    }

    @Test
    public void checkConnectionCheckReturns() {
        INVALID_SERVER = "qwe...wqe.pl";

        expect(messageService.checkConnection(INVALID_SERVER)).andReturn(CSFaillure);

        replay(messageService);

        int result = messenger.testConnection(INVALID_SERVER);

        assertNotNull(result);

    }

    @Test
    public void checkConnectionTypeReturs() {

        IMocksControl mockMaker = EasyMock.createStrictControl();

        VALID_SERVER = "asd.ad.org.pl";
        messageService = mockMaker.createMock(MessageService.class);
        messenger = new Messenger(messageService);

        expect(messageService.checkConnection(VALID_SERVER)).andReturn(CSSuccess);


        replay(messageService);

        messenger.testConnection(VALID_SERVER);

        verify(messageService);

    }

    @Test
    public void checkSendMessageProblemsWithSendReturns0() throws MalformedRecipientException {
        VALID_SERVER = "koko.jambo.pl";
        VALID_MESSAGE = "Całkiem fajna wiadmość";

        expect(messageService.send(VALID_SERVER, VALID_MESSAGE)).andReturn(SSError);

        replay(messageService);
        int result = messenger.sendMessage(VALID_SERVER, VALID_MESSAGE);

        assertEquals(1, result);


    }

    @Test
    public void checkSendMessageWrongServerReturns2() throws MalformedRecipientException {
        VALID_SERVER = "koko...jambo.pl";
        VALID_MESSAGE = "Całkiem fajna wiadmość";

        expect(messageService.send(VALID_SERVER, VALID_MESSAGE)).andThrow(new MalformedRecipientException());

        replay(messageService);

        Executable closureContainingCodeToTest = () -> {
            messageService.send(VALID_SERVER, VALID_MESSAGE);
        };

        assertThrows(MalformedRecipientException.class, closureContainingCodeToTest, "a message");
    }

    // Określenie że musi byc wywołana co najmniej raz
    @Test
    public void checkSendMessageAtLeastOnce() throws MalformedRecipientException {
        VALID_SERVER = "koko...jambo.pl";
        VALID_MESSAGE = "Całkiem fajna wiadmość";

        expect(messageService.send(VALID_SERVER, VALID_MESSAGE)).andReturn(SSError);
        expectLastCall().atLeastOnce();

        replay(messageService);

        messenger.sendMessage(VALID_SERVER, VALID_MESSAGE);
        messenger.sendMessage(VALID_SERVER, VALID_MESSAGE);

        verify(messageService);
    }

    @Test
    public void checkSendMessage3TimesCalled() throws MalformedRecipientException {


        expect(messageService.send("", "")).andReturn(SSError);
        expectLastCall().times(3);

        replay(messageService);

        messenger.sendMessage("", "");
        messenger.sendMessage("", "");
        messenger.sendMessage("", "");

        verify(messageService);

    }

    @AfterEach
    public void after() {
        resetAll();
        replayAll();
    }
}