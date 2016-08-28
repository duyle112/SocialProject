package com.english.howf;

/**
 * Created by HoangDuy on 27/08/2016.
 */
public class Items  {
    private String title;
    private int icon;

    public Items(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
