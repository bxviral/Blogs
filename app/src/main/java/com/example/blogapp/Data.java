package com.example.blogapp;
import java.io.Serializable;
public class Data implements Serializable {
    String path, title, content;
    boolean selected;

    int realPos;

    public Data(String path, String title, String content, boolean selected, int realPos) {
        this.path = path;
        this.title = title;
        this.content = content;
        this.selected = selected;
        this.realPos = realPos;
    }

    public int getRealPos() {
        return realPos;
    }

    public void setRealPos(int realPos) {
        this.realPos = realPos;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
