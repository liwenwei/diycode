package com.example.wenwei.diycode.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.wenwei.diycode.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liwenwei on 6/8/2018.
 */

public class DBManager {

    private final int BUFFER_SIZE = 400000;
    private String DB_PATH;
    private final String DB_NAME = "countries.db";

    private SQLiteDatabase database;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        DB_PATH = "/data"
                + Environment.getDataDirectory().getAbsolutePath() + "/"
                + context.getPackageName();
    }

    public void open() {
        database = open(DB_PATH + "/" + DB_NAME);
    }

    private SQLiteDatabase open(String dbFile) {
        File file = new File(dbFile);
        if (!file.exists()) {
            try {
                InputStream is = context.getResources().openRawResource(R.raw.countries);
                FileOutputStream fos = new FileOutputStream(dbFile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int len;
                while ((len = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                is.close();
                fos.close();
            } catch (FileNotFoundException e) {
                Log.e("Database", "File not found");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("Database", "IO exception");
                e.printStackTrace();
            }
        }
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        return db;
    }

    public void close() {
        if (this.database != null) {
            this.database.close();
        }
    }
}
