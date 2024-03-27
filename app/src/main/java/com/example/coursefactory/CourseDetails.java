package com.example.coursefactory;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

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

        //TODO
        addButton.setOnClickListener(v ->{

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            if(addButton.getText().equals("Add to my courses")){

                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put(CourseService.allCourses.get(position).getCourseId(), null);
                db.collection("myCourses").document("{userId}").set(userMap);
                addButton.setText("Remove from my courses");
                addButton.setBackground(getResources().getDrawable(R.drawable.cancel_button));

            }else{

                AlertDialog builder = new AlertDialog
                        .Builder(this).
                        setTitle("Remove course")
                        .setMessage("Are you sure you want to remove the course from 'my courses' ?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                HashMap<String, Object> userMap = new HashMap<>();
                                userMap.put(CourseService.allCourses.get(position).getCourseId(), FieldValue.delete());
                                db.collection("myCourses").document("{userId}").update(userMap);
                                addButton.setText("Add to my courses");
                                addButton.setBackground( getResources().getDrawable(R.drawable.button));
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
            }
        });
    }
}