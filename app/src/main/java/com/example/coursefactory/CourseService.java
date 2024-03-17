package com.example.coursefactory;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class CourseService {

    static ArrayList<CourseProfile> allCourses;

    static Task<QuerySnapshot> getAllCourses(/*ArrayList<String> courseIds*/) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        ArrayList<CourseProfile> list = new ArrayList<>();


        return db.collection("courses").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {

                    String courseId = document.getId();

                    db.collection("courses").document(document.getId()).get().addOnSuccessListener(documentSnapshot -> {
                        db.collection("courses").document(document.getId()).collection("lessons").get().addOnSuccessListener(documentSnapshot1 -> {
                            db.collection("courses").document(document.getId()).collection("test").get().addOnSuccessListener(documentSnapshot2 -> {

                            });
                        });
                    });

                    String courseName = db.collection("courses").document(document.getId()).get().getResult().getString("name");
                    String courseDescription = db.collection("courses").document(document.getId()).get().getResult().getString("description");
                    String courseDetails = db.collection("courses").document(document.getId()).get().getResult().getString("details");
                    ArrayList<StudyMaterial> lessons = new ArrayList<>();
                    ArrayList<Question> test = new ArrayList<>();

                    db.collection("courses").document(document.getId()).collection("lessons").get().addOnCompleteListener(task1 -> {

                        for (QueryDocumentSnapshot documentSnapshot : task1.getResult()) {
                            if (db.collection("courses").document(document.getId()).collection("lessons").document(documentSnapshot.getId()).get().getResult().getString("type") == "video") {
                                String lessonId = documentSnapshot.getId();
                                String title = db.collection("courses").document(document.getId()).collection("lessons").document(documentSnapshot.getId()).get().getResult().getString("name");
                                storage.getReference().child("Courses/" + document.getId() + "/" + documentSnapshot.getId() + ".mp4").getDownloadUrl().addOnSuccessListener(uri -> {
                                    lessons.add(new VideoMaterial(lessonId, title, uri.toString()));
                                });
                            } else {
                                String lessonId = documentSnapshot.getId();
                                String title = db.collection("courses").document(document.getId()).collection("lessons").document(documentSnapshot.getId()).get().getResult().getString("name");
                                storage.getReference().child("Courses/" + document.getId() + "/" + documentSnapshot.getId() + ".txt").getDownloadUrl().addOnSuccessListener(uri -> {
                                    lessons.add(new ReadMaterial(lessonId, title, uri.toString()));
                                });
                            }

                        }
                    });


                    db.collection("courses").document(document.getId()).collection("test").get().addOnCompleteListener(task1 -> {

                        for (QueryDocumentSnapshot documentSnapshot : task1.getResult()) {
                            if (db.collection("courses").document(document.getId()).collection("test").document(documentSnapshot.getId()).get().getResult().getString("type") == "multipleChoice") {
                                String questionId = documentSnapshot.getId();
                                String questionContent = db.collection("courses").document(document.getId()).collection("test").document(documentSnapshot.getId()).get().getResult().getString("content");
                                String correctAnswer = db.collection("courses").document(document.getId()).collection("test").document(documentSnapshot.getId()).get().getResult().getString("correctAnswer");
                                String option1 = db.collection("courses").document(document.getId()).collection("test").document(documentSnapshot.getId()).get().getResult().getString("option1");
                                String option2 = db.collection("courses").document(document.getId()).collection("test").document(documentSnapshot.getId()).get().getResult().getString("option2");
                                String option3 = db.collection("courses").document(document.getId()).collection("test").document(documentSnapshot.getId()).get().getResult().getString("option3");
                                String option4 = db.collection("courses").document(document.getId()).collection("test").document(documentSnapshot.getId()).get().getResult().getString("option4");

                                test.add(new MultipleChoiceQuestion(questionId, questionContent, correctAnswer, option1, option2, option3, option4));
                            } else {
                                String questionId = documentSnapshot.getId();
                                String questionContent = db.collection("courses").document(document.getId()).collection("test").document(documentSnapshot.getId()).get().getResult().getString("content");
                                Boolean isTrue = db.collection("courses").document(document.getId()).collection("test").document(documentSnapshot.getId()).get().getResult().getBoolean("isTrue");

                                test.add(new TrueFalseQuestion(questionId, questionContent, isTrue));
                            }
                        }
                    });


                    storage.getReference().child("Courses/" + document.getId() + "/courseImage.png").getDownloadUrl().addOnSuccessListener(uri -> {
                        list.add(new CourseProfile(courseId, courseName, courseDescription, courseDetails, uri.toString(), lessons, test));
                    });

                }
            }
            allCourses = list;
        });
    }

    static Task<QuerySnapshot> getAllCoursesNew() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();

        ArrayList<CourseProfile> list = new ArrayList<>();
        ArrayList<Question> test = new ArrayList<>();

        return db.collection("courses").get().addOnCompleteListener(task -> {

            for (QueryDocumentSnapshot document : task.getResult()) {

                String courseId = document.getId();

                db.collection("courses").document(courseId).get().addOnSuccessListener(documentSnapshot -> {

                    String courseName = documentSnapshot.getString("name");
                    String details = documentSnapshot.getString("description");
                    String description = documentSnapshot.getString("description");
                    ArrayList<StudyMaterial> lessons = new ArrayList<>();

                    db.collection("courses").document(courseId).collection("lessons").get().addOnCompleteListener(task1 -> {

                        for (QueryDocumentSnapshot document1 : task1.getResult()) {

                            String lessonId = document1.getId();

                            db.collection("courses").document(courseId).collection("lessons").document(lessonId).get().addOnSuccessListener(documentSnapshot1 -> {

                                String lessonName = documentSnapshot1.getString("name");
                                String lessonType = documentSnapshot1.getString("type");

                                if (lessonType == "video") {


                                    storage.getReference().child("Courses/" + courseId + "/" + lessonId + ".mp4").getDownloadUrl().addOnSuccessListener(uri -> {
                                        lessons.add(new VideoMaterial(lessonId, lessonName, uri.toString()));
                                    });
                                } else {

                                    storage.getReference().child("Courses/" + courseId + "/" + lessonId + ".txt").getDownloadUrl().addOnSuccessListener(uri -> {
                                        lessons.add(new ReadMaterial(lessonId, lessonName, uri.toString()));
                                    });
                                }
                            });

                        }

                    });

                    db.collection("courses").document(courseId).collection("test").get().addOnCompleteListener(task1 -> {

                        for (QueryDocumentSnapshot document1 : task1.getResult()) {

                            String questionId = document1.getId();

                            db.collection("courses").document(courseId).collection("test").document(questionId).get().addOnSuccessListener(documentSnapshot1 ->{

                               String questionContent = documentSnapshot1.getString("content");
                               String questionType = documentSnapshot1.getString("type");

                               if(questionType == "multipleChoice"){

                                   String correctAnswer = documentSnapshot1.getString("correctAnswer");
                                   String option1 = documentSnapshot1.getString("option1");
                                   String option2 = documentSnapshot1.getString("option2");
                                   String option3 = documentSnapshot1.getString("option3");
                                   String option4 = documentSnapshot1.getString("option4");

                                   test.add(new MultipleChoiceQuestion(questionId, questionContent, correctAnswer, option1, option2, option3, option4));
                               }else {

                                   Boolean isTrue = documentSnapshot1.getBoolean("isTrue");

                                   test.add(new TrueFalseQuestion(questionId, questionContent, isTrue));
                               }
                            });
                        }

                    });

                    storage.getReference().child("Courses/"+ courseId +"/courseImage.png").getDownloadUrl().addOnSuccessListener(uri -> {

                        list.add(new CourseProfile(courseId, courseName, description, details, uri.toString(), lessons, test));

                    });

                });



            }

            allCourses = list;
        });
    }
}
