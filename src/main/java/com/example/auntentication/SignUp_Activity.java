package com.example.auntentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUp_Activity extends AppCompatActivity {

    private Button Create_account_Button;
    private EditText Input_email_address, Input_password, Input_name;
    private ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        Create_account_Button = (Button) findViewById(R.id.signup_button_Signup_page);
        Input_email_address = (EditText) findViewById(R.id.CreateEmail);
        Input_password = (EditText) findViewById(R.id.CreateTextPassword);
        LoadingBar = new ProgressDialog(this);


        Create_account_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });

    }

    private void CreateAccount(){

        String Email = Input_email_address.getText().toString();
        String Password = Input_password.getText().toString();

        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show();
        }


        else if(TextUtils.isEmpty(Password)){
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        }
        else{
            LoadingBar.setTitle("Create Account");
            LoadingBar.setMessage("Please wait, we are checking the credentials.");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();


            Validate_PH_number(Email,Password);

        }

    }

    private void Validate_PH_number(String Email, String password) {

         DatabaseReference Rootref = FirebaseDatabase.getInstance("https://autenticacion-936ca-default-rtdb.firebaseio.com/").getReference();



        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (!(snapshot.child("Users").child(Email).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                   // userdataMap.put("Username", Email);
                    userdataMap.put("Masterpassword", password);


                    Rootref.child("Users").child(Email).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUp_Activity.this, "Congratulations, your account is created successfully" , Toast.LENGTH_SHORT).show();
                                        LoadingBar.dismiss();

                                        Intent intent = new Intent(SignUp_Activity.this,MainActivity.class );
                                        startActivity(intent);
                                    }
                                    else{
                                        LoadingBar.dismiss();
                                        Toast.makeText(SignUp_Activity.this, "Network Error, Please try again!" , Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });


                }

                else
                {
                    Toast.makeText(SignUp_Activity.this, "This "+ Email + " is already exist!" , Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();
                    Toast.makeText(SignUp_Activity.this, "Please try again using different Username." , Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignUp_Activity.this,MainActivity.class );
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
