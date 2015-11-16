package com.student.android.eis;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.student.android.eismodel.Constant;
import com.student.android.eismodel.Student;
import com.student.android.eisparser.MyParser;
import com.student.android.eis.R;

public class LoginScreenActivity extends BaseLoginActivity{
	   /** Called when the activity is first created. */
	private LinearLayout mNavigationbBar;
	private TextView txt_header;
	private Button btn_signup;
	private Button btn_login;
	private EditText et_name;
	private EditText et_password;
	ArrayList<Student> studentArray;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        initUI();
        setListeners();
        createDatabase();
    }
    private void createDatabase() {
		// TODO Auto-generated method stub
		boolean isDbPresent = dbManager.checkIfDataBasePresent(Constant.TABLE_STUDENT);
		if(!isDbPresent)
		{
		MyParser parser = new MyParser();
		try {
			studentArray = parser.parseData(this);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			if (studentArray.size()>0) {
				boolean success = dbManager.insertstudentData(studentArray);
				if (success) {
					
				} else {

				}
			}
		}
		
	}
	private void setListeners() {
		// TODO Auto-generated method stub
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSignUpButtonClicked();
 }
  });
        
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginButtonClicked();
 }

  });
        
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        
        
	}

	public void loginButtonClicked() {
		// TODO Auto-generated method stub
		String name = et_name.getText().toString();
		String password = et_password.getText().toString();
		
		boolean success = dbManager.verifyUser(name, password);
		if (success) {
			pushActivity(this, EveryoneActivity.class.getName(),true);
		}		
	}

	public void onSignUpButtonClicked()
    {
		pushActivity(this, SignUpActivity.class.getName());
    }
	
	private void initUI() {
		// TODO Auto-generated method stub
		mNavigationbBar = (LinearLayout)findViewById(R.id.layout_header);
		txt_header = (TextView)mNavigationbBar.findViewById(R.id.tv_header);
		txt_header.setText("Login");
	
	}
}
