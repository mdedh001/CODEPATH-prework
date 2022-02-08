package com.example.simpletodo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    EditText editText
    Button btnsave

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editText = findViewById(R.id.editText);
        btnsave = findViewById(R.id.btnsave);
        getSupportActionBar().setTitle("edit items");
}}
