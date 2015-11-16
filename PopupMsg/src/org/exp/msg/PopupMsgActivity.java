package org.exp.msg;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class PopupMsgActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final AlertDialog.Builder obj = new AlertDialog.Builder(this);  
        final AlertDialog.Builder obj1 = new AlertDialog.Builder(this);   
        final AlertDialog.Builder obj2 = new AlertDialog.Builder(this);   
        
        
        obj.setMessage("hi.. i will call u latter... \n" +
        		"i am in meeting!!!")
        .setCancelable(false)

        
        .setPositiveButton("send sms", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
        	     	
        	  
                	AlertDialog alert1 = obj1.create();
            // Title for AlertDialog
            alert1.setTitle("Message has been sent!!!");
           
            alert1.show();

        }
        })
        
        .setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int anything) {
        //  Action for ‘NO’ Button
        	AlertDialog alert2 = obj2.create();
            // Title for AlertDialog
            alert2.setTitle("Message has been saved ...");
            alert2.setIcon(R.drawable.index);
            alert2.show();

}
        });
        
        
        AlertDialog alert = obj.create();
        // Title for AlertDialog
        alert.setTitle("Hey.. i made a call to u \n" +
        		"But u dint respond...\n");
        // Icon for AlertDialog
        alert.setIcon(R.drawable.images);
        alert.show();
        }
        }