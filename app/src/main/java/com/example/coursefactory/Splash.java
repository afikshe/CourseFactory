package com.example.coursefactory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CourseService.getAllCoursesNew();

        new Handler().postDelayed(() ->{
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if(currentUser != null){

                        startActivity(new Intent(Splash.this, MainActivity.class));

            }else {
                startActivity(new Intent(Splash.this, Login.class));
            }
            finish();
        }, 3000);
    }
}