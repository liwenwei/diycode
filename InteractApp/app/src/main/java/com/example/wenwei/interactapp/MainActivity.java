package com.example.wenwei.interactapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int PICK_CONTACT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callBtnClick(View view) {
        Uri number = Uri.parse("tel:5551234");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    public void browserBtnClick(View view) {
        Uri webpage = Uri.parse("http://www.android.com");
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(browserIntent);
    }

    public void mapBtnClick(View view) {
        Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

        PackageManager packageManager = getPackageManager();
        List activities = packageManager.queryIntentActivities(mapIntent, 0);
        boolean isIntentSafe = activities.size() > 0;
        if (isIntentSafe) {
            startActivity(mapIntent);
        }
    }

    public void emailBtnClick(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"liwenwei06@126.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Emial Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");

        startActivity(emailIntent);
    }

    public void calendarBtnClick(View view) {
//        Calendar calendar = Calendar.getInstance();
//        Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
//        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calendar.getTimeInMillis());
//        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calendar.getTimeInMillis() + 60 * 60 * 1000);
//        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
//        calendarIntent.putExtra("rrule", "FREQ=YEARLY");
//        calendarIntent.putExtra(CalendarContract.Events.TITLE, "A Test Event from android app");
//        startActivity(calendarIntent);
    }

    public void pickContactClick(View view) {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                Uri contactUri  = data.getData();

                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column);

                Toast.makeText(MainActivity.this, number, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
