package com.example.wenwei.utils;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlParser {

    /**
     * 获取 html 中的纯文本, Convert html to plain text
     *
     * @param html origin html string
     * @return
     */
    public static String html2Text(String html) {
        Document doc = Jsoup.parse(html);
        return doc.body().text();
    }

    /**
     * 移除段落标签
     *
     * @param html HTML 文本
     * @return
     */
    public static String removeP(String html) {
        String result = html;
        if (result.contains("<p>") && result.contains("</p>")) {
            result = result.replace("<p>", "");
            result = result.replace("</p>", "<br>");
            while (result.endsWith("<br>")) {
                result = result.substring(0, result.length() - 4);
            }
        }
        return result;
    }
}
