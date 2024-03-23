package com.example.coursefactory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class CourseDetails extends AppCompatActivity {

    TextView courseNameTextView, courseDetailsTextView;
    Button returnButton , addButton;
    ImageView courseImageImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        courseNameTextView = findViewById(R.id.courseNameTextView);
        courseDetailsTextView = findViewById(R.id.courseDetailsTextView);
        returnButton = findViewById(R.id.returnButton);
        addButton = findViewById(R.id.addButton);
        courseImageImageView = findViewById(R.id.courseImageImageView);

        Integer position = getIntent().getIntExtra("POSITION", 0);

        courseNameTextView.setText(CourseService.allCourses.get(position).getCourseName());
        courseDetailsTextView.setText(CourseService.allCourses.get(position).getCourseDetails());

        Picasso.get().load(CourseService.allCourses.get(position).getImageUrl()).resize(0, 550).into(courseImageImageView);

        returnButton.setOnClickListener(v ->{
            startActivity(new Intent(CourseDetails.this, MainActivity.class));
        });
    }
}