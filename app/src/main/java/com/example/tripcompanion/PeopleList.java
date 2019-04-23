package com.example.tripcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PeopleList extends AppCompatActivity {
    String test;
    String city;
    String state;
    ArrayList<String> friends1=new ArrayList<>();
    String newOb;
    String[] abc={"Sorry you don't have any friends!!"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {


//        String[] friends2={"Gopi","Krihsna","Dr.Hoot"};

         ListView lv ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peoplelist);
        lv = (ListView) findViewById(R.id.listView);

        Intent ini=getIntent();
        test=ini.getStringExtra("user_id");
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        abc();

//       if(name.toUpperCase().equals("MISSOURI")){
//           ListAdapter ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,friends1);
//           ListView lv=(ListView)findViewById(R.id.listView);
//           lv.setAdapter(ad);
//       }
//        else if(name.toUpperCase().equals("KANSAS")){
//           ListAdapter ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,friends2);
//           ListView lv=(ListView)findViewById(R.id.listView);
//           lv.setAdapter(ad);
//        }
//           else{
//           TextView tv=findViewById(R.id.textView);
//           tv.setText("Sorry! you do not have any friends!!");
//       }
//
    }
    public void abc(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Trip");
        Log.d("kk","err"+test);

        query.whereEqualTo("objectId",test);
        query.getInBackground(test, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject ob, com.parse.ParseException e) {
                if (e == null) {

                    city=ob.getString("city");
                    state=ob.getString("state");
                    Log.d("test","err"+city);
                    Log.d("test","err"+state);





                }
            }
        });
        tryy();
    }

    public void tryy(){
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Trip");
// Fetches all the Movie objects
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> tripList, ParseException e) {

                String ct;
                String st;
                if (e == null) {
                    Log.d("Parse", "Trips retrieved:" + tripList.size());
// Do something with the found objects
                    for (int i = 0; i < tripList.size(); i++) {
                        ct= (String)tripList.get(i).get("city");
                        st=(String)tripList.get(i).get("state");
                        newOb=tripList.get(i).getObjectId();
                        Log.d("Parse", "obj retrieved:" +newOb);

                        if(city.equals(ct) && state.equals(st) ) {
                            String name= (String)tripList.get(i).get("name");
                            if(!test.equals(newOb)) {
                                friends1.add(name);
                            }


                        }

                    }
                    Log.d ("Df","Dfd"+friends1.size());
                    if(friends1.size()!=0){
                        showMem();
                    }
                    else{
                        noMem();
                    }

                } else {
                    Log.d("Parse", "Error: " + e.getMessage());
                }
            }
        });

    }
    public void showMem(){
//
//    ListAdapter ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,abc);
//    ListView lv=(ListView)findViewById(R.id.listView);
//    lv.setAdapter(ad);
//
//
////        else{
////
        ArrayAdapter<String> ad =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends1);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(ad);
//        }
    }
    public void noMem(){
        ListAdapter ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,abc);
        ListView lv=(ListView)findViewById(R.id.listView);
        lv.setAdapter(ad);
    }
}
