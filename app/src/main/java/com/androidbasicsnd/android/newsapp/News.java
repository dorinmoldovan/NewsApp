package com.androidbasicsnd.android.newsapp;

public class News {

    private String title;
    private String section;
    private String author;
    private String date;
    private String thumbnail;
    private String url;

    public News(String title, String section, String author, String date, String thumbnail, String url) {
        this.title = title;
        this.section = section;
        this.author = author;
        this.date = date;
        this.thumbnail = thumbnail;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
