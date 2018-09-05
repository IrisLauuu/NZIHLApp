package com.example.johke.nzihl;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {
    private String title;
    private String description;
    private String category;
    private String creator;
    private String pubDate;
    private String image;
    private String link;
    private String content;
    private int index;
    private int total;

    public Article(String title, String description, String category, String creator, String pubDate, String image, String link, String content, int index, int total) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.creator = creator;
        this.pubDate = pubDate;
        this.image = image;
        this.link = link;
        this.content = content;
        this.index = index;
        this.total = total;
    }

    private Article(Parcel in) {
        title = in.readString();
        description = in.readString();
        category = in.readString();
        creator = in.readString();
        pubDate = in.readString();
        image = in.readString();
        link = in.readString();
        content = in.readString();
        index = in.readInt();
        total = in.readInt();
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

    public String getContent() {
        return content;
    }

    public int getIndex() {
        return index;
    }

    public int getTotal() {
        return total;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(description);
        out.writeString(category);
        out.writeString(creator);
        out.writeString(pubDate);
        out.writeString(image);
        out.writeString(link);
        out.writeString(content);
        out.writeInt(index);
        out.writeInt(total);
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
