package com.example.privatchat.mainclass;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.privatchat.model.bd.converter.Converters;
import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Dialog implements IDialog<Message> {

    @PrimaryKey
    @NonNull
    public String id;
    public String dialogPhoto;
    public String dialogName;
    @TypeConverters({Converters.class})
    public ArrayList<User> users;
    @TypeConverters({Converters.class})
    public Message lastMessage;
    public int unreadCount;

    public Dialog() {
    }

    public Dialog(String id, String name, String photo,
                  ArrayList<User> users, Message lastMessage, int unreadCount) {

        this.id = id;
        this.dialogName = name;
        this.dialogPhoto = photo;
        this.users = users;
        this.lastMessage = lastMessage;
        this.unreadCount = unreadCount;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDialogPhoto() {
        return dialogPhoto;
    }

    @Override
    public String getDialogName() {
        return dialogName;
    }

    @Override
    public List<? extends IUser> getUsers() {
        return users;
    }

    @Override
    public Message getLastMessage() {
        return lastMessage;
    }

    @Override
    public void setLastMessage(Message message) {

    }

    @Override
    public int getUnreadCount() {
        return unreadCount;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setDialogPhoto(String dialogPhoto) {
        this.dialogPhoto = dialogPhoto;
    }

    public void setDialogName(String dialogName) {
        this.dialogName = dialogName;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"dialogPhoto\":\"" + dialogPhoto + '\"' +
                ", \"dialogName\":\"" + dialogName + '\"' +
                ", \"users\":\"" + users +'\"'+
                ", \"lastMessage\":\"" + lastMessage +'\"'+
                ", \"unreadCount\":\"" + unreadCount +'\"'+
                '}';
    }
}
