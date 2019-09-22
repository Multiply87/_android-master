package com.example.privatchat.model.bd.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.privatchat.mainclass.User;

import java.util.List;


@Dao
public interface UserDao {

    @Query("SELECT * FROM User WHERE ldap = :ldap")
    User getuser(String ldap);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user );

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User Where id = '0'")
    User getThisiser();


}
