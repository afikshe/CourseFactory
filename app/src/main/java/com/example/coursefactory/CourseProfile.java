package com.example.coursefactory;

public class CourseProfile {
    String courseName;
    String courseShortDecription;
    String courseDecription;
    int image;

    public CourseProfile(String courseName, String courseShortDecription, String courseLongDecription, int image) {
        this.courseName = courseName;
        this.courseShortDecription = courseShortDecription;
        this.courseDecription = courseLongDecription;
        this.image = image;
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

    public int getImage() {
        return image;
    }
}
