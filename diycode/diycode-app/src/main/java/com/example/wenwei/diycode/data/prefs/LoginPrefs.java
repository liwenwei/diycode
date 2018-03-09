package com.example.wenwei.diycode.data.prefs;


import android.content.Context;

/**
 * Storing login username and password
 */
public class LoginPrefs {

    private static final String LOGIN_PREF = "LOGIN_PREF";

    private static final String KEY_USER_NAME = "KEY_USER_NAME";
    private static final String KEY_PASSWORD = "KEY_PASSWORD";

    private static volatile LoginPrefs singleton;
    private final ObscuredSharedPreferences prefs;

    public static LoginPrefs get(Context context) {
        if (singleton == null) {
            synchronized (LoginPrefs.class) {
                singleton = new LoginPrefs(context);
            }
        }
        return singleton;
    }

    private LoginPrefs(Context context) {
        prefs = new ObscuredSharedPreferences(context, LOGIN_PREF, Context.MODE_PRIVATE);
    }

    public void setUserName(String userName) {
        try {
            ObscuredSharedPreferences.Editor editor = prefs.edit();
            editor.putString(KEY_USER_NAME, userName);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPassword(String password) {
        ObscuredSharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public String getUserName() {
        return prefs.getString(KEY_USER_NAME, null);
    }

    public String getPassword() {
        return prefs.getString(KEY_PASSWORD, null);
    }
}
