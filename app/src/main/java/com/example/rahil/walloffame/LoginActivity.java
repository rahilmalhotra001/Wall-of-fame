package com.example.rahil.walloffame;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.rahil.walloffame.MainActivity;
import com.example.rahil.walloffame.R;


public class LoginActivity extends AppCompatActivity {
    private EditText email_et;
    private EditText password_et;
    private Button login_bt;
    String TAG ="LoginActivity";
    TextInputLayout email_til;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_login);

        email_et = (EditText) findViewById(R.id.email_et);
        password_et = (EditText) findViewById(R.id.password_et);
        login_bt = (Button) findViewById(R.id.login_bt);
        login_bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(email_et.getText().toString(), password_et.getText().toString());
            }
        });
    }


    private void validate(String userName, String userPassword)
    {
        if(null == userName || userName.length() == 0)
            email_et.setError("You can't leave email blank");
        else if(null == userPassword || userPassword.length() == 0)
            password_et.setError("You can't leave password blank");
        else
        {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra("email", userName);
            Log.d(TAG, "email is - " + userName);
            //to get a small popup in black before moving to second activity
            Toast.makeText(getApplicationContext(), "Welcome, " + userName, Toast.LENGTH_SHORT).show();
            startActivity(i);
        }

    }
}