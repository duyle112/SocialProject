package com.english.howf;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private Firebase root;
    private Button btnsignup_;
    private EditText edtusername_;
    private EditText edtpassword_;
    private EditText edtretypepwd_;
    private FirebaseAuth mAuth;
// ...

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Firebase.setAndroidContext(this);
         mAuth=FirebaseAuth.getInstance();
         init();
         btnsignup_.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                authenticate();
             }
         });
    }





    public void authenticate()
    {
        if(edtpassword_.getText().toString().equals(edtretypepwd_.getText().toString()))
        {
            try {
                mAuth.createUserWithEmailAndPassword(edtusername_.getText().toString(), edtpassword_.getText().toString())
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Succeed", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .addOnFailureListener(SignupActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignupActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
            }catch (Exception e)
            {
                Toast.makeText(SignupActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(SignupActivity.this,"These passwords don't match.",Toast.LENGTH_LONG).show();
        }
    }
    public void init()
    {
        btnsignup_=(Button)findViewById(R.id.btnsignup);
        edtusername_=(EditText)findViewById(R.id.edtusername);
        edtpassword_=(EditText)findViewById(R.id.edtpassword);
        edtretypepwd_=(EditText)findViewById(R.id.edtretypepwd);
        edtpassword_.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        edtretypepwd_.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        root=new Firebase("https://howf-56d85.firebaseio.com/");
    }
}
