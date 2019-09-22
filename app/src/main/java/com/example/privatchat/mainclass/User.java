package com.example.privatchat.mainclass;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.stfalcon.chatkit.commons.models.IUser;

import org.json.JSONException;
import org.json.JSONObject;


@Entity
public class User  implements IUser {
    @PrimaryKey
    @NonNull
    String id;

    String name;

    String avatar;

    String ldap;

    public User(@NonNull String id, @NonNull String name, @NonNull String avatar,@NonNull String ldap) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.ldap = ldap;
    }
    public User(String str) {
        try {
            JSONObject jsonObject = new JSONObject(str);
            this.id = jsonObject.getString("id");
            this.name = jsonObject.getString("name");
            this.avatar = jsonObject.getString("avatar");
            this.ldap = jsonObject.getString("ldap");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public User() { }

    @Override
    @NonNull
    public String getId() {
        return id;
    }

    @Override
    @NonNull
    public String getName() {
        return name;
    }

    @Override
    @NonNull
    public String getAvatar() {
        return avatar;
    }

    @NonNull
    public String getLdap() {
        return ldap;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setAvatar(@NonNull String avatar) {
        this.avatar = avatar;
    }

    public void setLdap(@NonNull String ldap) {
        this.ldap = ldap;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id:\"" + id + '\"' +
                ", \"name:\"" + name + '\"' +
                ", \"avatar:\"" + avatar + '\"' +
                ", \"ldap:\"" + ldap + '\"' +
                '}';
    }
}
