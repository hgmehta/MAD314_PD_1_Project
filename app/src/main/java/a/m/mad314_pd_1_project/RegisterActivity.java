package a.m.mad314_pd_1_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,password,confirmPassword;
    TextView alreadyUser;
    Button signUp;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth=FirebaseAuth.getInstance();

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
                        createUsers(nameFromUser,emailID,pass);

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

    public void createUsers(final String name, final String email, String password)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    Map<String, Object> usermap = new HashMap<>();
                    usermap.put("name", name);
                    usermap.put("email", email);


                    db.collection("user").document(user.getUid()).set(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "Register success", Toast.LENGTH_LONG).show();
                            FirebaseAuth.getInstance().signOut();
                            Intent _intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(_intent);

                        }
                    });
                }else
                {
                    System.out.println("Error"+task.getException());
                }
            }

        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
