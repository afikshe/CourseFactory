package com.example.coursefactory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.auth.User;

public class Signup extends AppCompatActivity {

    EditText emailEditText, nameEditText, passwordEditText;
    Button continueButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailEditText = findViewById(R.id.emailEditText);
        nameEditText = findViewById(R.id.nameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(v -> signUp());

        loginButton = findViewById(R.id.openLoginButton);
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
        });
    }

    private void signUp(){
        String email = emailEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        UserProfile userProfile = new UserProfile("null", name, email, "null", 0);

        if(email.isEmpty() || name.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter email, name & password",  Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener( task -> {
            if(task.isSuccessful()){
                userProfile.setUserId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                UserService.setMyUser(userProfile).addOnCompleteListener(task1 -> {
                    if(task.isSuccessful()){
                        Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Signup.this, Splash.class));
                    }else {
                        Toast.makeText(this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }else {
                Toast.makeText(this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}