package com.example.coursefactory;

import android.net.Uri;

public class CourseProfile {
    String courseName;
    String courseShortDecription;
    String courseDecription;
    String imageUrl;

    public CourseProfile(String courseName, String courseShortDecription, String courseLongDecription, String imageUrl) {
        this.courseName = courseName;
        this.courseShortDecription = courseShortDecription;
        this.courseDecription = courseLongDecription;
        this.imageUrl = imageUrl;
    }


    public String getCourseName() {
        return courseName;
    }

    public String getCourseShortDecription() {
        return courseShortDecription;
    }

    public String getCourseDecription() {
        return courseDecription;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
