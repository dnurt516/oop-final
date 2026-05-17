package utils;

import models.users.Manager;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class News implements Serializable {
    @Serial
    private static final long serialVersionUID = 2499767184756523306L;

    private String title;
    private String content;
    private Manager author;
    private Date publishedAt;
    private List<String> comments;

    public News() {
        this.publishedAt = new Date();
        this.comments = new ArrayList<>();
    }

    public News(String title, String content, Manager author) {
        this();
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Manager getAuthor() { return author; }
    public void setAuthor(Manager author) { this.author = author; }

    public Date getPublishedAt() { return publishedAt; }

    public List<String> getComments() { return comments; }

    public void addComment(String comment) {
        this.comments.add(comment);
    }

    @Override
    public String toString() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm");
        String formattedDate = sdf.format(publishedAt);

        return String.format(
                "=== %s ===\nAuthor: %s | Date: %s\n\n%s\n-------------------",
                title.toUpperCase(),
                (author != null ? author.getFirstName() + " " + author.getLastName() : "System"),
                formattedDate,
                content
        );
    }
}