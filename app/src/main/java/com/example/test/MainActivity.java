package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    protected int h = 0;
    protected long g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<String> pencils = new ArrayList<>();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        Button buttonSave = findViewById(R.id.buttonSave);
        EditText editName = findViewById(R.id.editName);
        EditText editView = findViewById(R.id.editView);
        EditText editThickness = findViewById(R.id.editThickness);

        mDatabase.child("Pencil").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    for (DataSnapshot ds : task.getResult().getChildren()){
                        h++;
                        Pencil pencil = ds.getValue(Pencil.class);
                        pencils.add("Pencil" + h + ": " + pencil.Name + " " + pencil.View + " " + pencil.WritingLineThickness);

                    }


                    ListView listView = findViewById(R.id.listView);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, pencils);
                    listView.setAdapter(adapter);
                }
            }
        });

        mDatabase.child("Pencil").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    g = task.getResult().getChildrenCount();
                }
            }
        });



        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                g++;
                mDatabase.child("Pencil").child("pencil" + g).child("Name").setValue(editName.getText().toString());
                mDatabase.child("Pencil").child("pencil" + g).child("View").setValue(editView.getText().toString());
                mDatabase.child("Pencil").child("pencil" + g).child("WritingLineThickness").setValue(editThickness.getText().toString());

                mDatabase.child("Pencil").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()){

                            h = 0;
                            pencils.clear();
                            for (DataSnapshot ds : task.getResult().getChildren()){

                                h++;
                                Pencil pencil = ds.getValue(Pencil.class);

                                pencils.add("Pencil" + h + ": " + pencil.Name+ " " + pencil.View + " " + pencil.WritingLineThickness);
                            }

                            ListView listView = findViewById(R.id.listView);
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, pencils);
                            listView.setAdapter(adapter);
                        }
                    }
                });


            }
        });

    }
}