package com.jok.pieceofcake.bakerSide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jok.pieceofcake.Objects.Address;
import com.jok.pieceofcake.Navigation.Baker_Navigation;
import com.jok.pieceofcake.R;

import java.util.HashMap;
import java.util.Map;

public class baker_settings extends Baker_Navigation {
    private FirebaseAuth FireLog = FirebaseAuth.getInstance();// fire base authentication
    String userID;
    FirebaseDatabase DB;
    DatabaseReference BakerRef;
    FirebaseUser user;


    EditText oldPassword;
    EditText newPassword;
    EditText city;
    EditText street;
    EditText numOfHouse;
    EditText floor;
    EditText appartment;
    EditText phone;
    Button confirm;

    String newPasswordS,cityS, streetS, numOfHouseS, floorS,appartmentS, phoneS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_baker_settings);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        city = findViewById(R.id.city);
        street = findViewById(R.id.street);
        numOfHouse = findViewById(R.id.house);
        floor = findViewById(R.id.floor);
        appartment = findViewById(R.id.appartment);
        phone = findViewById(R.id.phone);
        confirm = (Button)findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPasswordS = newPassword.getText().toString().trim();
                cityS = city.getText().toString().trim();
                streetS = street.getText().toString().trim();
                numOfHouseS = numOfHouse.getText().toString().trim();
                floorS = floor.getText().toString().trim();
                appartmentS = appartment.getText().toString().trim();
                phoneS = phone.getText().toString().trim();

                Address address = new Address(cityS, streetS, numOfHouseS, floorS, appartmentS);
                updateBaker(address,phoneS,newPasswordS);

            }
        });

    }

    public void updateBaker(final Address address, final String phone, final String newPasswordS){
        user = FireLog.getCurrentUser();
        userID = FireLog.getCurrentUser().getUid();
        DB = FirebaseDatabase.getInstance();
        BakerRef = DB.getReference("Users/Bakers").child(userID);
        Map<String ,Object> updates= new HashMap<>();
        updates.put("phone",phone);
        updates.put("address",address);
        BakerRef.updateChildren(updates);
        //user.updatePassword(newPasswordS);
        if(!(newPasswordS.isEmpty())) user.updatePassword(newPasswordS);
        Toast.makeText(getApplicationContext(), "הפרטים עודכנו בהצלחה",
                Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), bakerScreen.class));


    }

}