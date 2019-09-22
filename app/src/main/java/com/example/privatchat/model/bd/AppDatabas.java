package com.example.privatchat.model.bd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.privatchat.mainclass.Dialog;
import com.example.privatchat.mainclass.Message;
import com.example.privatchat.mainclass.User;
import com.example.privatchat.model.bd.converter.Converters;
import com.example.privatchat.model.bd.dao.DialogDao;
import com.example.privatchat.model.bd.dao.MessageDao;
import com.example.privatchat.model.bd.dao.UserDao;

@Database(entities = {User.class, Dialog.class, Message.class}, version = 1)
public abstract class AppDatabas extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract DialogDao dialogDao();
    public abstract MessageDao messageDao();


}
