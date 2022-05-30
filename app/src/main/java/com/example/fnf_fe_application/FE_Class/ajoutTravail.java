package com.example.fnf_fe_application.FE_Class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fnf_fe_application.FNF_Class.Etape_3;
import com.example.fnf_fe_application.FNF_Class.ajoutPersonnes;
import com.example.fnf_fe_application.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class ajoutTravail extends AppCompatActivity {

    private Button ajouterTravail;
    private ArrayList datesListTravaux, designationListTravaux, intervenantListTravaux, tempsPassedListTravaux;

    private TextInputLayout dateTravauxLayout, designationtravauxLayout, intervenantTravauxLayout, tempsTravauxLayout;

    private ArrayList datesList, designationList, quantityList, obsList;

    private ArrayList FEElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_travail);

        ajouterTravail = (Button) findViewById(R.id.buttonTravail1);

        dateTravauxLayout = (TextInputLayout) findViewById(R.id.dateTravauxLayout);
        designationtravauxLayout = (TextInputLayout) findViewById(R.id.designationTravauxLayout);
        intervenantTravauxLayout = (TextInputLayout) findViewById(R.id.intervenantTravauxLayout);
        tempsTravauxLayout = (TextInputLayout) findViewById(R.id.tempsTravauxLayout);

        FEElements = this.getIntent().getExtras().getStringArrayList("FEListElements");
        if(!this.getIntent().hasExtra("datesMaterielListe")
                && !this.getIntent().hasExtra("designationMaterielListe")
                && !this.getIntent().hasExtra("quantitéMaterielListe")
                && !this.getIntent().hasExtra("observationListe")){
            datesList = new ArrayList<String>(Arrays.asList());
            designationList  = new ArrayList<String>(Arrays.asList());
            quantityList = new ArrayList<String>(Arrays.asList());
            obsList = new ArrayList<String>(Arrays.asList());
        }
        else {
            datesList = this.getIntent().getExtras().getStringArrayList("datesMaterielListe");
            designationList = this.getIntent().getExtras().getStringArrayList("designationMaterielListe");
            quantityList = this.getIntent().getExtras().getStringArrayList("quantitéMaterielListe");
            obsList = this.getIntent().getExtras().getStringArrayList("observationListe");
        }
        if(this.getIntent().hasExtra("datesTravauxListe")
                && this.getIntent().hasExtra("designationTravauxListe")
                && this.getIntent().hasExtra("intervenantTravauxListe")
                && this.getIntent().hasExtra("tempsPasséListe")) {
            datesListTravaux = this.getIntent().getExtras().getStringArrayList("datesTravauxListe");
            designationListTravaux = this.getIntent().getExtras().getStringArrayList("designationTravauxListe");
            intervenantListTravaux = this.getIntent().getExtras().getStringArrayList("intervenantTravauxListe");
            tempsPassedListTravaux = this.getIntent().getExtras().getStringArrayList("tempsPasséListe");
        }

        ajouterTravail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ajoutTravail.this, Fiche_Execution.class);
                String dateTravail = dateTravauxLayout.getEditText().getText().toString().trim();
                String designationTravail = designationtravauxLayout.getEditText().getText().toString().trim();
                String intervenantTravail = intervenantTravauxLayout.getEditText().getText().toString().trim();
                String tempsTravail = tempsTravauxLayout.getEditText().getText().toString().trim();
                if(!dateTravail.equals("")
                        && !designationTravail.equals("")
                        && !intervenantTravail.equals("")
                        && !tempsTravail.equals("")) {
                    datesListTravaux.add(dateTravail);
                    designationListTravaux.add(designationTravail);
                    intervenantListTravaux.add(intervenantTravail);
                    tempsPassedListTravaux.add(tempsTravail);
                }
                if((!datesListTravaux.isEmpty()
                        && !designationListTravaux.isEmpty()
                        && !intervenantListTravaux.isEmpty()
                        && !tempsPassedListTravaux.isEmpty()) &&
                        (dateTravauxLayout != null
                                && designationtravauxLayout != null
                                && intervenantTravauxLayout != null
                                && tempsTravauxLayout != null)
                        ){
                    intent.putExtra("datesTravauxListe",datesListTravaux);
                    intent.putExtra("designationTravauxListe",designationListTravaux);
                    intent.putExtra("intervenantTravauxListe",intervenantListTravaux);
                    intent.putExtra("tempsPasséListe",tempsPassedListTravaux);
                }
                transferElements(intent);
                startActivity(intent);
            }
        });
    }
    private void transferElements(Intent intent){
        intent.putExtra("FEListElements", FEElements);
        intent.putExtra("datesMaterielListe",datesList);
        intent.putExtra("designationMaterielListe",designationList);
        intent.putExtra("quantitéMaterielListe",quantityList);
        intent.putExtra("observationListe",obsList);
    }
}