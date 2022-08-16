package com.Athrav.ars6strings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_user extends AppCompatActivity {
            ImageButton back_button;
            Button  login ;
            EditText email, password ;
            TextView forgot_password , back_text;
            ProgressBar progressBar;
            FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        back_button=findViewById(R.id.back_button);
        login=findViewById(R.id.login);
        email=findViewById(R.id.email_admin);
        forgot_password=findViewById(R.id.signup);
        password=findViewById(R.id.password);
        progressBar=findViewById(R.id.progressBar_admin);
        back_text=findViewById(R.id.back_text);
        mAuth=FirebaseAuth.getInstance();

        Intent intent= getIntent();
        String name = intent.getStringExtra(loginoption.TAG);
        back_text.setText(name);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null)
        {
            Intent intent1 =new Intent(login_user.this,user_home.class);
            startActivity(intent1);
            finish();

        }



        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),loginoption.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(Email))
                {
                    email.setError("Enter the email");
                    return;
                }

                if (TextUtils.isEmpty(pass))
                {
                    password.setError("Enter the password");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                
                mAuth.signInWithEmailAndPassword(Email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(login_user.this, "Login Successful ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), user_home.class));
                            finish();
                        }

                        else
                        {
                            Toast.makeText(login_user.this, "Login Failed : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });


            }
        });


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mail = new EditText(v.getContext());
                AlertDialog.Builder Alert = new AlertDialog.Builder(v.getContext());
                Alert.setTitle("Forgot Password ?");
                Alert.setMessage("Enter The Register Email Id to Receive The Reset Link :");
                Alert.setView(mail);

               Alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                       String email= mail.getText().toString();
                        if (TextUtils.isEmpty(email))
                        {
                            Toast.makeText(login_user.this, "Enter The Register Email", Toast.LENGTH_SHORT).show();
                        }


                        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(login_user.this, "Reset Link Has Been Send ", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(login_user.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                   }
               });

               Alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });


                Alert.create().show();
            }
        });







    }
}