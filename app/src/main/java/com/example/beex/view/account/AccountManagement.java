package com.example.beex.view.account;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;

import com.example.beex.R;
import com.example.beex.view.schedule.ScheduleActivity;
import com.example.beex.view.feed.HomeScreen;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class AccountManagement extends AppCompatActivity {

    ImageView icon;
    ConstraintLayout screen;
    TextInputLayout mailLayout, passLayout;
    TextInputEditText mail, pass;
    Space topOfScreen;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        topOfScreen = findViewById(R.id.spc_account_top);
        icon = findViewById(R.id.img_account_icon);
        screen = findViewById(R.id.ll_account_content);
        mailLayout =  findViewById(R.id.til_account_mailLayout);
        passLayout =  findViewById(R.id.til_account_passLayout);
        mail    =  findViewById(R.id.tiet_account_mail);
        pass    =  findViewById(R.id.tiet_account_pass);
        login   = findViewById(R.id.btn_account_login);

        getPosition();
        setButtonFunctions();
    }

    void getPosition(){
        topOfScreen.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int local;
                topOfScreen.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                local = icon.getBottom() - topOfScreen.getBottom();
                Log.d("X and Y Point", local + " ");
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                    setAnimation(-1*local);
                else
                    setAnimation(-1*local);
            }
        });
    }

    public void setAnimation(int height) {
        icon.animate().translationY(Math.round(height)).setDuration(1500);
        screen.animate().setDuration(2500).alpha(1);
    }

    public int verification(String field){
        if (field.isEmpty()) return 0;
        if ((field.indexOf('.') == -1 &&
            field.indexOf('@') == -1 )||
            field.length() == 2)
                return -1;
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setErrorMessages(TextInputEditText txt, TextInputLayout field) {
        int vf = verification(Objects.requireNonNull(txt.getText()).toString());
        if (vf == 1) {
            field.setError(null);
        } else if (vf == -1) {
            field.setError("Resposta inválida");
        } else if (vf == 0) {
            field.setError("Campo vazio");
        }
    }

    public boolean verifyFields() {
        return mailLayout.getError() == null &&
                passLayout.getError() == null;
    }

    public void cleanErrorMessages(TextInputLayout field) {
        field.setError(null);
    }

    private void setButtonFunctions() {

        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                cleanErrorMessages(mailLayout);
                cleanErrorMessages(passLayout);
                setErrorMessages(mail,mailLayout);
                setErrorMessages(pass,passLayout);
                if(verifyFields()) startActivity(new Intent(getApplicationContext(), HomeScreen.class));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void Recover(View v){
        cleanErrorMessages(passLayout);
        setErrorMessages(mail,mailLayout);
        if (verifyFields()){
                Intent recover = new Intent(this, RecoveryPassword.class);
                recover.putExtra("email", Objects.requireNonNull(mail.getText()).toString());
                startActivity(recover);
        }
    }


    public void Create(View v){
        startActivity(new Intent(this, ScheduleActivity.class));
    }


}