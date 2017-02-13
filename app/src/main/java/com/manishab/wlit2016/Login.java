package com.manishab.wlit2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button login_button;
    EditText e_password, e_emailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_button=(Button)findViewById(R.id.login_button);
        e_password=(EditText) findViewById(R.id.e_password);
        e_emailId=(EditText) findViewById(R.id.e_emailId);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e_emailId.getText().toString().equals("mandira")&&e_password.getText().toString().equals("password")){
                  Intent intent = new Intent(Login.this,Home.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this,"Username and password is incorrect",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
