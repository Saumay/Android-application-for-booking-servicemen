package com.example.saumay.knock;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Map;

public class ProfileActivity2 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public ArrayList<String> arr;
    public ArrayAdapter adapter;
    private TextView tvv;
    DatabaseReference mDatabase;
    String url, name;
    ListView listView;

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_profile2);
        tvv = (TextView) findViewById(R.id.textView5);
        name = getIntent().getStringExtra("request");
        //tvv.setText("Following " + name + "s are found");
        listView = (ListView) findViewById(R.id.ListView);
        listView.setOnItemClickListener(this);

        url = "https://knock-a99b7.firebaseio.com/Servicemen" + "/" + name;
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl(url);
        arr = new ArrayList<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                map2list((Map) dataSnapshot.getValue());
                //formats the datasnapshot entries to strings
                adapter.notifyDataSetChanged();
                //makes the ListView realtime
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                System.out.println(databaseError.toException());
                // ...
            }
        };
        mDatabase.addValueEventListener(listener);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, arr);

        listView.setAdapter(adapter);
    }

    public void map2list(Map<String, Long> map){

        arr.clear();
        for (Map.Entry<String, Long> entry : map.entrySet()) {

            String key = entry.getKey();
            //DatabaseReference nameDatabase = mDatabase.child(key);
            arr.add(key);
        }

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        String UserInfo = listView.getItemAtPosition(position).toString();
        String info = url + "/" + UserInfo;
        //tvv.setText(info);
        Intent i =new Intent(getApplicationContext(), ProfileActivity3.class);
        i.putExtra("info", info);
        i.putExtra("name", UserInfo);
        startActivity(i);
        /***just to check if it has value***/
        // Toast.makeText(getBaseContext(),"position is : "+position+" and value is +UserInfo,Toast.LENGTH_SHORT).show();
        //Toast.makeText(getBaseContext(), id + "", Toast.LENGTH_LONG).show();
    }


    /*Intent in = new Intent(getApplicationContext(), ProfileActivity3.class);
    String info = url + "/" + key;
    in.putExtra("details", info);
    startActivity(in);*/


}
