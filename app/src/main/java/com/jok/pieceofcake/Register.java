package com.jok.pieceofcake;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jok.pieceofcake.bakerSide.Baker;
import com.jok.pieceofcake.customerSide.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    private FirebaseAuth FireLog;// fire base authentication
    FirebaseDatabase DB; //firebase DB

    Typeface font; // font
    EditText password_handler, email_handler, inputPhone, inputFullName;//input bars
    TextView pass, mail, phone, name;//names near input bars
    Button confirm;
    String password, email, Phone, fullName;
    String userID;
    CheckBox inputBaker, inputCustomer;
    ProgressBar progressBar2;
    DatabaseReference usersRef;
    DatabaseReference bakersRef;
    DatabaseReference customersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        font = Typeface.createFromAsset(this.getAssets(), "fonts/Anka CLM Bold.ttf");
        FireLog = FirebaseAuth.getInstance();
        DB = FirebaseDatabase.getInstance();
        usersRef = DB.getReference("Users");
        bakersRef = DB.getReference("Users/Bakers");
        customersRef = DB.getReference("Users/Customers");

        retrieve();//retrieve all elements in the activity
        setFonts();


    }

    public void register(View v) {

        //saving context of user details
        password = password_handler.getText().toString().trim();
        email = email_handler.getText().toString().trim();
        Phone = inputPhone.getText().toString().trim();
        fullName = inputFullName.getText().toString().trim();

        //check that all the inputs are valid
        if (TextUtils.isEmpty(email)) {
            email_handler.setError("נא למלא E-mail");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            password_handler.setError("נא למלא סיסמא.");
            return;
        }

        if (password.length() < 6) {
            password_handler.setError("סיסמא חייבת להיות עם 6 תווים לפחות.");
            return;
        }

        if (TextUtils.isEmpty(fullName)) {
            inputFullName.setError("זהו שדה חובה.");
            return;
        }

        if (TextUtils.isEmpty(Phone)) {
            inputPhone.setError("יש למלא מספר טלפון");
            return;
        }


        if ((!inputBaker.isChecked()) && (!inputCustomer.isChecked())) {
            inputBaker.setError("יש לבחור אופה/לקוח");
            return;
        }

        FireLog.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar2.setVisibility(View.VISIBLE);
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("[INFO]", "createUserWithEmail:success");
                            FirebaseUser user = FireLog.getCurrentUser();

                            userID = user.getUid();
                            if (inputBaker.isChecked()) {
                                Baker baker = new Baker(email, fullName, Phone, "address", "Baker");
                                try {
                                    bakersRef.child(userID).setValue(baker);
                                    Toast.makeText(Register.this, "Baker added", Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    Log.d(TAG, "onFailue" + e.toString());
                                }
                            } else {
                                Customer customer = new Customer(email, fullName, Phone, "address", "Customer");
                                try {
                                    customersRef.child(userID).setValue(customer);
                                    Toast.makeText(Register.this, "Customer added", Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    Log.d(TAG, "onFailue" + e.toString());
                                }
                            }
                            moveToLogin();
                        }
                        else {
                            progressBar2.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user.
                            Log.w("[INFO]", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    /**
     * retrieve all elements in the activity
     */
    public void retrieve() {
        //retrieving details EditText input bars
        password_handler = findViewById(R.id.PasswordInput);
        email_handler = findViewById(R.id.EmailInput);
        inputPhone = findViewById(R.id.inputPhone);
        inputFullName = findViewById(R.id.inputFullName);

        progressBar2 = findViewById(R.id.progressBar2);

        //retrieving the names near the input bars
        pass = findViewById(R.id.Password);
        mail = findViewById(R.id.Email);
        phone = findViewById(R.id.Phone);
        name = findViewById(R.id.fullName);

        //confirm button
        confirm = findViewById(R.id.confirm);

        //checkboxes of baker and customer
        inputBaker = findViewById(R.id.ifBaker);
        inputCustomer = findViewById(R.id.ifCustomer);

    }

    public void setFonts() {
        pass.setTypeface(font);
        mail.setTypeface(font);
        phone.setTypeface(font);
        pass.setTypeface(font);
        name.setTypeface(font);
        inputBaker.setTypeface(font);
        inputCustomer.setTypeface(font);
        confirm.setTypeface(font);
    }

    private void moveToLogin() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }

}
