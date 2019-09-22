package com.example.privatchat.mainclass;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.privatchat.model.bd.converter.Converters;
import com.stfalcon.chatkit.commons.models.IMessage;

import java.util.Date;

@Entity
public class Message implements IMessage {

    @PrimaryKey
    @NonNull
    String id;
    String text;
    @TypeConverters({Converters.class})
    User author;
    @TypeConverters({Converters.class})
    Date createdAt;

    public Message(String id, String text, User author, Date createdAt) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
    }

    public Message( String text) {
        this.text = text;
    }

    public Message() { }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public User getUser() {
        return author;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + "\"'" +
                ", \"text\":\"" + text + '\"' +
                ", \"author\":\"" + author +'\"'+
                ", \"createdAt\":\"" + createdAt +'\"'+
                '}';
    }
}
