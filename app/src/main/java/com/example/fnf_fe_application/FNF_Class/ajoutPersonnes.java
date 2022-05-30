package com.example.fnf_fe_application.FNF_Class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.fnf_fe_application.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class ajoutPersonnes extends AppCompatActivity {


    private Button ajoutPersonne;

    private TextInputLayout nomPersonneLayout, nomOrganisationLayout;

    private ArrayList etape1Elements, etape2Elements, etape3Elements, etape4Elements;

    private ArrayList<String> personnesNom, organisationsNom;
    private ArrayList enginsImpliqués, typeEngins, immatriculationEngins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_personnes);

        ajoutPersonne = (Button) findViewById(R.id.buttonAjouterPersonne);

        nomPersonneLayout = (TextInputLayout) findViewById(R.id.nomPersonneInputLayout);
        nomOrganisationLayout = (TextInputLayout) findViewById(R.id.nomOrganisationInputLayout);

        if(this.getIntent().hasExtra("nomListe") && this.getIntent().hasExtra("organisationListe")){
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

        ajoutPersonne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ajoutPersonnes.this, Etape_3.class);
                String nomP = nomPersonneLayout.getEditText().getText().toString().trim();
                String nomO = nomOrganisationLayout.getEditText().getText().toString().trim();
                if(!nomP.equals("") && !nomO.equals("")) {
                    personnesNom.add(nomP);
                    organisationsNom.add(nomO);
                }
                if((!personnesNom.equals("") && !organisationsNom.equals(""))
                    || (personnesNom != null && organisationsNom != null)){
                    intent.putExtra("nomListe", personnesNom);
                    intent.putExtra("organisationListe", organisationsNom);
                }
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
        intent.putExtra("enginsListe", enginsImpliqués);
        intent.putExtra("typeEnginsListe", typeEngins);
        intent.putExtra("immatriculationList", immatriculationEngins);
    }
}