package org.pibot.controler;

import android.os.AsyncTask;

import org.jivesoftware.smack.chat.Chat;

public class SendMessageTask extends AsyncTask<MessageParameters, Void, Boolean>
{
    Exception exception;
    @Override
    protected Boolean doInBackground(MessageParameters... parameters)
    {

        Chat chat = parameters[0].chat;
        String message = parameters[0].message;
        try {

            chat.sendMessage(message);
        }
        catch (Exception e)
        {
            this.exception = e;
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (this.exception != null)
            this.exception.printStackTrace();
    }
}
