package com.example.lollo.read_led_from_firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writetoDataBase();
        readFromDataBase();
    }
    public void writetoDataBase()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("LED_STATUS");
    }
    public void readFromDataBase()
    {
       myRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot)
           {
               status = findViewById(R.id.led_status);
               String value = dataSnapshot.getValue(String.class);
               Log.v("lorenzo","Value is "+value);
               status.setText(value);

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError)
           {
               Log.v("lorenzo","Failed to read value", databaseError.toException());
           }
       });
    }
}
