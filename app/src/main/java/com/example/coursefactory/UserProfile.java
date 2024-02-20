package com.example.coursefactory;

import android.media.Image;

public class UserProfile {

    String userId, name, email, password;
    Image profilePicture;

    public UserProfile(String userId, String name, String email, String password, Image profilePicture) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public UserProfile(String name) {
        this.name = name;
        this.profilePicture = profilePicture;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }
}
