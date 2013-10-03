package com.mvc.util;

import org.rythmengine.template.ITag;
import org.rythmengine.template.JavaTagBase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Html2StringTag extends JavaTagBase {
    @Override
    protected void call(__ParameterList parameters, __Body body) {
        p(htmlToText(parameters.getDefault().toString()));
    }

    @Override
    public String __getName() {
        return "html2string";
    }

    public static String htmlToText(String inputString) {
        String htmlStr = inputString;
        String textStr = "";
        String scriptRegEx = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
        String styleRegEx = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
        String htmlRegEx1 = "<[^>]*>";
        String htmlRegEx2 = "<[^>]*";
        try {
            Pattern scriptPattern = Pattern.compile(scriptRegEx, Pattern.CASE_INSENSITIVE);
            Matcher scriptMatcher = scriptPattern.matcher(htmlStr);
            htmlStr = scriptMatcher.replaceAll("");
            Pattern stylePattern = Pattern.compile(styleRegEx, Pattern.CASE_INSENSITIVE);
            Matcher styleMatcher = stylePattern.matcher(htmlStr);
            htmlStr = styleMatcher.replaceAll("");
            Pattern htmlPattern1 = Pattern.compile(htmlRegEx1, Pattern.CASE_INSENSITIVE);
            Matcher htmlMatcher1 = htmlPattern1.matcher(htmlStr);
            htmlStr = htmlMatcher1.replaceAll("");
            Pattern htmlPattern2 = Pattern.compile(htmlRegEx2, Pattern.CASE_INSENSITIVE);
            Matcher htmlMatcher2 = htmlPattern2.matcher(htmlStr);
            htmlStr = htmlMatcher2.replaceAll("");
            textStr = htmlStr;
        } catch (Exception e) {
            System.err.println("->htmlToText(String inputString):" + e.getMessage());
        }
        textStr = textStr.replaceAll("&acute;", "\'");
        textStr = textStr.replaceAll("&quot;", "\"");
        textStr = textStr.replaceAll("&lt;", "<");
        textStr = textStr.replaceAll("&gt;", ">");
        textStr = textStr.replaceAll("&nbsp;", " ");
        textStr = textStr.replaceAll("&amp;", "&");
        return textStr;
    }

}
