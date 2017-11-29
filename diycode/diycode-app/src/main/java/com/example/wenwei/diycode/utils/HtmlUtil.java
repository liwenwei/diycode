package com.example.wenwei.diycode.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {

    /**
     * 获取 html 中的纯文本
     *
     * @param inputString
     * @return
     */
    public static String html2Text(String inputString) {
        String htmlStr = inputString;
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;
        Pattern p_html1;
        Matcher m_html1;

        try {
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String regEx_html1 = "<[^>]+";

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("");  // 过滤html标签

            p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll(htmlStr);       // 过滤html标签

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return htmlStr;
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
