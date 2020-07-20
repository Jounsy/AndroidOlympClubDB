package com.snowman.sportclubolymp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddMemberActivity extends AppCompatActivity {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText groupEditText;
    private Spinner genderSpinner;
    private int gender = 0;
    private ArrayAdapter spinnerAdapter;
    private ArrayList spinnerArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        firstNameEditText = findViewById(R.id.textEditName);
        lastNameEditText = findViewById(R.id.textEditSurname);
        groupEditText = findViewById(R.id.textEditGroup);
        genderSpinner = findViewById(R.id.spinnerGender);

        spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender,android.R.layout.simple_spinner_item);
        genderSpinner.setAdapter(spinnerAdapter);
    }
}