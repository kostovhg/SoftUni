package com.softuni.exodia.domain.models.view;

public class DocumentDetailsViewModel {

    private String id;
    private String title;
    private String content;

    public DocumentDetailsViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
