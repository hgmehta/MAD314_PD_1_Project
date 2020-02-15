package a.m.mad314_pd_1_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    Button buttonLogin;
    Intent intentToRegister,intentToHome;
    EditText editTextUsername, editTextPassword;
    TextView textViewRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername=findViewById(R.id.editText_login_username);
        editTextPassword=findViewById(R.id.editText_login_password);
        buttonLogin=findViewById(R.id.button_login);
        textViewRegister=findViewById(R.id.textView_register);



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(editTextUsername.getText().toString()))
                {
                    editTextUsername.setError("Email cannot be empty");
                    editTextUsername.requestFocus();
                }
                else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
                    editTextPassword.setError("Password cannot be empty");
                    editTextPassword.requestFocus();
                }
                else
                {
                    String email=editTextUsername.getText().toString();
                    String pass=editTextPassword.getText().toString();
                    startActivity(intentToHome);
                }
            }
        });
    }



}
