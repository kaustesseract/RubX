package com.kaustubh.rubrics;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    private Logout session;
    int tid;
    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    String name;
    String password;

    void image() {
        //    ImageView img = (ImageView) findViewById(R.id.rubrics);
        //   img.animate().translationXBy(-1000f).setDuration(3000);


    }

   /* public void click(View view)
    {
        if(view.getId() == R.id.submit)
        {
            EditText a = (EditText) findViewById(R.id.user);
            String str = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.pass);
            String pass = b.getText().toString();
            session = new Logout(this);

            String password = helper.search(str);
            tid = helper.searchId(str);
            //String passn = helper.search(password);

            if (pass.equals(password))
            {
                session.setLgi(true);
                Intent i = new Intent(getApplicationContext(), BaseActivity.class);
              //  Toast.makeText(getApplicationContext(), "Id is "+id , Toast.LENGTH_LONG).show();
                startActivity(i);
                finish();




            }
            else
            {
                Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_LONG).show();


            }
        }



    }*/

    public void register(View view) {
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {

                    startActivity(new Intent(getApplicationContext(), BaseActivity.class));
                   // Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        };



        Button submit = (Button) findViewById(R.id.submitst);
        session = new Logout(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText a = (EditText) findViewById(R.id.user);
                String str = a.getText().toString();
                EditText b = (EditText) findViewById(R.id.pass);
                String pass = b.getText().toString();
                String password = helper.search(str);
                int pid = helper.searchId(str);

                if (pass.equals(password)) {
                    //  session.setLgi(true);
                    Intent i = new Intent(getApplicationContext(), BaseActivity.class);
                    i.putExtra("pid", pid);
                    // Toast.makeText(getApplicationContext(), "Id is "+pid , Toast.LENGTH_LONG).show();
                    startActivity(i);
                    finish();


                } else {
                    Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_LONG).show();


                }
            }

        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        SignInButton bt = (SignInButton) findViewById(R.id.googs);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });


        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        Toast.makeText(getApplicationContext(), "Google Signin was unsuccessfull", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        // Intent i = new Intent(getApplicationContext(), BaseActivity.class);
        // i.putExtra("tid",tid);
        //startActivity(i);


        // img.setTranslationX(1000f);


        //  Button  donebutton = (Button) findViewById(R.id.button);
        // donebutton.animate().alpha(1f).setDuration(6000);
        //donebutton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleresult(result);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            //  Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            // Intent j = new Intent(getApplicationContext(), BaseActivity.class);
            // startActivity(j);
            finishAffinity();
        }
        return super.onKeyDown(keyCode, event);
    }


    public void handleresult(GoogleSignInResult result)
    {
        if(result.isSuccess())
        {
            GoogleSignInAccount acc = result.getSignInAccount();
             name = acc.getDisplayName();
           // password = acc.getEmail();

        }

    }

}
