package a.m.mad314_pd_1_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    Button buttonLogin;
    Intent intentToRegister,intentToHome;
    EditText editTextUsername, editTextPassword;
    TextView textViewRegister;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        firebaseAuth=FirebaseAuth.getInstance();


        setContentView(R.layout.activity_login);
        //final String emailSample="movie@rental.com";
        //final String passwordSample="123456";

        intentToHome=new Intent(this,MainActivity.class);
        intentToRegister=new Intent(this,RegisterActivity.class);

        editTextUsername=findViewById(R.id.editText_login_username);
        editTextPassword=findViewById(R.id.editText_login_password);
        buttonLogin=findViewById(R.id.button_login);
        textViewRegister=findViewById(R.id.textView_register);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                HomeFragment fragment=new HomeFragment();
//                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

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
                    loginUser(email,pass);

                    /*
                    if(email.equals(emailSample) && pass.equals(passwordSample)) {
                        startActivity(intentToHome);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Wrong Credentials", Toast.LENGTH_LONG).show();
                    }

                     */
                }
            }
        });


        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentToRegister);
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            //Toast.makeText(getActivity().getApplicationContext(),"Already logged in",Toast.LENGTH_LONG).show();
            updateUI(firebaseUser);
        }
    }


    public void loginUser(String email, String password)
    {
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            firebaseUser=firebaseAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT);
                            updateUI(firebaseUser);
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


    public void updateUI(FirebaseUser user)
    {

        Bundle bundle = new Bundle();
        bundle.putParcelable("user",user);
        startActivity(intentToHome);
    }



}
