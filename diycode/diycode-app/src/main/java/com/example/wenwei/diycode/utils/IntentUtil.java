package com.example.wenwei.diycode.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.wenwei.diycode.utils.customtabs.CustomTabsHelper;


public class IntentUtil {


    /**
     * 打开链接
     * 根据设置判断是用那种方式打开
     *
     * @param context 上下文
     * @param url     url
     */
    public static void openUrl(Context context, String url) {
        // TODO Chrome Custom Tabs
        if (null == url || url.isEmpty()) {
            Log.i("Diyocde", "Url地址错误");
            return;
        }
        CustomTabsHelper.openUrl(context, url);
    }

    /**
     * 分享链接
     *
     * @param context 上下文
     * @param subject 主题
     * @param url     要分享的链接
     */
    public static void shareUrl(Context context, String subject, String url) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
        shareIntent.setType("text/plain");
        context.startActivity(shareIntent);
    }

    /**
     * 打开支付宝
     */
    public static void openAlipay(Context context) {
        if (AppUtil.isAvailable(context, "com.eg.android.AlipayGphone")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String QRCode = "HTTPS://QR.ALIPAY.COM/FKX07101FYSJGTNCAPQW39";
            intent.setData(Uri.parse("alipayqr://platformapi/startapp?saId=10000007&qrcode=" + QRCode));
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "你没有捐赠的权限", Toast.LENGTH_SHORT).show();
        }
    }
}
