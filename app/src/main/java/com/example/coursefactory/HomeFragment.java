package com.example.coursefactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    ArrayList<CourseProfile> courseProfiles = new ArrayList<>();
    Button logoutButton;
    TextView userNameTextView;

    // TODO: Rename parameter arguments, choose names that match

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        userNameTextView = view.findViewById(R.id.userNameTextView);
        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        UserService.getUserById(userId)
                .addOnCompleteListener(task -> {
                    String userName = UserService.myUser.getName();
                    userNameTextView.setText(userName);
                });

        /*FirestoreSyncManager.getDocumentIdsAsync("courses", new FirestoreSyncManager.FirestoreCallback<List<String>>() {
            @Override
            public void onSuccess(List<String> documentIds) {
                for (String docId : documentIds) {
                    courseProfiles.add(new CourseProfile(docId));
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("Firestore", "Error getting document IDs", e);
            }
        });*/

        courseProfiles = cpyFirebase.getCourseProfiles();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        C_RecyclerViewAdapter adapter = new C_RecyclerViewAdapter(requireContext(), courseProfiles); // Initialize the adapter
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


}