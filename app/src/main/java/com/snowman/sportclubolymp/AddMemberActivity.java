package com.snowman.sportclubolymp;

import com.snowman.sportclubolymp.data.ClubOlympusContract.MemberEntry;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddMemberActivity extends AppCompatActivity {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText groupEditText;
    private Spinner genderSpinner;
    private int gender = 0;
    private ArrayAdapter spinnerAdapter;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addMember){
            Toast.makeText(this, "Member was Added", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.deleteMember){
            Toast.makeText(this, "Member was deleted", Toast.LENGTH_SHORT).show();
        } else if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return true;
    }

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
                    gender = MemberEntry.GENDER_MALE;
                }else if(selectedGender.equals("Female")){
                    gender = MemberEntry.GENDER_FEMALE;
                }else if(selectedGender.equals("Unknown")){
                     gender = MemberEntry.GENDER_UNKNOWN;
                 }
            }
                Toast.makeText(AddMemberActivity.this, "Пол номер = " + gender, Toast.LENGTH_SHORT).show();
        }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender = 0;
            }
        });

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}