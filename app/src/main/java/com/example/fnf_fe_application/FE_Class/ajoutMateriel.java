package com.example.fnf_fe_application.FE_Class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.fnf_fe_application.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class ajoutMateriel extends AppCompatActivity {

    private Button ajoutMateriel;
    private ArrayList datesList, designationList, quantityList, obsList;
    private TextInputLayout dateMaterielLayout,
            designationMaterielLayout,
            quantityLayout,
            obsLayout;
    private ArrayList datesListTravaux, designationListTravaux, intervenantListTravaux, tempsPassedListTravaux;

    private ArrayList FEElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_materiel);

        FEElements = this.getIntent().getExtras().getStringArrayList("FEListElements");
        if(!this.getIntent().hasExtra("datesTravauxListe")
                && !this.getIntent().hasExtra("designationTravauxListe")
                && !this.getIntent().hasExtra("intervenantTravauxListe")
                && !this.getIntent().hasExtra("tempsPasséListe")){
            datesListTravaux = new ArrayList<String>(Arrays.asList());
            designationListTravaux  = new ArrayList<String>(Arrays.asList());
            intervenantListTravaux = new ArrayList<String>(Arrays.asList());
            tempsPassedListTravaux = new ArrayList<String>(Arrays.asList());
        }
        else {
            datesListTravaux = this.getIntent().getExtras().getStringArrayList("datesTravauxListe");
            designationListTravaux = this.getIntent().getExtras().getStringArrayList("designationTravauxListe");
            intervenantListTravaux = this.getIntent().getExtras().getStringArrayList("intervenantTravauxListe");
            tempsPassedListTravaux = this.getIntent().getExtras().getStringArrayList("tempsPasséListe");
        }
        if(this.getIntent().hasExtra("datesMaterielListe")
                && this.getIntent().hasExtra("designationMaterielListe")
                && this.getIntent().hasExtra("quantitéMaterielListe")
                && this.getIntent().hasExtra("observationListe")) {
            datesList = this.getIntent().getExtras().getStringArrayList("datesMaterielListe");
            designationList = this.getIntent().getExtras().getStringArrayList("designationMaterielListe");
            quantityList = this.getIntent().getExtras().getStringArrayList("quantitéMaterielListe");
            obsList = this.getIntent().getExtras().getStringArrayList("observationListe");
        }

        dateMaterielLayout = (TextInputLayout) findViewById(R.id.dateMaterielLayout);
        designationMaterielLayout = (TextInputLayout) findViewById(R.id.designationMaterielLayout);
        quantityLayout = (TextInputLayout) findViewById(R.id.quantityLayout);
        obsLayout = (TextInputLayout) findViewById(R.id.observationLayout);

        ajoutMateriel = (Button) findViewById(R.id.buttonmateriel1);

        ajoutMateriel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ajoutMateriel.this, Fiche_Execution.class);
                String dateMateriel = dateMaterielLayout.getEditText().getText().toString().trim();
                String designationMateriel = designationMaterielLayout.getEditText().getText().toString().trim();
                String quantitéMateriel = quantityLayout.getEditText().getText().toString().trim();
                String observationMateriel = obsLayout.getEditText().getText().toString().trim();
                if(!dateMateriel.equals("")
                        && !designationMateriel.equals("")
                        && !quantitéMateriel.equals("")
                        && !observationMateriel.equals("") ){
                    datesList.add(dateMateriel);
                    designationList.add(designationMateriel);
                    quantityList.add(quantitéMateriel);
                    obsList.add(observationMateriel);
                }
                if((!datesList.isEmpty()
                        && !designationList.isEmpty()
                        && !quantityList.isEmpty()
                        && !obsList.isEmpty()) &&
                        (datesList != null
                        && designationList!= null
                        && quantityList!= null
                        && obsList!= null)
                ) {
                    intent.putExtra("datesMaterielListe",datesList);
                    intent.putExtra("designationMaterielListe",designationList);
                    intent.putExtra("quantitéMaterielListe",quantityList);
                    intent.putExtra("observationListe",obsList);
                }
                transferElements(intent);
                startActivity(intent);
            }
        });
    }
    private void transferElements(Intent intent){
        intent.putExtra("FEListElements", FEElements);
        intent.putExtra("datesTravauxListe",datesListTravaux);
        intent.putExtra("designationTravauxListe",designationListTravaux);
        intent.putExtra("intervenantTravauxListe",intervenantListTravaux);
        intent.putExtra("tempsPasséListe",tempsPassedListTravaux);
    }
}