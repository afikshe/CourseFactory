package com.example.coursefactory;

public class StudyMaterial {

    String lessonId, title;

    public StudyMaterial(String lessonId, String title) {
        this.lessonId = lessonId;
        this.title = title;
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getTitle() {
        return title;
    }
}
