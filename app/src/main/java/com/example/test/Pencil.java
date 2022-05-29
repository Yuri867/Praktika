package com.example.test;

public class Pencil {

    public String Name;
    public String View;
    public String WritingLineThickness;

    public Pencil() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Pencil(String Name, String View, String WritingLineThickness) {
        this.Name = Name;
        this.View = View;
        this.WritingLineThickness = WritingLineThickness;
    }

}


