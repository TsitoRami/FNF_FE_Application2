package com.example.fnf_fe_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Connect_After extends AppCompatActivity {

    private Button confirmerButton, annulerButton;

    private Boolean logBeforeAfter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_after);

        confirmerButton = (Button) findViewById(R.id.buttonConfirmer2);
        annulerButton = (Button) findViewById(R.id.buttonAnnuler2);

        confirmerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Connect_After.this, loginPage.class);
                intent.putExtra("textPrincipal", "Formulaire envoyé !\n" +
                        "Les services ont été notifiés");
                intent.putExtra("code", 2);
                intent.putExtra("logBefore", false);
                startActivity(intent);
            }
        });

        annulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Connect_After.this, MainActivity.class);
                intent.putExtra("textPrincipal", "Formulaire annulé !\n" +
                        "Vous pouvez choisir d’en faire un autre");
                intent.putExtra("code", 1);
                startActivity(intent);
            }
        });
    }
}