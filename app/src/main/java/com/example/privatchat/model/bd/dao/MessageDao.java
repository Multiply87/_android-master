package com.example.privatchat.model.bd.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.example.privatchat.mainclass.Dialog;
import com.example.privatchat.mainclass.Message;
import com.example.privatchat.mainclass.User;
import com.example.privatchat.model.bd.converter.Converters;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Message message);

    @Query("SELECT * FROM Dialog WHERE users =:user")
    List<Message> getUserMessage(@TypeConverters({Converters.class}) User user);

}
