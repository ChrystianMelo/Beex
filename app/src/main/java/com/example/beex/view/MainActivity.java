package com.example.beex.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.beex.R;
import com.example.beex.repository.FieldVerification;
import com.example.beex.services.ScheduleActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {

    ImageView icon;
    RelativeLayout screen;
    TextInputLayout mailLayout, passLayout;
    TextInputEditText mail, pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        icon = findViewById(R.id.icon);
        screen = findViewById(R.id.screen);
        mailLayout =  findViewById(R.id.mailLayout);
        passLayout =  findViewById(R.id.passLayout);
        mail    =  findViewById(R.id.email);
        pass    =  findViewById(R.id.pass);
        login   = findViewById(R.id.login);

        setAnimation();
        setButtonFunctions();
    }

    public void setAnimation() {
        icon.animate().translationYBy(-500).setDuration(1500);
        screen.animate().setDuration(2000).alpha(1);
    }

    private void setButtonFunctions() {
        final Intent home = new Intent(this, HomeScreen.class);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FieldVerification fv = new FieldVerification();
                fv.setEmail(mail.getText().toString());
                fv.setPass(pass.getText().toString());

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

    public void Recover(View v){
        FieldVerification fv = new FieldVerification();
        fv.setEmail(mail.getText().toString());
        if (fv.emailVerification()) {
                Intent recover = new Intent(this,RecoveryPassword.class);
                recover.putExtra("email", mail.getText().toString());
                startActivity(recover);
        }
    }

    /*public void Create(View v){
        startActivity(new Intent(this,CreateAccount.class));
    }*/

    public void Create(View v){
        startActivity(new Intent(this, ScheduleActivity.class));
    }


}