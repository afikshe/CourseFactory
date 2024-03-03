package com.example.coursefactory;

import java.util.ArrayList;

public class Section {

    String name;
    ArrayList<StudyMaterial> materials;
    ArrayList<Question> test;

    public Section(String name, ArrayList<StudyMaterial> materials, ArrayList<Question> test) {
        this.name = name;
        this.materials = materials;
        this.test = test;
    }

    public String getName() {
        return name;
    }

    public ArrayList<StudyMaterial> getMaterials() {
        return materials;
    }

    public ArrayList<Question> getTest() {
        return test;
    }
}
