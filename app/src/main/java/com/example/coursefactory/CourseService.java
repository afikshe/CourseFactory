package com.example.coursefactory;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CourseService {

    static ArrayList<CourseProfile> allCourses;

    static Task<QuerySnapshot> getAllCoursesNew() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();

        ArrayList<CourseProfile> list = new ArrayList<>();
        ArrayList<Question> test = new ArrayList<>();

        return db.collection("courses").get().continueWithTask(task -> {
            List<Task<Void>> allTasks = new ArrayList<>();

            for (QueryDocumentSnapshot document : task.getResult()) {
                String courseId = document.getId();

                Task<Void> courseTask = db.collection("courses").document(courseId).get().continueWithTask(documentSnapshot -> {
                    String courseName = documentSnapshot.getResult().getString("name");
                    String details = documentSnapshot.getResult().getString("details");
                    String description = documentSnapshot.getResult().getString("description");
                    ArrayList<StudyMaterial> lessons = new ArrayList<>();
                    List<Task<Void>> lessonTasks = new ArrayList<>();
                    List<Task<Void>> testTasks = new ArrayList<>();

                        Task lessonTask = db.collection("courses").document(courseId).collection("lessons").get().continueWithTask(task1 -> {
                        for (QueryDocumentSnapshot lessonDoc : task1.getResult()) {
                            String lessonId = lessonDoc.getId();
                            Task<Void> lessonDownloadTask = db.collection("courses").document(courseId).collection("lessons").document(lessonId).get().continueWithTask(documentSnapshot1 -> {
                                String lessonName = documentSnapshot1.getResult().getString("name");
                                String lessonType = documentSnapshot1.getResult().getString("type");
                                if (lessonType.equals("video")) {
                                    return storage.getReference().child("Courses/" + courseId + "/" + lessonId + ".mp4").getDownloadUrl().onSuccessTask(uri -> {
                                        lessons.add(new VideoMaterial(lessonId, lessonName, uri.toString()));
                                        return Tasks.forResult(null);
                                    });
                                } else {
                                    return storage.getReference().child("Courses/" + courseId + "/" + lessonId + ".txt").getDownloadUrl().onSuccessTask(uri -> {
                                        lessons.add(new ReadMaterial(lessonId, lessonName, uri.toString()));
                                        return Tasks.forResult(null);
                                    });
                                }
                            });
                            lessonTasks.add(lessonDownloadTask);
                        }
                        return Tasks.whenAllComplete(lessonTasks);
                    });

                    Task testTask = db.collection("courses").document(courseId).collection("test").get().continueWithTask(task1 -> {
                        for (QueryDocumentSnapshot testDoc : task1.getResult()) {
                            String questionId = testDoc.getId();
                            Task<Void> questionTask = db.collection("courses").document(courseId).collection("test").document(questionId).get().continueWithTask(documentSnapshot1 -> {
                                String questionContent = documentSnapshot1.getResult().getString("content");
                                String questionType = documentSnapshot1.getResult().getString("type");
                                if ("multipleChoice".equals(questionType)) {
                                    String correctAnswer = documentSnapshot1.getResult().getString("correctAnswer");
                                    String option1 = documentSnapshot1.getResult().getString("option1");
                                    String option2 = documentSnapshot1.getResult().getString("option2");
                                    String option3 = documentSnapshot1.getResult().getString("option3");
                                    String option4 = documentSnapshot1.getResult().getString("option4");
                                    test.add(new MultipleChoiceQuestion(questionId, questionContent, correctAnswer, option1, option2, option3, option4));
                                } else {
                                    Boolean isTrue = documentSnapshot1.getResult().getBoolean("isTrue");
                                    test.add(new TrueFalseQuestion(questionId, questionContent, isTrue));
                                }
                                return null;
                            });
                            testTasks.add(questionTask);
                        }
                        return Tasks.whenAllComplete(testTasks);
                    });

                    ArrayList<Task<Object>> tasks = new ArrayList<>();
                    tasks.add(lessonTask);
                    tasks.add(testTask);
                    return Tasks.whenAll(tasks).continueWith(task2 -> {
                        storage.getReference().child("Courses/" + courseId + "/courseImage.png").getDownloadUrl().addOnSuccessListener(uri -> {
                            list.add(new CourseProfile(courseId, courseName, description, details, uri.toString(), lessons, test));
                        });
                        return null;
                    });
                });
                allTasks.add(courseTask);
            }

            return Tasks.whenAllComplete(allTasks).continueWith(task1 -> {
                allCourses = list;
                return null;
            });
        });

    }

    /*static Task<Void> setMyUser(UserProfile user){

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
    }*/

    /*static Task<Void> updateMyCourses(UserProfile user, String courseId){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference ref = database.getReference("users/" + userId + "/myCourses");

        HashMap<String, Object>
        return null;
    }*/
}
