package com.example.fnf_fe_application.FE_Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fnf_fe_application.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class consulterTravaux extends AppCompatActivity {

    private ArrayList datesListTravaux, designationListTravaux, intervenantListTravaux, tempsPassedListTravaux;
    private RecyclerView recyclerView;
    private Button retourButton;

    private boolean vide = true;

    private ArrayList datesList, designationList, quantityList, obsList;

    private ArrayList FEElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter_travaux);

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
            vide = false;
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewTravaux);
        retourButton = (Button) findViewById(R.id.buttonRetourLsTrav);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        //  call the constructor of CustomAdapterMateriel to send the reference and data to Adapter
        CustomAdapterTravaux customAdapter = new CustomAdapterTravaux(consulterTravaux.this, datesListTravaux, designationListTravaux, intervenantListTravaux, tempsPassedListTravaux);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(consulterTravaux.this, Fiche_Execution.class);
                if(!vide) {
                    intent.putExtra("datesTravauxListe", datesListTravaux);
                    intent.putExtra("designationTravauxListe", designationListTravaux);
                    intent.putExtra("intervenantTravauxListe", intervenantListTravaux);
                    intent.putExtra("tempsPasséListe", tempsPassedListTravaux);
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