package com.jok.pieceofcake.customerSide;

import android.os.Bundle;

import com.jok.pieceofcake.Customer_Navigation;
import com.jok.pieceofcake.R;

import androidx.appcompat.app.AppCompatActivity;

public class Favorites extends Customer_Navigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
    }
}
