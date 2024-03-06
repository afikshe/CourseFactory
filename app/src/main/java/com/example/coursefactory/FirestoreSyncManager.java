//package com.example.coursefactory;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
////import com.google.api.core.ApiFuture;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class FirestoreSyncManager {
//    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
//    //private static final Executor executor = Executors.newSingleThreadExecutor();
//
//
//    public List<String> getDocumentIds(String collectionName) {
//        //ApiFuture<QuerySnapshot> query = db.collection("courses").get();
//        //QuerySnapshot query = db.collection(collectionName).get().getResult();
//        //QuerySnapshot querySnapshot = query.get();
//
//        //query = db.collection("courses").get();
//        return  ["assa","asassdf"];
//        return null;
//    }
//
//    public static void getDocumentIdsAsync(String collectionName, final FirestoreCallback<List<String>> callback) {
//        final List<String> documentIds = new ArrayList<>();
//        CollectionReference collectionReference = db.collection(collectionName);
//        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                    documentIds.add(documentSnapshot.getId());
//                }
//                callback.onSuccess(documentIds);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                callback.onFailure(e);
//            }
//        });
//    }
//    public interface FirestoreCallback<T> {
//            void onSuccess(T result);
//            void onFailure(Exception e);
//    }*/
//
//    public  String getCourseName(String courseId) {
//        final String[] courseName = {null};
//        DocumentReference documentReference = db.collection("courses").document(courseId);
//        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if (documentSnapshot.exists()) {
//                    courseName[0] = documentSnapshot.getString("name");
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                // Handle error here
//            }
//        });
//        return courseName[0];
//    }
//
//}