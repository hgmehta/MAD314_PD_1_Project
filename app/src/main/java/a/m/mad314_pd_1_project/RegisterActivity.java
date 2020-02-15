package a.m.mad314_pd_1_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {


    TextView alreadyUser;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        alreadyUser = findViewById(R.id.alreadyUser_textView);
        signUp = findViewById(R.id.signUpButton);

        alreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(_intent);
            }
        });
        
    }
}
