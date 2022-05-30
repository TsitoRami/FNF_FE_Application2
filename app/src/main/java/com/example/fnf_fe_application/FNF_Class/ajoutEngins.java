package com.example.fnf_fe_application.FNF_Class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;


import com.example.fnf_fe_application.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class ajoutEngins extends AppCompatActivity {

    private Button ajoutEngins;
    private RadioButton aéronefButton, véhiculButton;

    private TextInputLayout typeEnginsLayout, immatriculationLayout;
    private String enginImpliqué;

    private ArrayList etape1Elements, etape2Elements, etape3Elements, etape4Elements;

    private ArrayList<String> personnesNom, organisationsNom;
    private ArrayList enginsImpliqués, typeEngins, immatriculationEngins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_engins);

        ajoutEngins = (Button) findViewById(R.id.buttonAjouterEngin);
        aéronefButton = (RadioButton) findViewById(R.id.radioButtonAéronef);
        véhiculButton = (RadioButton) findViewById(R.id.radioButtonVéhicule);

        typeEnginsLayout = (TextInputLayout) findViewById(R.id.typeEnginLayoutAdd);
        immatriculationLayout = (TextInputLayout) findViewById(R.id.immatriculationLayoutAdd);

        if(!this.getIntent().hasExtra("nomListe") && !this.getIntent().hasExtra("organisationListe")){
            personnesNom = new ArrayList<String>(Arrays.asList());
            organisationsNom  = new ArrayList<String>(Arrays.asList());
        }
        else {
            personnesNom = this.getIntent().getExtras().getStringArrayList("nomListe");
            organisationsNom = this.getIntent().getExtras().getStringArrayList("organisationListe");
        }
        if(this.getIntent().hasExtra("enginsListe") && this.getIntent().hasExtra("typeEnginsListe") && this.getIntent().hasExtra("immatriculationList")){
            enginsImpliqués = this.getIntent().getExtras().getStringArrayList("enginsListe");
            typeEngins = this.getIntent().getExtras().getStringArrayList("typeEnginsListe");
            immatriculationEngins = this.getIntent().getExtras().getStringArrayList("immatriculationList");
        }

        etape1Elements = this.getIntent().getExtras().getStringArrayList("etape1List");
        etape2Elements = this.getIntent().getExtras().getStringArrayList("etape2List");
        etape3Elements = this.getIntent().getExtras().getStringArrayList("etape3List");
        etape4Elements = this.getIntent().getExtras().getStringArrayList("etape4List");
        //Un genre d'engin sera mis par défaut
        aéronefButton.setChecked(true);
        enginImpliqué = "Aéronef";

        ajoutEngins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ajoutEngins.this, Etape_3.class);
                String typeEngin = typeEnginsLayout.getEditText().getText().toString().trim();
                String immatriculationEngin = immatriculationLayout.getEditText().getText().toString().trim();
                if(!typeEngins.equals("") && !immatriculationEngin.equals("")) {
                    enginsImpliqués.add(enginImpliqué);
                    typeEngins.add(typeEngin);
                    immatriculationEngins.add(immatriculationEngin);
                }
                if((!enginsImpliqués.isEmpty() && !typeEngins.isEmpty() && !immatriculationEngins.isEmpty())
                    || (enginsImpliqués != null && typeEngins != null && immatriculationEngins != null)){
                    intent.putExtra("enginsListe",enginsImpliqués);
                    intent.putExtra("typeEnginsListe",typeEngins);
                    intent.putExtra("immatriculationList",immatriculationEngins);
                }
                transferElements(intent);
                startActivity(intent);
            }
        });

        //listeners permettant de désélectionner l'autre genre si on décide d'en sélectionner un nouveau
        aéronefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(véhiculButton.isChecked()){
                    véhiculButton.setChecked(false);
                    enginImpliqué = "Aéronef";
                }
            }
        });
        véhiculButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aéronefButton.isChecked()){
                    aéronefButton.setChecked(false);
                    enginImpliqué = "Véhicule";
                }
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
    }
}