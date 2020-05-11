package com.example.beex.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}



/*
VIEW: visualização
VIEWMODEL: receber informações direto do view
REPOSITORIO: Tratar informações
SERVICES: comunicação banco de dados
UTILS:
*
* */