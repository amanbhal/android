package org.ex.analog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlertDialogActivity extends Activity {
    /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) 
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        AlertDialog.Builder obj = new AlertDialog.Builder(this);
        obj.setMessage("Do you want to close the running Activity?")
        

        
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
        finish();
        }
        })
        
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int anything) {
        //  Action for ‘NO’ Button
        dialog.cancel();
}
        });
        
        
        AlertDialog alert = obj.create();
        // Title for AlertDialog
        alert.setTitle("Alert !!!");
        // Icon for AlertDialog
        alert.setIcon(R.drawable.ic_launcher);
        alert.show();
        }
        }