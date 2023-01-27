package com.example.auntentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.auntentication.prevelant.prevelant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import io.paperdb.Paper;

public class Save_password extends AppCompatActivity {

    private EditText Input_id, Input_password, Input_platform;
    private ProgressDialog LoadingBar;
    Task<Void> reff;
    private Button Save_Button,paste_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_password);

        Input_id = (EditText) findViewById(R.id.Create_id);
                Input_password = (EditText) findViewById(R.id.Create_id_password);
        Input_platform = (EditText) findViewById(R.id.Create_platform);
        LoadingBar = new ProgressDialog(this);
        Save_Button = (Button) findViewById(R.id.Save);
        paste_button = (Button) findViewById(R.id.Paste);


        paste_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData a = clipboardManager.getPrimaryClip();
                ClipData.Item item = a.getItemAt(0);
                String tex1 = item.getText().toString();
                Input_password.setText(tex1);
            }
        });

        Save_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {

        String id =  Input_id.getText().toString();
        String password = Input_password.getText().toString();
        String platform = Input_platform.getText().toString();

        if(TextUtils.isEmpty(id)){
            Toast.makeText(this, "Please write your account's ID", Toast.LENGTH_SHORT).show();
        }


        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please write your account's password", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(platform)){
            Toast.makeText(this, "Please specify which platform", Toast.LENGTH_SHORT).show();
        }
        else{
            LoadingBar.setTitle("Create Account");
            LoadingBar.setMessage("Please wait, we are checking the credentials.");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();


            Validate_Credentials(id,password,platform);

        }
    }

    private void Validate_Credentials(String id, String password,String platform) {

        DatabaseReference Rootref = FirebaseDatabase.getInstance("https://autenticacion-936ca-default-rtdb.firebaseio.com/").getReference().child("Users").child(Paper.book().read(prevelant.UserEmail)).child("Platform");
        String platformId = Rootref.push().getKey();


        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                        HashMap<String, Object> userdataMap = new HashMap<>();
                        // userdataMap.put("Username", Email);
                        userdataMap.put("id", id);
                        userdataMap.put("password", password);
                        userdataMap.put("platform", platform);


                        Rootref.child(platformId).updateChildren(userdataMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Save_password.this, "Congratulations, your credentials saved successfully!", Toast.LENGTH_SHORT).show();
                                            LoadingBar.dismiss();

                                            Intent intent = new Intent(Save_password.this, Password_Manager.class);
                                            startActivity(intent);
                                        } else {
                                            LoadingBar.dismiss();
                                            Toast.makeText(Save_password.this, "Network Error, Please try again!", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}