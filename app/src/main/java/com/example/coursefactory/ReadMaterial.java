package com.example.coursefactory;

public class ReadMaterial extends StudyMaterial{

    String content;

    public ReadMaterial(String lessonId, String title, String content) {
        super(lessonId, title);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
