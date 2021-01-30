package org.pibot.controler;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat.Chat;

public class MessageParameters
{
    Chat chat;
    String message;

    public MessageParameters(Chat c, String msg)
    {
        this.chat = c;
        this.message = msg;
    }

}
