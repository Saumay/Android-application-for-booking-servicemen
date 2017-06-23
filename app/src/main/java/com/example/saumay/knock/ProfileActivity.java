package com.example.saumay.knock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private Button buttonLogout;
    private TextView welcome;
    private ImageButton ib1;
    private ImageButton ib2;
    private ImageButton ib3;
    private ImageButton ib4;
    private ImageButton ib5;
    private ImageButton ib6;
    private TextView tt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        //FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        ib1 = (ImageButton)findViewById(R.id.ib1);
        ib2 = (ImageButton)findViewById(R.id.ib2);
        ib3 = (ImageButton)findViewById(R.id.ib3);
        ib4 = (ImageButton)findViewById(R.id.ib4);
        ib5 = (ImageButton)findViewById(R.id.ib5);
        ib6 = (ImageButton)findViewById(R.id.ib6);
        tt = (TextView) findViewById(R.id.textView3);
        //displaying logged in user name
        //welcome.setText("Welcome "+user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(this);
        ib1.setOnClickListener(this);
        ib2.setOnClickListener(this);
        ib3.setOnClickListener(this);
        ib4.setOnClickListener(this);
        ib5.setOnClickListener(this);
        ib6.setOnClickListener(this);
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

        else if(view == ib1) {
            //tt.setText("Following Carpenters are found");
            Intent in = new Intent(getApplicationContext(), ProfileActivity2.class);
            in.putExtra("request", "Carpenter");
            startActivity(in);
        }

        else if(view == ib2)
        {
            //tt.setText("Following Maids are found");
            Intent in = new Intent(getApplicationContext(), ProfileActivity2.class);
            in.putExtra("request", "Maid");
            startActivity(in);
        }
        else if(view == ib3)
        {
            //tt.setText("Following Electricians are found");
            Intent in = new Intent(getApplicationContext(), ProfileActivity2.class);
            in.putExtra("request", "Electrician");
            startActivity(in);
        }

        else if(view == ib4)
        {
            //tt.setText("Following Mechanics are found");
            Intent in = new Intent(getApplicationContext(), ProfileActivity2.class);
            in.putExtra("request", "Mechanic");
            startActivity(in);
        }

        else if(view == ib5)
        {
            //tt.setText("Following Painters are found");
            Intent in = new Intent(getApplicationContext(), ProfileActivity2.class);
            in.putExtra("request", "Painter");
            startActivity(in);
        }

        else if(view == ib6)
        {
            //tt.setText("Following Pest Controllers are found");
            Intent in = new Intent(getApplicationContext(), ProfileActivity2.class);
            in.putExtra("request", "Pest Control");
            startActivity(in);
        }
    }
}