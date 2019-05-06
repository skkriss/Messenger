package com.example.mockdemo.app;
import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.mockdemo.messenger.ConnectionStatus.FAILURE;
import static com.example.mockdemo.messenger.ConnectionStatus.SUCCESS;
import static com.example.mockdemo.messenger.SendingStatus.SENDING_ERROR;
import static com.example.mockdemo.messenger.SendingStatus.SENT;

public class MessageServiceFake implements MessageService {

    public MessageServiceFake() {
    }

    // Jako że to tylko atrapa nie uwzględanim relanego podłączenia do sieci i sprawdzania czy dany serwer istniej
    // natomiast sprawdzenie będzie polegało na sprawdzeniu poprawności budowy nazwy serwera
    // to już wystarczy do sprawdzenia czy funkcjia testConnection z Messenger działa poprawnie

    public ConnectionStatus checkConnection(String server) {
        if(server == null ) throw new IllegalArgumentException("Name of server cant't be empty.");
        String patternServer = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";
        Pattern a = Pattern.compile(patternServer);
        Matcher m = a.matcher(server);
        if(m.matches()){
            return SUCCESS;
        }else
            return FAILURE;
    }

    // długośc wiadomości co najmniej 3 .Taka długość aby móc sprawdzać czy działa
    // atrapa nie musi odzwierciedlać w pełni
    // Nie sprawdzam problemów z wysłaniem w funkcyjnej formie sprawdzę tylko zwracaną wartość
    // dla przykładowej nazwy

    public SendingStatus send(String server, String message) throws MalformedRecipientException {
        if(server.equals("INVALID SERVER")) { return SENDING_ERROR ;}
        if(server == null ) throw new IllegalArgumentException("Name of server cant't be empty.");


        String patternServer = "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$";
        String patternMessage  = "^.{3,}$";
        Pattern pServer = Pattern.compile(patternServer);
        Pattern pMessage = Pattern.compile(patternMessage);

        Matcher mServer = pServer.matcher(server);
        Matcher mMessage = pMessage.matcher(message);

        if(!mServer.matches() || !mMessage.matches()){
            throw new  MalformedRecipientException();
        }

        return SENT;
    }

}
