package com.example.auntentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.auntentication.prevelant.prevelant;
import com.example.auntentication.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import io.paperdb.Paper;


public class MainActivity extends AppCompatActivity {

    private Button Login_Button,SignUp_Button;
    private ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadingBar = new ProgressDialog(this);

        Paper.init(this);

        Login_Button=(Button) findViewById(R.id.login_button);
        Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login_Activity.class);
                startActivity((intent));
            }
        });

        SignUp_Button=(Button) findViewById(R.id.signup_button);
        SignUp_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUp_Activity.class);
                startActivity((intent));
            }
        });


        String UserPhoneKey = Paper.book().read(prevelant.UserEmail);
        String UserPasswordKey = Paper.book().read(prevelant.UserPasswordKey);
        if(UserPhoneKey != "" && UserPasswordKey != "")
        {
            if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey))
            {
                AllowAcces(UserPhoneKey,UserPasswordKey);
                LoadingBar.setTitle("Already Logged in Account");
                LoadingBar.setMessage("Please wait, we are checking the credentials.");
                LoadingBar.setCanceledOnTouchOutside(false);
                LoadingBar.show();
            }
        }

    }

    private void AllowAcces(final String email, final String password) {
        final DatabaseReference Rootref = FirebaseDatabase.getInstance("https://autenticacion-936ca-default-rtdb.firebaseio.com/").getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child("Users").child(email).exists())
                {
                    Users usersData = snapshot.child("Users").child(email).getValue(Users.class);




                    if(usersData.getMasterpassword().equals(password))
                    {
                        Toast.makeText(MainActivity.this, "Logged in successfully!!!", Toast.LENGTH_SHORT).show();
                        LoadingBar.dismiss();
                        Toast.makeText(MainActivity.this, "Hello "+ usersData.getUsername()+" !!!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this,Home_page.class );
                        startActivity(intent);


                    }
                    else{
                        LoadingBar.dismiss();
                        Toast.makeText(MainActivity.this, "Password is incorrect!!!", Toast.LENGTH_SHORT).show();

                    }


                }
                else{
                    Toast.makeText(MainActivity.this, "Account with this "+ email
                            + " does not exist!!", Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();
                    Toast.makeText(MainActivity.this, "Please Sign Up to create an account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}