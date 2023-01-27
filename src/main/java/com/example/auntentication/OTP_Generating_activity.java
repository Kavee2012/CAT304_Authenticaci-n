package com.example.auntentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.auntentication.model.Users;
import com.example.auntentication.prevelant.prevelant;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.paperdb.Paper;

public class OTP_Generating_activity<data> extends AppCompatActivity {

    //Initialize variable
    String OTPGenerated, NewOTPGenerated;
    android.widget.TextView textView, TextView;
    Task<Void> reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_generating);

        //Assign variable
        textView = findViewById(R.id.timer);
        TextView = findViewById(R.id.OTPNumber);

        //Calling function to generate OTP when timer is started
        OTPGenerated = getRandomNumberString();

        //Display first OTP
        TextView.setText(OTPGenerated);



        //Store the OTP into firebase
        reff= FirebaseDatabase.getInstance("https://autenticacion-936ca-default-rtdb.firebaseio.com/")
                .getReference().child("Users").child(Paper.book().read(prevelant.UserEmail))
                .child("OTP Numbers").child("Current OTP").setValue(OTPGenerated);

        //Initialize countdown timer
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                // When tick
                // Convert millisecond to minute and second
                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d"
                        , TimeUnit.MILLISECONDS.toMinutes(l)
                        , TimeUnit.MILLISECONDS.toSeconds(l) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                //Set converted string on text view and display it
                textView.setText(sDuration);
            }

            @Override
            public void onFinish() {
                //When finish
                //Restart Activity
                start();

                //Display toast
                Toast.makeText(getApplicationContext()
                        , "Time Up! New OTP is generated :)", Toast.LENGTH_LONG).show();

                //Calling OTP generator function
                NewOTPGenerated = getRandomNumberString();

                //Display new OTP
                TextView.setText(NewOTPGenerated);

                //Update new OTP into firebase
                reff=FirebaseDatabase.getInstance("https://autenticacion-936ca-default-rtdb.firebaseio.com/")
                        .getReference().child("Users").child(Paper.book().read(prevelant.UserEmail))
                        .child("OTP Numbers").child("Current OTP").setValue(NewOTPGenerated);
            }
        }.start(); //Timer is started

        //Set Current OTP to 0 when application is disconnected
        DatabaseReference ref = FirebaseDatabase.getInstance("https://autenticacion-936ca-default-rtdb.firebaseio.com/")
                .getReference().child("Users").child(Paper.book().read(prevelant.UserEmail))
                .child("OTP Numbers").child("Current OTP");
        ref.onDisconnect().setValue(0);

    }
    //Function to generate OTP
    public String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int OTPNumber = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", OTPNumber);
    }

}