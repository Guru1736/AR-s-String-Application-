package com.Athrav.ars6strings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class user_Signup extends AppCompatActivity {
        EditText fullname , email,password , phoneno ;
        Button signup ;
        ProgressBar progressBar;
        TextView account ;
        FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fullname= findViewById(R.id.fullname);
        email= findViewById(R.id.email);
        password=findViewById(R.id.password);
        phoneno=findViewById(R.id.Phone);
        signup=findViewById(R.id.signup);
        account=findViewById(R.id.account);
        progressBar=findViewById(R.id.progressBar_admin);
        firebaseAuth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = email.getText().toString().trim();
                String pass = password.getText().toString();




                if (TextUtils.isEmpty(Email))
                {
                    email.setError("Enter the Email");
                    return;
                }

                if (TextUtils.isEmpty(pass))
                {
                    password.setError("Enter the Password");
                    return;
                }


                    progressBar.setVisibility(View.VISIBLE);

                    firebaseAuth.createUserWithEmailAndPassword(Email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(user_Signup.this, "Admin Created ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),user_home.class));
                                finish();
                            }
                            else
                            {
                                Toast.makeText(user_Signup.this, "Error! :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

                }
        });


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login_user.class));
                finish();
            }
        });




         }
    }

