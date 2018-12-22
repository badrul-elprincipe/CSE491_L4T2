package com.example.user.lab4t2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String>a;
    ArrayAdapter<String>adapter;
    ListView listView;


    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        //databaseReference.child(databaseReference.push().getKey()).setValue(new food("ber","100"));


        a=new ArrayList<String>();
        listView=findViewById(R.id.list);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,a);
        listView.setAdapter(adapter);






        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                a.clear();

                for (DataSnapshot d:dataSnapshot.getChildren()){


                  a.add(d.child("name").getValue(String.class));
                  adapter.notifyDataSetChanged();


                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
