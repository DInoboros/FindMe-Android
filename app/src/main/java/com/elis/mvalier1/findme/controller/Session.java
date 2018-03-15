package com.elis.mvalier1.findme.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by mvalier1 on 15/03/2018.
 */

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUsename(String usename) {
        prefs.edit().putString("usename", usename).apply();
    }

    public String getUsename() {
        String usename = prefs.getString("usename","");
        return usename;
    }

    public void setPassword(String password) {
        prefs.edit().putString("password", password).apply();
    }

    public String getPassword() {
        String password = prefs.getString("password","");
        return password;
    }
}
