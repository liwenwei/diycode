package com.example.wenwei.savedate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private SharedPreferences prefs;

    DatabaseHelper mDatabaseHelper;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
    }

    /*
    https://stackoverflow.com/questions/23024831/android-shared-preferences-example
    * */
    public void get(View view) {
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
    }

    public void write(View view) {
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("id", 12);
        editor.putString("name", "liwenwei");
        editor.apply();
    }

    public void read(View view) {
        int id = prefs.getInt("id", -1);
        String name = prefs.getString("name", null);
    }

    public void createFile(View view) {
        String fileName = "myFile";
        String content = "Hello World!";
        FileOutputStream fos;

        try {
            fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getTempFile(Context context, String url) {
        File file = null;
        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, context.getCacheDir());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    // https://github.com/mitchtabian/SaveReadWriteDeleteSQLite

    public void addClick(View view) {
        String newEntry = editText.getText().toString();
        if (editText.length() != 0) {
            addData(newEntry);
            editText.setText("");
        } else {
            Util.toastMessage(this, "You must put something in the text field!");
        }
    }

    public void viewClick(View view) {
        Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
        startActivity(intent);
    }

    public void addData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            Util.toastMessage(this, "Data Successfully Inserted!");
        } else {
            Util.toastMessage(this, "Something went wrong");
        }
    }
}
