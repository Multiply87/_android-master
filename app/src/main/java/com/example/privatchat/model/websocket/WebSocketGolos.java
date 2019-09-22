package com.example.privatchat.model.websocket;


import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.privatchat.mainclass.Message;
import com.example.privatchat.mainclass.User;
import com.example.privatchat.view.MessagesActivity;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.Date;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketGolos extends WebSocketListener {

    MutableLiveData<Message> messages = new MutableLiveData<>();



    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send("Hello my darling!");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text)
    {
        Message message = new Message("1",text, new User("1","Andru","https://m.the-flow.ru/uploads/images/origin/10/49/74/35/49/a6b2d1e.png","xxxxxxxxxxx"),new Date());
        messages.postValue(message);

        Log.d("WStest", text);
        System.out.print(text);
    }

    public MutableLiveData<Message> getMessages() {
        return messages;
    }
}
