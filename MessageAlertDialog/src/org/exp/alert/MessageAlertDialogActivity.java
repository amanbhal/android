package org.exp.alert;

import android.app.Activity;

import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MessageAlertDialogActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final CharSequence[] items = {"INBOX", "DRAFT", "SENT ITEMS", "TEMPLATE"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog.Builder obj1 = new AlertDialog.Builder(this);
        
        
        
        builder.setTitle("..MESSAGE..");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                AlertDialog alert1 = obj1.create();
                // Title for AlertDialog
                alert1.setTitle("u r in "+items[item]);
                               
                alert1.show();
                
            }
        });
        AlertDialog alert = builder.create();
        alert.setIcon(R.drawable.ic_launcher);
        alert.show();

    }
}
