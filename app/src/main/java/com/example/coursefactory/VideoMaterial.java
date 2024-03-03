package com.example.coursefactory;

public class VideoMaterial extends StudyMaterial{

    String url;

    public VideoMaterial(String title, String url) {
        super(title);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
