package com.example.coursefactory;

public class ReadMaterial extends StudyMaterial{

    String content;

    public ReadMaterial(String title, String description, String content) {
        super(title, description);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
