package com.example.coursefactory;

public class VideoMaterial extends StudyMaterial{

    String url;

    public VideoMaterial(String title, String description, String url) {
        super(title, description);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
