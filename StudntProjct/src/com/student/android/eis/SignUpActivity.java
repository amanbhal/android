package com.student.android.eis;

import com.student.android.eis.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends BaseLoginActivity{
	private LinearLayout mNavigationbBar;
	private TextView txt_header;
	private Button btn_add;
	private EditText et_username;
	private EditText et_password;
	private EditText et_confirmpassword;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        initUI();
        setListeners();
        
   }
    
    private void setListeners() {
		// TODO Auto-generated method stub
    	btn_add = (Button) findViewById(R.id.btn_add);
    	btn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSignUpButtonClicked();
 }
  });
        
        
	}
    
	private void onSignUpButtonClicked()
    {
		String username = et_username.getText().toString();
		String password = et_password.getText().toString();
		String confirmpassword = et_confirmpassword.getText().toString();
		if (username.trim().equalsIgnoreCase("") || password.trim().equalsIgnoreCase("")
				|| confirmpassword.trim().equalsIgnoreCase("")) {
			String text = "Fill All details";
			Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
			toast.show();
			
		} else {
			if (password.equals(confirmpassword)) {
				boolean success = dbManager.insertRowLoginTable(username, password);
				
				if (success) {
					String text = "Signup Successful";
					Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
					toast.show();
					finish();
				}
			} else {
				String text = "Passwords Do not match";
				Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
				toast.show();
			}
		}

    }
        

	private void initUI() {
		// TODO Auto-generated method stub
		mNavigationbBar = (LinearLayout)findViewById(R.id.layout_header);
		txt_header = (TextView)mNavigationbBar.findViewById(R.id.tv_header);
		txt_header.setText("Sign Up");
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_confirmpassword = (EditText) findViewById(R.id.et_confirmpassword);

	}
}

