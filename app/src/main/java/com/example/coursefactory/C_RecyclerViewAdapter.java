package com.example.coursefactory;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class C_RecyclerViewAdapter extends RecyclerView.Adapter<C_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<CourseProfile> courseProfiles;


    public C_RecyclerViewAdapter(Context context, ArrayList<CourseProfile> courseProfiles){
        this.context = context;
        this.courseProfiles = courseProfiles;
    }

    @NonNull
    @Override
    public C_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new C_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull C_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_row layout file
        // based on the position of the recycler view

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        holder.courseNameTextView.setText(Firebase.getCourseName(courseProfiles.get(position)));
        holder.courseDescriptionTextView.setText(Firebase.getCourseDescription(courseProfiles.get(position)));
        Picasso.get().load(Firebase.getCourseImageUri(courseProfiles.get(position))).resize(0, 550).into(holder.coursePictureImageView);
    }

    @Override
    public int getItemCount() {
        // the recycler view just wants to know the number of items you want displayed
        return courseProfiles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing the views from our recycler_view_row layout file
        // Kinda like in the onCreate method

        ImageView coursePictureImageView;
        TextView courseNameTextView, courseDescriptionTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            coursePictureImageView = itemView.findViewById(R.id.coursePictureImageView);
            courseNameTextView = itemView.findViewById(R.id.courseNameTextView);
            courseDescriptionTextView = itemView.findViewById(R.id.descriptionTextView);

        }
    }
}
