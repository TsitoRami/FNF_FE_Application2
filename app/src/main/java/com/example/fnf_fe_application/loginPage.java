package com.example.fnf_fe_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class loginPage extends AppCompatActivity {

    private Button connectButton, annulerButton;
    private TextInputLayout loginInput, mdpInput;

    private Boolean logBeforeAfter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        logBeforeAfter = this.getIntent().getExtras().getBoolean("logBefore", false);

        connectButton = (Button) findViewById(R.id.buttonLogin);
        annulerButton = (Button) findViewById(R.id.buttonAnnulerLog);

        loginInput = (TextInputLayout) findViewById(R.id.loginInputLoginLayout);
        mdpInput = (TextInputLayout) findViewById(R.id.passwordInputLoginLayout);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginPage.this, MainActivity.class);
                redirection(logBeforeAfter, 2, "Formulaire envoyé !\n" +
                        "Les services ont été notifiés", intent);
                startActivity(intent);
            }
        });

        annulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginPage.this, MainActivity.class);
                redirection(logBeforeAfter, 1, "Formulaire annulé !\n" +
                        "Vous pouvez choisir d’en faire un autre", intent);
                startActivity(intent);
            }
        });
    }

    private void redirection (Boolean logBefore, int code, String text, Intent intent){
        if(!logBefore){
            intent.putExtra("textPrincipal", text);
            intent.putExtra("code", code);
        }
    }
}