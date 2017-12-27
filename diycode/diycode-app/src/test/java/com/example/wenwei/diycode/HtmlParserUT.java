package com.example.wenwei.diycode;

import com.example.wenwei.diycode.utils.HtmlParser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class HtmlParserUT {
    @Test
    public void html2Text_default() {
        String originHtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<script>\n" +
                "function myFunction() {\n" +
                "    document.getElementById(\"demo\").innerHTML = \"Paragraph changed.\";\n" +
                "}\n" +
                "</script>\n" +
                "<style>\n" +
                "    img { max-width: 100%; }\n" +
                "</style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<h2>JavaScript in Head.</h2>\n" +
                "\n" +
                "<p id=\"demo\">A Paragraph.</p>\n" +
                "\n" +
                "<button type=\"button\" onclick=\"myFunction()\">Try it</button>\n" +
                "\n" +
                "</body>\n" +
                "</html> ";
        String expectedText = "JavaScript in Head. A Paragraph. Try it";
        String actualText = HtmlParser.html2Text(originHtml);
        assertEquals(expectedText, actualText);
    }

    @Test
    public void html2Text_simple() {
        String originHtml = "<html>" +
                "<head>" +
                "<title>" +
                "A Simple HTML Document" +
                "</title>" +
                "</head>" +
                "<body>" +
                "<p>This is a very simple HTML document</p>" +
                "<p>It only has two paragraphs</p>" +
                "</body>" +
                "</html>";
        String expectedText = "This is a very simple HTML document It only has two paragraphs";
        String actualText = HtmlParser.html2Text(originHtml);
        assertEquals(expectedText, actualText);
    }
}
