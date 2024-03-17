package com.example.coursefactory;

import android.net.Uri;

import java.util.ArrayList;

public class CourseProfile {
    String courseId, courseName, courseDescription, courseDetails, imageUrl;
    ArrayList<StudyMaterial> lessons;
    ArrayList<Question> test;


    public CourseProfile(String courseId, String courseName, String courseDescription, String courseDetails, String imageUrl, ArrayList<StudyMaterial> lessons, ArrayList<Question> test) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseDetails = courseDetails;
        this.imageUrl = imageUrl;
        this.lessons = lessons;
        this.test = test;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getCourseDetails() {
        return courseDetails;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<StudyMaterial> getLessons() {
        return lessons;
    }

    public ArrayList<Question> getTest() {
        return test;
    }
}
