package com.example.auntentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import io.paperdb.Paper;


public class Home_page extends AppCompatActivity {


    private Button generate_otp, password_manager, password_generator, logout_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        generate_otp = (Button) findViewById(R.id.Generate_OTP);
        password_manager = (Button) findViewById(R.id.Password_Manager);
        password_generator = (Button) findViewById(R.id.Password_Generator);
        logout_btn = (Button) findViewById(R.id.Logout_Btn);

        password_generator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Home_page.this,Passwordgenarator.class );
                startActivity(intent);
            }
        });

        generate_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Home_page.this,OTP_Generating_activity.class );
                startActivity(intent);
            }
        });

        password_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Home_page.this,Password_Manager.class );
                startActivity(intent);
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Paper.book().destroy();
                Intent intent = new Intent(Home_page.this,MainActivity.class );
                startActivity(intent);
            }
        });



    }
}