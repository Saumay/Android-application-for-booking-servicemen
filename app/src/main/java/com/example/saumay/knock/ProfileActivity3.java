package com.example.saumay.knock;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Map;

import static com.example.saumay.knock.R.id.buttonSMS;

public class ProfileActivity3 extends AppCompatActivity implements View.OnClickListener
{
    TextView textViewName, textViewPhone, textViewBio;
    String url, name, phone, customerName, customerPhone, message, bio;
    DatabaseReference Database;
    Button b;
    String[] arr;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile3);

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewPhone = (TextView) findViewById(R.id.textViewPhone);
        textViewBio = (TextView) findViewById(R.id.textViewBio);
        b = (Button) findViewById(R.id.buttonSMS);

        customerPhone = getIntent().getStringExtra("phone");
        //customerPhone = getIntent().getStringExtra("phone");
        //arr = customerName.split(",");

        url = getIntent().getStringExtra("info");
        url = url.trim();

        name = getIntent().getStringExtra("name");
        textViewName.setText(name);
        Database = FirebaseDatabase.getInstance().getReferenceFromUrl(url);

        b.setOnClickListener(this);

        Database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                phone = map.get("Mobile number").toString();
                bio = map.get("Bio").toString();
                textViewPhone.setText(phone);
                textViewBio.setText(bio);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onClick(View view) {

        if (view == b) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.SEND_SMS)) {
                    Toast.makeText(getApplicationContext(), "SMS sent2.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "SMS sent3.",
                            Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.SEND_SMS},
                            MY_PERMISSIONS_REQUEST_SEND_SMS);
                }
            } else
            {
                message = "I would like to take your services.\nContact Number:" + customerPhone + "\nCall on the above number for further details.";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone, null, message, null, null);

                Toast.makeText(getApplicationContext(), "SMS sent.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}
