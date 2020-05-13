package com.example.beex.utils;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beex.view.account.AccountManagement;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        changeScreen();
    }

    public void changeScreen(){
        Intent intent = new Intent(this, AccountManagement.class);
        startActivity(intent);
        finish();
    }
}