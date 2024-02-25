package com.example.coursefactory;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.grpc.Context;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String KEY_NAME = "name";
    private static final String KEY_SHORT_DESCRIPTION = "shortDescription";
    private static final String KEY_LONG_DESCRIPTION = "longDescription";

    ArrayList<CourseProfile> courseProfiles = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private String[] coursesId = {"ZOpf5ccNEOSoXF5UOb3I", "NtWCfXonSSHYgEa71gVT", "t7AWRXSI7JMQofnX7H2t", "qm0svv73KX8EXgGFa1VD", "I3zq69Evg4uqiUV4roU2"};
    Button logoutButton;
    TextView userNameTextView;

    private C_RecyclerViewAdapter adapter; // Declare the adapter as a field


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        userNameTextView = view.findViewById(R.id.userNameTextView);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        UserService.getUserById(userId)
                .addOnCompleteListener(task -> {
                    String userName = UserService.myUser.getName();
                    userNameTextView.setText(userName);
                });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        setUpCourseProfiles();

        adapter = new C_RecyclerViewAdapter(requireContext(), courseProfiles); // Initialize the adapter
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Find the logoutButton within the inflated layout
        logoutButton = view.findViewById(R.id.logoutButton);
        // Set click listener for logoutButton
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            UserService.myUser = null;
            startActivity(new Intent(getActivity(), Splash.class));
        });

        return view;
    }

    private void setUpCourseProfiles() {
        Query query = db.collection("courses");
        AggregateQuery countQuery = query.count();
        countQuery.get(AggregateSource.SERVER).addOnCompleteListener(new OnCompleteListener<AggregateQuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<AggregateQuerySnapshot> task) {
                AggregateQuerySnapshot snapshot = task.getResult();
                for (int i = 0; i < snapshot.getCount(); i++) {

                    final int index = i;
                    DocumentReference documentReference = db.collection("courses").document(coursesId[i]);
                    StorageReference storageReference = storage.getReference().child("Courses/"+ coursesId[index] +"/courseImage.png");
                    documentReference.get().addOnSuccessListener(documentSnapshot -> {
                        storageReference.getDownloadUrl().addOnSuccessListener(uri -> {

                        courseProfiles.add(new CourseProfile(documentSnapshot.getString(KEY_NAME),
                                documentSnapshot.getString(KEY_SHORT_DESCRIPTION),
                                documentSnapshot.getString(KEY_LONG_DESCRIPTION),
                                uri.toString()));
                        adapter.notifyDataSetChanged(); // Notify adapter of data change

                        });
                    });
                }
            }
        });
    }


}