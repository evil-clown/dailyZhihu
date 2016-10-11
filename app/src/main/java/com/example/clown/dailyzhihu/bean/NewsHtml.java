package com.example.clown.dailyzhihu.bean;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clown on 2016/5/18.
 */
public class NewsHtml {

    public static final String HIDE_HEADER_STYLE = "<style>div.headline{display:none;}</style>";
    public static final String FORMAT_CSS_TAG = "<link rel=\\\"stylesheet\\\" type=\\\"text/css\\\" href=\\\"%s\\\"/>";
    public static final String FORMAT_JS_TAG = "<script src=\\\"%s\\\"></script>";
    public static final String MIME_TYPE = "text/html; charset=utf-8";
    public static final String ENCODING = "utf-8";

    private String body;
    private String title;
    private String image;
    private String share_url;
    private int type;
    private String id;
    private ArrayList<String> js;
    private ArrayList<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getJs(){
        if (js == null) {
            js.add("");
        }
            return js;
    }

    public void setJs(ArrayList<String> js) {
        this.js = js;
    }

    public ArrayList<String> getCss() {
        return css;
    }

    public void setCss(ArrayList<String> css) {
        this.css = css;
    }
    /*@Override
    public String toString() {
        return "body: " + getBody() + " ,title:" + getTitle() + " ,image: " + getImage()
                + " ,share_url: " + share_url + ", Type: " + getType() + " , id: " + getId()
                + ", Js: " + getJs() + ", Css: " + getCss();
    }

    public String createCssTag(String url) {
        return String.format(FORMAT_CSS_TAG, url);
    }


    public String createCssTag(List<String> urls) {
        StringBuilder sb = new StringBuilder();
        for (String url : urls) {
            sb.append(createCssTag(url));
        }

        return sb.toString();
    }


    public String createJsTag(String url) {
        return String.format(FORMAT_JS_TAG, url);
    }

    public String createJsTag(List<String> urls) {
        final StringBuilder sb = new StringBuilder();
        for (String url : urls) {
            sb.append(createJsTag(url));
        }
        return sb.toString();
    }

    private String createHtmlData(String html, String css, String js) {
        return css.concat(HIDE_HEADER_STYLE).concat(html).concat(js);
    }

    public String createHtmlData(NewsHtml newsHtml) {
        final String css = storyHtml.createCssTag(storyHtml.getCss());
        final String js = storyHtml.createJsTag(storyHtml.getJs());
        System.out.println(createHtmlData(storyHtml.getBody(), css, js));
        return createHtmlData(storyHtml.getBody(), css, js);
    }*/
}
