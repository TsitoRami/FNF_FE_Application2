package com.example.fnf_fe_application.FNF_Class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fnf_fe_application.Connect_After;
import com.example.fnf_fe_application.MainActivity;
import com.example.fnf_fe_application.R;

import java.util.ArrayList;
import java.util.Arrays;

public class FNF_Confirmation extends AppCompatActivity {

    private Button step1Retour, step2Retour, step3Retour, step4Retour;
    private Button annulerButton, confirmerButton;

    private ArrayList etape1Elements, etape2Elements, etape3Elements, etape4Elements;

    private ArrayList<String> personnesNom, organisationsNom;
    private ArrayList enginsImpliqués, typeEngins, immatriculationEngins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fnf_confirmation);

        //Les différents de boutons de retour
        step1Retour = findViewById(R.id.buttonBackStep1);
        step2Retour = findViewById(R.id.buttonBackStep2);
        step3Retour = findViewById(R.id.buttonBackStep3);
        step4Retour = findViewById(R.id.buttonBackStep4);

        if(!this.getIntent().hasExtra("nomListe") && !this.getIntent().hasExtra("organisationListe")){
            personnesNom = new ArrayList<String>(Arrays.asList());
            organisationsNom  = new ArrayList<String>(Arrays.asList());
        }
        else {
            personnesNom = this.getIntent().getExtras().getStringArrayList("nomListe");
            organisationsNom = this.getIntent().getExtras().getStringArrayList("organisationListe");
        }
        if(!this.getIntent().hasExtra("enginsListe") && !this.getIntent().hasExtra("typeEnginsListe") && !this.getIntent().hasExtra("immatriculationList")){
            enginsImpliqués = new ArrayList<String>(Arrays.asList());
            typeEngins  = new ArrayList<String>(Arrays.asList());
            immatriculationEngins = new ArrayList<String>(Arrays.asList());
        }
        else {
            enginsImpliqués = this.getIntent().getExtras().getStringArrayList("enginsListe");
            typeEngins = this.getIntent().getExtras().getStringArrayList("typeEnginsListe");
            immatriculationEngins = this.getIntent().getExtras().getStringArrayList("immatriculationList");
        }

        etape1Elements = this.getIntent().getExtras().getStringArrayList("etape1List");
        etape2Elements = this.getIntent().getExtras().getStringArrayList("etape2List");
        etape3Elements = this.getIntent().getExtras().getStringArrayList("etape3List");
        etape4Elements = this.getIntent().getExtras().getStringArrayList("etape4List");

        step1Retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FNF_Confirmation.this, Etape_1.class);
                transferElements(intent);
                startActivity(intent);
            }
        });
        step2Retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FNF_Confirmation.this, Etape_2.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        step3Retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FNF_Confirmation.this, Etape_3.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        step4Retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FNF_Confirmation.this, Etape_4.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        //Les boutons de confirmation et d'annulation
        annulerButton = findViewById(R.id.buttonAnnuler);
        confirmerButton = findViewById(R.id.buttonConfirmer);

        annulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FNF_Confirmation.this, MainActivity.class);
                intent.putExtra("textPrincipal", "Formulaire annulé !\n" +
                        "Vous pouvez choisir d’en faire un autre");
                intent.putExtra("code", 1);
                startActivity(intent);
            }
        });

        confirmerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FNF_Confirmation.this, Connect_After.class);
                transferElements(intent);
                startActivity(intent);
            }
        });
    }

    private void transferElements(Intent intent) {
        intent.putExtra("etape1List", etape1Elements);
        intent.putExtra("etape2List", etape2Elements);
        intent.putExtra("etape3List", etape3Elements);
        intent.putExtra("etape4List", etape4Elements);
        intent.putExtra("nomListe", personnesNom);
        intent.putExtra("organisationListe", organisationsNom);
        intent.putExtra("enginsListe", enginsImpliqués);
        intent.putExtra("typeEnginsListe", typeEngins);
        intent.putExtra("immatriculationList", immatriculationEngins);
    }
}
