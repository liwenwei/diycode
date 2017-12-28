package com.example.wenwei.diycode;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class RegexTest {

    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void regex_IMAGE_LINK_PATTERN() {
        String regex = appContext.getString(R.string.IMAGE_LINK_PATTERN);
        String input = "[![text](http://note.youdao.com/favicon.ico)](http://note.youdao.com/)";

        boolean actualResult = Pattern.matches(regex, input);
        assertEquals(true, actualResult);
    }

    @Test
    public void regex_IMAGE_PATTERN() {
        String regex = appContext.getString(R.string.IMAGE_PATTERN);
        String input = "![image](http://note.youdao.com/favicon.ico)";

        boolean actualResult = Pattern.matches(regex, input);
        assertEquals(true, actualResult);
    }
}
