package org.pibot.controler;

import android.os.AsyncTask;
import android.util.Log;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;

public class ConnectTask extends AsyncTask<Void, Void, AbstractXMPPConnection> {

    //AbstractXMPPConnection connection;

    @Override
    protected AbstractXMPPConnection doInBackground(Void[] objects)
    {
        AbstractXMPPConnection connection;

        try {

            XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword("pibot", "pibot_1234")
                    .setXmppDomain(JidCreate.domainBareFrom("pibot@jabbers.one"))
                    .setHost("jabbers.one")
                    .setConnectTimeout(1000)
                    .build();

            //this.connection = new XMPPTCPConnection("pibot@jabbers.one", "pibot_1234");
            connection = new XMPPTCPConnection(configuration);
            connection.connect();
            connection.login();

            Jid toji = JidCreate.from("pibot@jabbers.one");
            Chat chat = ChatManager.getInstanceFor(connection)
                    .createChat(toji.asEntityJidIfPossible());

            chat.sendMessage("claimcontrol");

        }
        catch (Exception e)
        {
            Log.i("e", "error creating connection");
            e.printStackTrace();
            return null;
        }

        return connection;

    }
}
