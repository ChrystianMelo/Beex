package com.example.beex.view.account;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.beex.R;
import com.example.beex.utils.FieldVerification;
import com.example.beex.view.schedule.ScheduleActivity;
import com.example.beex.view.feed.HomeScreen;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class AccountManagement extends AppCompatActivity {

    ImageView icon;
    LinearLayout screen;
    TextInputLayout mailLayout, passLayout;
    TextInputEditText mail, pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        icon = findViewById(R.id.icon);
        screen = findViewById(R.id.screen);
        mailLayout =  findViewById(R.id.mailLayout);
        passLayout =  findViewById(R.id.passLayout);
        mail    =  findViewById(R.id.email);
        pass    =  findViewById(R.id.pass);
        login   = findViewById(R.id.login);

        //setAnimation();
        setButtonFunctions();
    }

    public void setAnimation() {
        icon.animate().translationYBy(-500).setDuration(1500);
        screen.animate().setDuration(2000).alpha(1);
    }

    private void setButtonFunctions() {
        final Intent home = new Intent(this, HomeScreen.class);
        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                FieldVerification fv = new FieldVerification();
                fv.setEmail(Objects.requireNonNull(mail.getText()).toString());
                fv.setPass(Objects.requireNonNull(pass.getText()).toString());

                if (fv.emailVerification() && fv.passVerification()){
                    passLayout.setError(null);
                    mailLayout.setError(null);
                    startActivity(home);
                }else if (!fv.emailVerification() && !fv.passVerification()){
                    mailLayout.setError("Digite um email válido!");
                    passLayout.setError("Digite uma senha válida!");
                }else if (!fv.emailVerification() || !fv.passVerification()){
                    if (fv.passVerification()) {
                        mailLayout.setError("Digite um email válido!");
                        passLayout.setError(null);
                    }else if (fv.emailVerification()) {
                        passLayout.setError("Digite uma senha válida!");
                        mailLayout.setError(null);
                    }
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void Recover(View v){
        FieldVerification fv = new FieldVerification();
        fv.setEmail(Objects.requireNonNull(mail.getText()).toString());
        if (fv.emailVerification()) {
                mailLayout.setError(null);
                Intent recover = new Intent(this, RecoveryPassword.class);
                recover.putExtra("email", mail.getText().toString());
                startActivity(recover);
        }else mailLayout.setError("Digite um email válido!");
    }

    /*public void Create(View v){
        startActivity(new Intent(this,CreateAccount.class));
    }*/

    public void Create(View v){
        startActivity(new Intent(this, ScheduleActivity.class));
    }


}