package com.example.privatchat.model.bd.converter;

import android.arch.persistence.room.TypeConverter;

import com.example.privatchat.mainclass.Message;
import com.example.privatchat.mainclass.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class Converters {

    @TypeConverter
    public static String fromArrayList(ArrayList<User> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
    @TypeConverter
    public static ArrayList<User> fromStringUser(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }


    @TypeConverter
    public static String fromMessage(@NotNull Message message){
        String message2 = message.toString();
        return message2;
    }
    @TypeConverter
    public static Message fromStringMES(String message){
        Message msg = new Message();
        try {
            JSONObject  jsonObject = new JSONObject(message);
            msg.setAuthor(new User(jsonObject.getString("author")));
            msg.setCreatedAt(new Date(jsonObject.getString("createdAt")));
            msg.setId(jsonObject.getString("Id"));
            msg.setText(jsonObject.getString("text"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return msg;
    }


    @NotNull
    @TypeConverter
    public static String fromUser(@NotNull User user) {
        return user.toString();
    }
    @TypeConverter
    public static User userfromString(String user) {
        User user1 = null;
        try {
            JSONObject  jsonObject = new JSONObject(user);
            user1 = new User(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.getString("avatar"),jsonObject.getString("ldap"));
            return user1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user1;
    }


    @NotNull
    @TypeConverter
    public static String stringFromDate(@NotNull Date date) {
        return date.toString();
    }

    @NotNull
    @Contract("_ -> new")
    @TypeConverter
    public static Date datefromString(String date) {
        return new Date(date);
    }

}
