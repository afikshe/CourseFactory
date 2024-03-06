package com.example.coursefactory;

import android.media.Image;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class UserService {

    static UserProfile myUser;

    static Task<Void> setMyUser(UserProfile user) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference ref = database.getReference("users/" + userId);

        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("Name", user.getName());
        return ref.setValue(userMap).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                myUser = user;
            }
        });
    }

    static Task<UserProfile> getUserById(String userId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users/" + userId);

        return ref.get().continueWith(task -> {
            if (task.isSuccessful()) {
                String name = task.getResult().child("Name").getValue(String.class);
                UserProfile profile = new UserProfile(name);

                if (Objects.equals(userId, FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                    myUser = profile;
                }
                return profile;
            } else {
                throw task.getException();
            }
        });
    }
}
