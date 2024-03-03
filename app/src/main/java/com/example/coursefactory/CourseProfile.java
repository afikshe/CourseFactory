package com.example.coursefactory;

import android.net.Uri;

import java.util.ArrayList;

public class CourseProfile {
    String courseName;
    String courseDecription;
    String courseDetails;
    String imageUrl;
    ArrayList<Section> sections;

    public CourseProfile(String courseName, String courseDecription, String courseDetails, String imageUrl, ArrayList<Section> sections) {
        this.courseName = courseName;
        this.courseDecription = courseDecription;
        this.courseDetails = courseDetails;
        this.imageUrl = imageUrl;
        this.sections = sections;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDecription() {
        return courseDecription;
    }

    public String getCourseDetails() {
        return courseDetails;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }
}
