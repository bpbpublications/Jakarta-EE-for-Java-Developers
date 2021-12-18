package net.rhuanrocha.dto;

import java.util.List;

public class MessageDto
{
    public List<String> messages;

    public static MessageDto of (List<String> messages){
        MessageDto message = new MessageDto();
        message.messages = messages;
        return message;
    }
}
