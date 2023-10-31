package org.example.article.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Article {
    private long id;
    private String content;
    private String author;
    public void article (long id, String content, String author/*String userId*/) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}
