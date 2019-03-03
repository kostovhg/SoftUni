package com.softuni.exodia.domain.models.view;

public class DocumentViewModel {

    private String id;
    private String title;
    private String content;

    public DocumentViewModel() {
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
        this.title = title.length() > 12 ?
            title.substring(0, 12) + " ..." :
                title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
