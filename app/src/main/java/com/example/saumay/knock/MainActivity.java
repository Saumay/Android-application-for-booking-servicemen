package com.example.saumay.knock;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;

import static com.example.saumay.knock.R.id.radioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextNumber;
    private TextView tt;

    private Button buttonSignup;
    //private Button buttonSignup2;
    private Spinner s;

    private TextView textViewSignin;

    private RadioGroup rg;
    private RadioButton rb;

    private ProgressDialog progressDialog;

    FirebaseDatabase database;
    DatabaseReference Ref, NoOfUsers, ServiceChild, childService, CustomerChild, childName, childChildType, childChildNumber;
    int usersCount;
    String defineUser;
    String name, ss, number, service;

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    //private Firebase Ref, childNoOfUsers, childRef, childChildName, childChildType, childChildNumber;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();
        //rg = (RadioGroup) findViewById(R.id.rg);

        database= FirebaseDatabase.getInstance();;
        //Ref = new Firebase("https://knock-a99b7.firebaseio.com/");
        Ref = database.getReference();
        NoOfUsers = Ref.child("Number of users");
        usersCount = 0;
        defineUser = "User "+usersCount;


        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));


        }

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        //buttonSignup2 = (Button) findViewById(R.id.buttonSignup2);
        tt = (TextView) findViewById(R.id.textViewBlink);

        s = (Spinner) findViewById(R.id.spinner);
        s.setVisibility(View.INVISIBLE);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);
        //buttonSignup2.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

        //for updating data
        NoOfUsers.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                //childNoOfUsers.setValue(usersCount+"");
                usersCount = Integer.parseInt(data);
            }

            public void onCancelled(DatabaseError databaseError) {}
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int rbtn = rg.getCheckedRadioButtonId();
                rb = (RadioButton)findViewById(rbtn);
                ss = rb.getText().toString().trim();
                //textViewSignin.setText(ss);
                if(ss.equals("Customer")) {
                    s.setVisibility(View.INVISIBLE);
                    tt.setVisibility(View.INVISIBLE);
                }
                else if(ss.equals("Serviceman")) {
                    s.setVisibility(View.VISIBLE);
                    tt.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void registerUser()
    {
        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful())
                        {
                            finish();
                            Intent in = new Intent(getApplicationContext(),ProfileActivity.class);
                            in.putExtra("name", name);
                            startActivity(in);
                        }else{
                            //display some message here
                            Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    private void registerUser2()
    {
        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful())
                        {
                            finish();
                            Intent in = new Intent(getApplicationContext(),ServicemanActivity2.class);
                            in.putExtra("name", name);
                            in.putExtra("service", service);
                            startActivity(in);
                            //startActivity(new Intent(getApplicationContext(), ServicemanActivity2.class));
                        }else{
                            //display some message here
                            Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
        -            }
                });
    }


    @Override
    public void onClick(View view) {

        if(view == buttonSignup)
        {
            name = editTextName.getText().toString().trim();
            number = editTextNumber.getText().toString().trim();

            //childRef = Ref.child(name);
            //childChildName = childRef.child("Name");
            //childChildService = childRef.child("Service");
            usersCount++;
            NoOfUsers.setValue(usersCount+"");

            if(ss.equals("Serviceman"))
            {
                service = String.valueOf(s.getSelectedItem());
                ServiceChild = Ref.child("Servicemen");
                childService = ServiceChild.child(service);
                childName = childService.child(name);
                //childChildType = childName.child("Type");
                childChildNumber = childName.child("Mobile number");
                //childChildType.setValue("Customer");
                childChildNumber.setValue(number);
                //Intent inn = new Intent(getApplicationContext(),ServicemanActivity2.class);

                //inn.putExtra("name", name);

                registerUser2();
            }
            else if(ss.equals("Customer"))
            {
                //service = String.valueOf(s.getSelectedItem());
                CustomerChild = Ref.child("Customers");
                childName = CustomerChild.child(name);
                //childChildType = childName.child("Type");
                childChildNumber = childName.child("Mobile number");
                //childChildType.setValue("Serviceman");
                childChildNumber.setValue(number);

                Intent inn = new Intent(getApplicationContext(),ProfileActivity3.class);
                inn.putExtra("phone", number);

                registerUser();
            }

        }

        if(view == textViewSignin){
            //open login activity when user taps on the already registered textview
            startActivity(new Intent(this, LoginActivity.class));
        }

    }

    protected void onStart(){
        super.onStart();
        }
}