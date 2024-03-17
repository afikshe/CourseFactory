package com.example.coursefactory;

public class VideoMaterial extends StudyMaterial{

    String url;

    public VideoMaterial(String lessonId, String title, String url) {
        super(lessonId, title);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
