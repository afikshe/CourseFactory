package com.example.coursefactory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class cpyFirebase {
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static FirebaseStorage storage = FirebaseStorage.getInstance();

    /*static ArrayList<CourseProfile> getCourseProfiles() {
        db.collection("courses")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    ArrayList<CourseProfile> courseNames = new ArrayList<>();
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        String name = documentSnapshot.getId();
                        courseNames.add(new CourseProfile(name));
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("firebase", "Error getting all course names: ", e);
                });
        return null;
    }*/

    /*static ArrayList<CourseProfile> getCourseProfilesArrayList(){
        ArrayList<CourseProfile> list = new ArrayList<>();
        db.collection("courses")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        QuerySnapshot querySnapshot = task.getResult();
                        for(DocumentSnapshot document : querySnapshot){
                            list.add(new CourseProfile(document.getId()));
                        }
                    }
                });
        return list;
    }*/

    /*public static LiveData<List<CourseProfile>> getCourseProfilesLiveData() {
        MutableLiveData<List<CourseProfile>> liveData = new MutableLiveData<>();
        db.collection("courses")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<CourseProfile> courseProfiles = new ArrayList<>();
                        QuerySnapshot querySnapshot = task.getResult();
                        for (DocumentSnapshot document : querySnapshot) {
                            courseProfiles.add(new CourseProfile(document.getId()));
                        }
                        liveData.setValue(courseProfiles);
                    } else {
                        // Handle error
                    }
                });
        return liveData;
    }*/

    /*public static List<String> getDocumentIdsFromCollection(String collectionName) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<String> documentIds = new ArrayList<>();

        db.collection(collectionName)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        for (DocumentSnapshot documentSnapshot : querySnapshot) {
                            documentIds.add(documentSnapshot.getId());
                        }
                    } else {
                        // Handle error here
                    }
                });

        return documentIds;
    }*/
    static ArrayList<CourseProfile> getCourseProfiles(){
        ArrayList<CourseProfile> list = new ArrayList<>();

        list.add(new CourseProfile("I3zq69Evg4uqiUV4roU2"));
        list.add(new CourseProfile("NtWCfXonSSHYgEa71gVT"));
        list.add(new CourseProfile("ZOpf5ccNEOSoXF5UOb3I"));
        list.add(new CourseProfile("qm0svv73KX8EXgGFa1VD"));
        list.add(new CourseProfile("t7AWRXSI7JMQofnX7H2t"));

        return list;
    }

    static String getCourseName(CourseProfile courseProfile) {
        return db.collection("courses").document(courseProfile.getCourseId()).get().getResult().getString("name");
    }

    static String getCourseDescription(CourseProfile courseProfile) {
        return db.collection("courses").document(courseProfile.getCourseId()).get().getResult().getString("description");
    }

    static String getCourseDetails(CourseProfile courseProfile) {
        return db.collection("courses").document(courseProfile.getCourseId()).get().getResult().getString("details");
    }

    static String getCourseImageUri(CourseProfile courseProfile) {
        return storage.getReference().child("Courses/" + courseProfile.getCourseId() + "/courseImage.png").getDownloadUrl().toString();
    }

}
