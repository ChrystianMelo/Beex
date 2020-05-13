package com.example.beex.view.account;

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
import com.example.beex.utils.mask.MaskEmail;
import com.example.beex.utils.mask.tox.MaskTextWatcher;
import com.example.beex.utils.mask.tox.SimpleMaskFormatter;
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
    String patternCPF = "NNN.NNN.NNN-NN";

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_pass);

        notify = findViewById(R.id.txt_recoverPass_notify);
        send   = findViewById(R.id.btn_recoverPass_send);
        back   = findViewById(R.id.btn_recoverPass_back);
        cpf    =  findViewById(R.id.tiet_recoverPass_cpf);
        cpfLayout = findViewById(R.id.til_recoverPass_cpfLayout);

        maskFieldCPF(cpf, patternCPF);

        setEmail(getArgs("email"));
        setEmail(maskEmail(getEmail()));

        setButtonFunctions();
    }

    public String getArgs(String KEY) {
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras != null && extras.containsKey(KEY)) {
            return i.getStringExtra(KEY);
        }
        return "UNKNOWN";
    }

    public void maskFieldCPF(TextInputEditText field, String pattern){
        SimpleMaskFormatter smf = new SimpleMaskFormatter(pattern);
        MaskTextWatcher mtw = new MaskTextWatcher(field,smf);
        field.addTextChangedListener(mtw);
    }

    public String maskEmail(String mail){
        MaskEmail maskedEmail = new MaskEmail();
        maskedEmail.setEmail(mail);
        return maskedEmail.emailHidden();
    }

    public int verification(String field){
        if (field.isEmpty()) return 0;
        if(field.length() < 14)
            return -1;
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setErrorMessages(TextInputEditText txt, TextInputLayout field) {
        int vf = verification(Objects.requireNonNull(txt.getText()).toString());
        if (vf == 1) {
            field.setError(null);
        } else if (vf == -1) {
            field.setError("Preencha corretamente");
        } else if (vf == 0) {
            field.setError("Campo vazio");
        }
    }

    public boolean verifyFields() {
        return cpfLayout.getError() == null;
    }

    public void cleanErrorMessages(TextInputLayout field) {
        field.setError(null);
    }
    public void setButtonFunctions(){
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
                cleanErrorMessages(cpfLayout);
                setErrorMessages(cpf,cpfLayout);
                if(verifyFields()){
                    notify.animate().alpha(1);
                    notify.append("\n'"+email+"'");
                    send.setClickable(false);
                }
            }
        });
    }
}
