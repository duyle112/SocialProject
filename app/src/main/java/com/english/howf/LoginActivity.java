package com.english.howf;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button btnlogin_;
    private TextView txtsignup_;
    private EditText edtusername_;
    private EditText edtpassword_;
    private Firebase root;
    private String name;
    private String email;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        root=new Firebase("https://howf-56d85.firebaseio.com/");
        mAuth=FirebaseAuth.getInstance();
        init();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("tag", "onAuthStateChanged:signed_in:" + user.getUid());
                    email=user.getEmail();
                    name=user.getUid();
                } else {
                    // User is signed out
                    Log.d("tag", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }
    public void init()
    {
        btnlogin_=(Button)findViewById(R.id.btnlogin);
        txtsignup_=(TextView)findViewById(R.id.txtsignup);
        edtusername_=(EditText)findViewById(R.id.edtusername);
        edtpassword_=(EditText)findViewById(R.id.edtpassword);
        btnlogin_.setOnClickListener(this);
        txtsignup_.setOnClickListener(this);
        edtpassword_.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnlogin:
                try {
                mAuth.signInWithEmailAndPassword(edtusername_.getText().toString(), edtpassword_.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this,"Login Succesful!",
                                            Toast.LENGTH_SHORT).show();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("name",name);
                                    bundle.putString("email",email);
                                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                    intent.putExtra("bundle",bundle);
                                    startActivity(intent);
                                }
                            }
                        })
                        .addOnFailureListener(LoginActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txtsignup:
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
