package com.example.coursefactory;

public class ReadMaterial extends StudyMaterial{

    String content;

    public ReadMaterial(String title, String content) {
        super(title);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
