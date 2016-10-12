package com.example.infs3634project2.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.infs3634project2.R;
import com.example.infs3634project2.model.Student;
import com.example.infs3634project2.model.Tutorial;
import com.example.infs3634project2.recyclerviews.StudentsAdapter;
import com.example.infs3634project2.recyclerviews.TutorialsAdapter;
import com.example.infs3634project2.storage.DBOpenHelper;
import com.example.infs3634project2.storage.StudentsContract;
import com.example.infs3634project2.storage.TutorialsContract;

import java.util.ArrayList;

public class StudentsActivity extends AppCompatActivity {

    private ArrayList<Student> studentsList;
    private RecyclerView mRecyclerView;
    private StudentsAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private Button addStudentButton;
    private int tutorialID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        addStudentButton = (Button) findViewById(R.id.addStudentButton);
        tutorialID = (int) getIntent().getSerializableExtra("TutorialID");

        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBOpenHelper helper = new DBOpenHelper(StudentsActivity.this);
                TutorialsContract tutorialsContract = new TutorialsContract(helper);

                //This should redirect to a blank list of the students but working out Tutorials right now
                Intent showClasses = new Intent(StudentsActivity.this, NewStudent.class);
                showClasses.putExtra("TutorialID", tutorialID);
                startActivity(showClasses);

            }
        });

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        StudentsContract studentsContract = new StudentsContract(dbOpenHelper);



        if (studentsContract.getStudentsList(tutorialID) != null) {

            studentsList = studentsContract.getStudentsList(tutorialID);

            mRecyclerView = (RecyclerView) findViewById(R.id.studentsRecyclerView);
            mAdapter =  new StudentsAdapter(studentsList);
            mRecyclerView.setAdapter(mAdapter);

            mLinearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            Log.d("Student List Return", studentsList.toString());
        }

    }
}
