package com.jok.pieceofcake.bakerSide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.jok.pieceofcake.Navigation.Baker_Navigation;
import com.jok.pieceofcake.Navigation.Login;
import com.jok.pieceofcake.R;


public class bakerScreen extends Baker_Navigation {

    Button myMenu;
    Button settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.baker_screen);
        myMenu = (Button) findViewById(R.id.myMenu);
        settings = (Button) findViewById(R.id.settings);
        myMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToBakerMenu();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSettings();
            }
        });


    }

    private void moveToBakerMenu() {
        Intent intent = new Intent(this, BakerMenuActivity.class);
        startActivity(intent);
    }

    public void moveToBakerOrders(View v){
        startActivity(new Intent(getApplicationContext(), BakerOrderActivity.class));
    }
    public void moveToSettings(){
        startActivity(new Intent(getApplicationContext(), baker_settings.class));
    }
    public void LogOutB (View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}
