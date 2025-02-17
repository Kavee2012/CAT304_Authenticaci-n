package com.example.auntentication;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Passwordgenarator extends AppCompatActivity {
    private EditText editPasswordSize;
    private TextView textPasswordGenerated,textErrorMessage;
    private CheckBox checkLower, checkUpper,checkSpecialChar, checkNumeric;
    private Button btnGenerate, btnCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordgenarator);

        initViews();

        clickListeners();
    }

    private void clickListeners() {
        btnGenerate.setOnClickListener(view -> {
            int passwordSize = Integer.parseInt(editPasswordSize.getText().toString());

            textErrorMessage.setText("");

            if(passwordSize<5){
                textErrorMessage.setText("Password Size must be greater than 5");
                return;
            }

            Passwordgenarator2.clear();
            if(checkLower.isChecked()) Passwordgenarator2.add(new LowerCaseGenerator());
            if(checkNumeric.isChecked()) Passwordgenarator2.add(new NumericGenerator());
            if(checkUpper.isChecked()) Passwordgenarator2.add(new UpperCaseGenerator());
            if(checkSpecialChar.isChecked()) Passwordgenarator2.add(new SpecialCharGenerator());


            if(Passwordgenarator2.isEmpty()){
                textErrorMessage.setText("Please select at least one password content type");
                return;
            }

            String passwrd = Passwordgenarator2.generatePassword(passwordSize);
            textPasswordGenerated.setText(passwrd);

        });

        btnCopy.setOnClickListener(view ->{
            ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            manager.setPrimaryClip(ClipData.newPlainText("password",textPasswordGenerated.getText().toString()));
            Toast.makeText(this, "Password Copied", Toast.LENGTH_SHORT).show();
        });
    }

    private void initViews() {
        editPasswordSize = findViewById(R.id.edit_pwd_size);
        textPasswordGenerated = findViewById(R.id.text_password_result);
        textErrorMessage = findViewById(R.id.text_error);
        checkLower = findViewById(R.id.check_lower);
        checkUpper = findViewById(R.id.check_upper);
        checkSpecialChar = findViewById(R.id.check_special_char);
        checkNumeric = findViewById(R.id.check_numeric);
        btnGenerate = findViewById(R.id.btn_generate);
        btnCopy = findViewById(R.id.btn_copy);
    }
}