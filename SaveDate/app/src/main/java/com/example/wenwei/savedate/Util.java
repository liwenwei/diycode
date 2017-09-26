package com.example.wenwei.savedate;


import android.content.Context;
import android.widget.Toast;

public class Util {
    public static void toastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
