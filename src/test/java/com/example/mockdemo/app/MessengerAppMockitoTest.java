package com.example.mockdemo.app;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Matchers;
import org.mockito.Mock;

import static com.example.mockdemo.messenger.ConnectionStatus.FAILURE;
import static com.example.mockdemo.messenger.ConnectionStatus.SUCCESS;
import static com.example.mockdemo.messenger.SendingStatus.SENDING_ERROR;
import static com.example.mockdemo.messenger.SendingStatus.SENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessengerAppMockitoTest {

    @Mock
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

    @BeforeEach
    void init() {
        messenger = new Messenger(messageService);
    }

    @Test
    public void checkConnectionValidMocito() {
        VALID_SERVER = "inf.ug.edu.pl";
        doReturn(CSSuccess).when(messageService).checkConnection(VALID_SERVER);

        int result = messenger.testConnection(VALID_SERVER);

        assertEquals(0, result);
    }

    @Test
    public void checkConnectionInvalidMocito() {

        INVALID_SERVER = "asdsadas..asdd.pl";

        doReturn(CSFaillure).when(messageService).checkConnection(INVALID_SERVER);

        int result = messenger.testConnection(INVALID_SERVER);

        assertEquals(1, result);
    }

    // Verify

    //Sprawdzenie argumentu z jakim wywołała się mockowana funkcja
    @Test
    public void checkConnectionValidVerifyMocito() {

        VALID_SERVER = "apple.org.com";

        //when(messageService.checkConnection(VALID_SERVER)).thenAnswer(a ->(MessageService)(a.getArguments()[0]));
        messenger.testConnection(VALID_SERVER);

        verify(messageService).checkConnection(VALID_SERVER);
    }

    // Sprawdzenie ile razy się wywołała mokowana funkcja
    @Test
    public void checkSendMessageCountCallFucntionReturns2() throws MalformedRecipientException {
        VALID_SERVER = "apple.org.com";
        VALID_MESSAGE = "ASdas";

        doReturn(SSSent).when(messageService).send(any(String.class), any(String.class));

        messenger.sendMessage(VALID_SERVER, VALID_MESSAGE);
        messenger.sendMessage(VALID_SERVER, VALID_MESSAGE);

        verify(messageService, times(2)).send(any(String.class), any(String.class));
    }

    @Test
    public void checkTestConnectionCountCallFucntionReturns2() {
        VALID_SERVER = "apple.org.com";

        doReturn(CSSuccess).when(messageService).checkConnection(any(String.class));

        messenger.testConnection(VALID_SERVER);
        messenger.testConnection(VALID_SERVER);

        verify(messageService, times(2)).checkConnection(any(String.class));
    }

    @Test
    public void checkSendMessageProblemsWithSendReturns2() {
        INVALID_SERVER = "asd.asd.pl";
        INVALID_MESSAGE = "Asdsa";

        try {
            when(messageService.send(INVALID_SERVER, INVALID_MESSAGE)).thenReturn(SSError);
        } catch (MalformedRecipientException e) {
            fail("Shud no throw exception.");
        }

        int result = messenger.sendMessage(INVALID_SERVER, INVALID_MESSAGE);

        assertEquals(1, result);
    }

    @Test
    public void asdcheckTestConnectionCountCallFucntion() throws MalformedRecipientException {
        VALID_SERVER = "apple.org.com";
        VALID_MESSAGE = "Asdsa";

        doReturn(SSSent).when(messageService).send(any(String.class), any(String.class));

        messenger.sendMessage(VALID_SERVER, VALID_MESSAGE);
        messenger.sendMessage(VALID_SERVER, "second");

        //verify(messageService,times(2)).checkConnection(any(String.class));
        verify(messageService, atLeast(2)).send(Matchers.startsWith("apple"), any(String.class));

    }
}

