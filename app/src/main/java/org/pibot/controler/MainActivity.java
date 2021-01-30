package org.pibot.controler;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

public class MainActivity extends Activity {

    AbstractXMPPConnection connection;
    Chat chat;
    Jid tojid;
    boolean claimed;
    static final String RECEIVER = "pibot@jabbers.one";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        try {
            this.connection = (new ConnectTask()).execute().get();
            this.tojid = JidCreate.from("pibot@jabbers.one");
        }catch (Exception e)
        {
            e.printStackTrace();

        }


        this.chat = ChatManager.getInstanceFor(connection)
                .createChat(this.tojid.asEntityJidIfPossible());
        /*try {

            XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword("pibot", "pibot_1234")
                    .setXmppDomain(JidCreate.domainBareFrom("pibot@jabbers.one"))
                    .setHost("jabbers.one")
                    .setConnectTimeout(1000)
                    .build();

            //this.connection = new XMPPTCPConnection("pibot@jabbers.one", "pibot_1234");
            this.connection = new XMPPTCPConnection(configuration);
            this.connection.connect();
            this.connection.login();

            this.tojid = JidCreate.from("pibot@jabbers.one");
            Chat chat = ChatManager.getInstanceFor(connection)
                    .createChat(this.tojid.asEntityJidIfPossible());

            chat.sendMessage("claimcontrol");

        }
        catch (Exception e)
        {
            Log.i("e", "error creating connection");
            e.printStackTrace();
        }

        /*try {
            this.connection = (new ConnectTask()).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/

        this.findViewById(R.id.forward).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    sendMessage("a");
                }
                if(action == MotionEvent.ACTION_UP){
                    sendMessage("c");
                }
                return true;
            }
        });
        this.findViewById(R.id.side_left).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN)
                    sendMessage("b");

                if(action == MotionEvent.ACTION_UP)
                    sendMessage("c");

                return true;
            }
        });

        this.findViewById(R.id.side_right).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN)
                    sendMessage("d");

                if(action == MotionEvent.ACTION_UP)
                    sendMessage("c");

                return true;
            }
        });

        this.findViewById(R.id.reverse).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN)
                    sendMessage("e");

                if(action == MotionEvent.ACTION_UP)
                    sendMessage("c");
                return true;
            }
        });


        this.findViewById(R.id.stopp).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                    sendMessage("claimcontrol");
                return true;
            }
        });


    }

    public void sendMessage(String s)
    {
        try{
            this.chat.sendMessage(s);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
