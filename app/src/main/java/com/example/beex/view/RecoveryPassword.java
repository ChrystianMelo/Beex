package com.example.beex.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.beex.R;
import com.example.beex.utils.FieldVerification;
import com.example.beex.utils.Mask.MaskEmail;
import com.example.beex.utils.Mask.MaskTextWatcher;
import com.example.beex.utils.Mask.SimpleMaskFormatter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class RecoveryPassword extends AppCompatActivity {

    TextView notify;
    Button send;
    ImageButton back;
    TextInputLayout cpfLayout;
    TextInputEditText cpf;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_recover);

        notify = findViewById(R.id.notify);
        send   = findViewById(R.id.send);
        back   = findViewById(R.id.back);
        cpf    =  findViewById(R.id.cpf);
        cpfLayout = findViewById(R.id.cpfLayout);
        
        setMaskFieldCPF();
        getArgs();
        setButtonFunctions();
    }

    private void getArgs() {
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null && extras.containsKey("email")) {
            this.email = i.getStringExtra("email");
        }
    }

    public void setMaskFieldCPF(){
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(cpf,smf);
        cpf.addTextChangedListener(mtw);
    }

    public void setButtonFunctions(){
        MaskEmail me = new MaskEmail(email);//Simulation/No api access
        final String maskedEmail = me.emailHidden();//Simulation/No api access

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                FieldVerification fv = new FieldVerification();
                fv.setCpf(Objects.requireNonNull(cpf.getText()).toString());

                if (fv.cpfVerification()){
                    cpfLayout.setError(null);
                    notify.animate().alpha(1);
                    notify.append(maskedEmail);
                }else{
                    cpfLayout.setError("Digite um cpf válido!");
                }
            }
        });
    }
}
