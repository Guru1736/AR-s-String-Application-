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

public class admin_signup extends AppCompatActivity {
            EditText fullname , email , password , secure_pin ;
            Button signup ;
            TextView Account;
            FirebaseAuth firebaseAuth;
            ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fullname= findViewById(R.id.fullname);
        email= findViewById(R.id.email);
        password=findViewById(R.id.password);
        secure_pin=findViewById(R.id.pin);
        signup=findViewById(R.id.signup);
        Account=findViewById(R.id.account);
        progressBar=findViewById(R.id.progressBar_admin);
        firebaseAuth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = email.getText().toString().trim();
                String pass = password.getText().toString();
                String secure= secure_pin.getText().toString().trim();

                String pin = "9561";

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

                if (TextUtils.isEmpty(secure))
                {
                    secure_pin.setError("Enter the SecurePin ");
                    return;
                }

                if (secure.equals(pin))
                {
                    Toast.makeText(admin_signup.this, "Pin is Correct ", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);

                    firebaseAuth.createUserWithEmailAndPassword(Email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(admin_signup.this, "Admin Created ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),admin_activity.class));
                                finish();
                            }
                            else
                            {
                                Toast.makeText(admin_signup.this, "Error! :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

                }

                else
                {
                    Toast.makeText(admin_signup.this, "Please Enter the Correct Pin ", Toast.LENGTH_SHORT).show();
                }


            }
        });

        Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login_admin.class));
                finish();
            }
        });

    }
}