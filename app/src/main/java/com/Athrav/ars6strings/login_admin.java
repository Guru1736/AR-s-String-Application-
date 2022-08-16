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
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_admin extends AppCompatActivity {

    ImageButton back_button;
    Button login ;
    EditText email, password ,secure ;
    TextView signup , back_text;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        back_button=findViewById(R.id.back_button);
        login=findViewById(R.id.login);
        email=findViewById(R.id.email_admin);
        signup=findViewById(R.id.signup);
        password=findViewById(R.id.password);
        progressBar=findViewById(R.id.progressBar2);
        back_text=findViewById(R.id.back_text);
        firebaseAuth=FirebaseAuth.getInstance();
        secure=findViewById(R.id.secure_code);


        Intent intent= getIntent();
        String name = intent.getStringExtra(loginoption.TAG);
        back_text.setText(name);

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
                                         String mail = email.getText().toString();
                                         String pass = password.getText().toString();
                                         String code = secure.getText().toString();
                                         String pin = "9561";

                                         if (TextUtils.isEmpty(mail)) {
                                             email.setError("Enter the Email ");
                                             return;
                                         }
                                         if (TextUtils.isEmpty(pass)) {
                                             password.setError("Enter the Password ");
                                             return;
                                         }

                                         if (TextUtils.isEmpty(code))
                                         {
                                             secure.setError("Enter Pin Please");
                                             return;
                                         }

                                         if (code.equals(pin)) {
                                             progressBar.setVisibility(View.VISIBLE);

                                             firebaseAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                 @Override
                                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                                     if (task.isSuccessful()) {
                                                         Toast.makeText(login_admin.this, "Login Successful ", Toast.LENGTH_SHORT).show();
                                                         startActivity(new Intent(getApplicationContext(), admin_activity.class));
                                                         finish();
                                                     } else {
                                                         Toast.makeText(login_admin.this, "Login Failed ", Toast.LENGTH_SHORT).show();
                                                         progressBar.setVisibility(View.GONE);
                                                     }
                                                 }
                                             });
                                         } else {
                                             Toast.makeText(login_admin.this, "Pin is incorrect ", Toast.LENGTH_SHORT).show();

                                         }

                                     }
                                 });





        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             startActivity(new Intent(getApplicationContext(),admin_signup.class));
             finish();
            }
        });

    }
}
