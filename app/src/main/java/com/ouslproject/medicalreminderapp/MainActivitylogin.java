package com.ouslproject.medicalreminderapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
// login functionality of the app
public class MainActivitylogin extends AppCompatActivity {
    private EditText loginuser;
    private EditText loginpass;
    private Button loginbtn;
    private TextView logintext;
    DatabseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogin);
        db=new DatabseHelper(this);
        loginuser=findViewById(R.id.loginmail);
        loginpass=findViewById(R.id.loginpassword);
        loginbtn=findViewById(R.id.loginbutton);
        logintext=findViewById(R.id.logintext);
        String text="Not Registered Yet?Register Here";//provides a way for users to register if they haven't already
        SpannableString ss=new SpannableString(text);
        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent=new Intent(MainActivitylogin.this,register.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState( TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan,19 ,32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        logintext.setText(ss);
        logintext.setMovementMethod(LinkMovementMethod.getInstance());
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=loginuser.getText().toString().trim();
                String pass=loginpass.getText().toString().trim();
                if(user.isEmpty())
                {
                    loginuser.setError("Field can't be empty");
                    return;
                }
                if(pass.isEmpty())
                {
                    loginpass.setError("Field can't be empty");
                    return;
                }
                Boolean res=db.checkuser(user,pass);
                if(res==true)
                {
                    Toast.makeText(MainActivitylogin.this,"Successsful",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivitylogin.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivitylogin.this,"Error",Toast.LENGTH_SHORT).show();;
                }
            }
        });
    }
}
