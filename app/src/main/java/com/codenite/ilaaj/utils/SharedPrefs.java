package com.codenite.ilaaj.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    String email = "email", name = "name",token= "token", newUser = "newUser", userType = "userType", userId = "userId";
    boolean isDoctor = false;
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public SharedPrefs(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Session",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getEmail() {
        return sharedPreferences.getString(email, null);
    }

    public void saveEmail(String key){
        editor.putString(email, key);
        editor.commit();
    }

    public String getName() {
        return sharedPreferences.getString(name, null);
    }

    public void saveName(String key){
        editor.putString(name, key);
        editor.commit();
    }

    public String getToken() {
        return sharedPreferences.getString(token, null);
    }

    public void saveToken(String key){
        editor.putString(token, key);
        editor.commit();
    }

    public boolean getNewUser() {
        return sharedPreferences.getBoolean(newUser, true);
    }

    public void saveNewUser(boolean key){
        editor.putBoolean(newUser, key);
        editor.commit();
    }

    public void clearData(){
        editor.clear();
        editor.commit();
    }

    public void saveUserType(boolean isDoctor){
        editor.putBoolean(userType,isDoctor);
        editor.commit();
    }

    public boolean isDoctor(){
        return sharedPreferences.getBoolean(userType,true);
    }

    public void saveUserId(int id){
        editor.putInt(userId,id);
        editor.commit();
    }

    public int getUserId(){
        return sharedPreferences.getInt(userId,1);
    }
}
