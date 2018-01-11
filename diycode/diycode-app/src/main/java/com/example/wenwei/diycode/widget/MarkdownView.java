package com.example.wenwei.diycode.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wenwei.diycode.activity.WebActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MarkdownView extends WebView {
    private static final String TAG = MarkdownView.class.getSimpleName();

    private String mPreviewText;

    private final Paint mPaint = new Paint();

    public MarkdownView(Context context) {
        this(context, null);
    }

    public MarkdownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarkdownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }

        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);

        initialize(context);
    }

    /**
     * Initialize the webview
     * 首先加载 markdown 显示模板 preview.html
     */
    private void initialize(Context context) {
        loadUrl("file:///android_asset/html/preview.html");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        setWebChromeClient(new MarkdownWebChromeClient());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isInEditMode()) {
            canvas.drawColor(Color.WHITE);
            canvas.translate(canvas.getWidth() / 2, 30);

            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setTextSize(30);
            mPaint.setColor(Color.GRAY);
            canvas.drawText("MarkdownView", -30, 0, mPaint);
        }
    }

    public void loadMarkdownFromFile(Context context, File markdownFile) {
        String mdText = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(markdownFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String readText;
            StringBuilder stringBuilder = new StringBuilder();
            while ((readText = bufferedReader.readLine()) != null) {
                stringBuilder.append(readText);
                stringBuilder.append("\n");
            }
            fileInputStream.close();
            mdText = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "FileNotFoundException:" + e);
        } catch (IOException e) {
            Log.e(TAG, "IOException:" + e);
        }
        setMarkDownText(context, mdText);
    }

    public void loadMarkdownFromAssets(Context context, String assetsFilePath) {
        try {
            StringBuilder buf = new StringBuilder();
            InputStream json = getContext().getAssets().open(assetsFilePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                buf.append(str).append("\n");
            }
            in.close();
            setMarkDownText(context, buf.toString());
        } catch (IOException e) {
            Log.e(TAG, "IOException:" + e);
        }
    }

    public void setMarkDownText(Context context, String markDownText) {
        String escMdText = escapeForText(markDownText);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            mPreviewText = String.format("javascript:preview('%s')", escMdText);
        } else {
            mPreviewText = String.format("preview('%s')", escMdText);
        }

        initialize(context);
    }

    /**
     * 格式化 escape
     *
     * @param mdText Markdown 文本
     * @return
     */
    private String escapeForText(String mdText) {
        String escText = mdText.replace("\n", "\\\\n");
        escText = escText.replace("'", "\\\'");
        escText = escText.replace("\r", "");
        return escText;
    }

    /**
     * Markdown WebChromeClient
     */
    private class MarkdownWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    loadUrl(mPreviewText);
                } else {
                    evaluateJavascript(mPreviewText, null);
                }
            }
        }
    }
}
