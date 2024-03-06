package com.example.coursefactory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CourseDetail extends AppCompatActivity {

    TextView courseNameTextView, courseDescriptionTextView;
    ImageView coursePictureImageView;
    Button addCourseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        courseNameTextView = findViewById(R.id.courseNameTextView);
        courseDescriptionTextView = findViewById(R.id.courseDescriptionTextView);
        coursePictureImageView = findViewById(R.id.coursePictureImageView);
        addCourseButton = findViewById(R.id.addCourseButton);


    }

    void uploadCourse(CourseProfile courseProfile) {

    }
}