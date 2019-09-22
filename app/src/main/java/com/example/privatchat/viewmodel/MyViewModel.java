package com.example.privatchat.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.widget.EditText;

import com.example.privatchat.mainclass.Dialog;
import com.example.privatchat.mainclass.Message;
import com.example.privatchat.mainclass.User;
import com.example.privatchat.model.Model;
import com.example.privatchat.view.DialogsActivity;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MyViewModel extends ViewModel implements LifecycleObserver {

    private Model model = new Model();
    private static AtomicReference<User> mainuser = new AtomicReference<>();



    public void getUserldap(String ldap,Context context){

        model.getUser(ldap).observe(ProcessLifecycleOwner.get(),user -> {
            mainuser.set(user);
            DialogsActivity.open(context);
        });
    }


    public MutableLiveData<List<Dialog>> getDialog (){
        User user = mainuser.get();
        return model.getDialogsUser(user);
    }


    public User getUser() {
         return mainuser.get();
    }

    public MutableLiveData<ArrayList<Message>> getMessageDialog() {
        return model.getMessageDialog(mainuser.get());
    }

    public MutableLiveData<Message> getnewmessage() {
        return  model.newMessage();
    }
}
