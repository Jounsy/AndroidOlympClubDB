package com.snowman.sportclubolymp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_member_menu,menu);
        return true;

    }

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
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = (String)parent.getItemAtPosition(position);

                if(selectedGender != null){

                 if(selectedGender.equals("Male")){
                    gender = 1;
                }else if(selectedGender.equals("Female")){
                    gender = 2;
                }else {
                     gender = 0;
                 }
            }
                Toast.makeText(AddMemberActivity.this, "Пол номер = " + gender, Toast.LENGTH_SHORT).show();
        }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender = 0;
            }
        });
    }
}