package org.ex.activity;

import android.app.Activity;


import android.os.Bundle;

import android.widget.*;


public class ActivityLifecycleActivity extends Activity {

 LinearLayout myScreen;
 
 


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     
        myScreen = (LinearLayout) findViewById(R.id.myScreen);
        TextView txtToDo = (TextView) findViewById(R.id.txtToDo);

        String msg = "Instructions: \n"
        + "0. New instance (onCreate, onStart, onResume) \n"
        + "1. Back Arrow (onPause, onStop, onDestroy) \n"
        + "2. Home (onPause, onStop) \n";
       

    
    
    txtToDo.setText(msg);
    
}
    
   
  
          
    @Override
    protected void onPause() {
    super.onPause();
     //saveDataFromCurrentState();
     Toast.makeText(this, "onPause", 1).show();
    }

    @Override
    protected void onRestart() {
    super.onRestart();
    Toast.makeText(this, "onRestart", 1).show();
    }

    @Override
    protected void onResume() {
    super.onResume();
    Toast.makeText(this, "onResume", 1).show();
    }
     
    @Override
    protected void onStart() {
    // TODO Auto-generated method stub
    super.onStart();
    //updateFromSavedState();
    Toast.makeText(this, "onStart",1).show();
    }

    @Override
    protected void onDestroy() {
    // TODO Auto-generated method stub
    super.onDestroy();
    Toast.makeText(this, "onDestroy", 1).show();
    }

    @Override
    protected void onStop() {
    // TODO Auto-generated method stub
    super.onStop();
    Toast.makeText(this, "onStop", 1).show();
    }




}