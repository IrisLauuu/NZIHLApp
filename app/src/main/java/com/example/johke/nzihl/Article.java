package com.example.johke.nzihl;

public class Article {
    private String title;
    private String description;
    private String category;
    private String creator;
    private String pubDate;
    private String image;
    private String link;

    public Article(String title, String description, String category, String creator, String pubDate, String image, String link) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.creator = creator;
        this.pubDate = pubDate;
        this.image = image;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getCreator() {
        return creator;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }
}
