package a.m.mad314_pd_1_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,password,confirmPassword;
    TextView alreadyUser;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        alreadyUser = findViewById(R.id.alreadyUser_textView);
        signUp = findViewById(R.id.signUpButton);
        name = findViewById(R.id.name_editText);
        email = findViewById(R.id.email_editText);
        password = findViewById(R.id.password_editText);
        confirmPassword = findViewById(R.id.confirmPassword);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(email.getText().toString()))
                {
                    email.setError("Email cannot be empty");
                    email.requestFocus();
                }
                else if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Password cannot be empty");
                    password.requestFocus();
                }
                else if (TextUtils.isEmpty(name.getText().toString())){
                    name.setError("Name cannot be empty");
                    name.requestFocus();
                }

                else if (TextUtils.isEmpty(confirmPassword.getText().toString())){
                    confirmPassword.setError("confirm password cannot be empty");
                    confirmPassword.requestFocus();
                }
                else
                {
                    String emailID=email.getText().toString();
                    String pass=password.getText().toString();
                    String nameFromUser = name.getText().toString();
                    String inputConfirmPassword = confirmPassword.getText().toString();
                    if (!pass.equals(inputConfirmPassword))
                    {
                        confirmPassword.setError("confirm does not match");
                        confirmPassword.requestFocus();
                    }
                    else
                    {
                        Intent intentToLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intentToLogin);
                    }

                }
            }
        });


        alreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(_intent);

            }
        });
        
    }
}
