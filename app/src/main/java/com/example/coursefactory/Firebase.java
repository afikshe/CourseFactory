package com.example.coursefactory;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;


import java.util.ArrayList;

public class Firebase {
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static FirebaseStorage storage = FirebaseStorage.getInstance();

    static ArrayList<CourseProfile> getCourseProfiles() {
רר

//        db.collection("courses").get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                list.add(new CourseProfile(document.getId()));
//                            }
//                        } else {
//                            Log.d("Firestore", "Error getting documents: ", task.getException());
//                        }
//
//                    }
//                });
//        return list;
//
////        db.collection("courses").get()
////                .addOnSuccessListener(task -> {
////                    for (QueryDocumentSnapshot document : task) {
////                        String tmpCourseId = document.getId();
////                        list.add(new CourseProfile(tmpCourseId));
////                    }
////                })
////                .addOnFailureListener(task->{
////                    //Log.d("Firestore", "Error getting documents: ", task.getException());
////                    Log.d("Firestore", "Error getting documents: ");
//                });

    }

    static String getCourseName(CourseProfile courseProfile){
        return db.collection("courses").document(courseProfile.getCourseId()).get().getResult().getString("name");
    }

    static String getCourseDescription(CourseProfile courseProfile){
        return  db.collection("courses").document(courseProfile.getCourseId()).get().getResult().getString("description");
    }

    static String getCourseDetails(CourseProfile courseProfile){
        return db.collection("courses").document(courseProfile.getCourseId()).get().getResult().getString("details");
    }

    static String getCourseImageUri (CourseProfile courseProfile){
        return storage.getReference().child("Courses/" + courseProfile.getCourseId() + "/courseImage.png").getDownloadUrl().toString();
    }

}
