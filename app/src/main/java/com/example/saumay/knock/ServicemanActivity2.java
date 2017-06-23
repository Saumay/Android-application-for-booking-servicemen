package com.example.saumay.knock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServicemanActivity2 extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;
    DatabaseReference childChildName, childChildBio;
    FirebaseDatabase database;

    private TextView editTextMobile;
    private Button buttonLogout;
    private Button buttonSubmit, button4, button5, button6, button7, button8, button9;
    private RadioGroup rgServiceman;
    private TextView t;
    //RadioGroup rg;
    private RadioButton rb;
    String url;
    private EditText editText;

    DatabaseReference Ref, childChildService, childRef;
    //private Firebase Ref, childChildService, childRef;
    String defineUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceman2);
        editText = (EditText) findViewById(R.id.editText4);
        t= (TextView) findViewById(R.id.textView6);

        //t = (TextView) findViewById(R.id.textView6);
        firebaseAuth = FirebaseAuth.getInstance();
        String service = getIntent().getStringExtra("service").trim();
        String name = getIntent().getStringExtra("name").trim();

        String url = "https://knock-a99b7.firebaseio.com/Servicemen/"+service+"/"+name;
       // t.setText(url);
        //Ref = new Firebase("https://knock-a99b7.firebaseio.com/");
        database = FirebaseDatabase.getInstance();
        childChildName = database.getReferenceFromUrl(url);;

        childChildBio = childChildName.child("Bio");

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if (view == buttonLogout) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
        //int rbtn = rgServiceman.getCheckedRadioButtonId();
        //rb = (RadioButton)findViewById(rbtn);
        if(view == buttonSubmit)
        {
            childChildBio.setValue(editText.getText().toString());
        }
    }
}
