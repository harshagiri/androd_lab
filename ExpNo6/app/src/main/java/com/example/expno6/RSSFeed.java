package com.example.expno6;

public class RSSFeed {
    private final String guid;
    private String title, description, pubDate, link;

    public RSSFeed(String title, String link, String description, String pubdate, String guid) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubdate;
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getLink() {
        return link;
    }
}
