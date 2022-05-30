package com.example.fnf_fe_application.FNF_Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.fnf_fe_application.R;

import java.util.ArrayList;
import java.util.Arrays;

public class consulterPersonnes extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button retourButton;

    private ArrayList etape1Elements, etape2Elements, etape3Elements, etape4Elements;

    private ArrayList<String> personnesNom, organisationsNom;
    private ArrayList enginsImpliqués, typeEngins, immatriculationEngins;

    private boolean vide = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter_personnes);

        if(this.getIntent().hasExtra("nomListe") && this.getIntent().hasExtra("organisationListe")){
            personnesNom = this.getIntent().getExtras().getStringArrayList("nomListe");
            organisationsNom = this.getIntent().getExtras().getStringArrayList("organisationListe");
            vide = false;
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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPersonnes);
        retourButton = (Button) findViewById(R.id.buttonRetourLsPers);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        //  call the constructor of CustomAdapterPersonnes to send the reference and data to Adapter
        CustomAdapterPersonnes customAdapter = new CustomAdapterPersonnes(consulterPersonnes.this, personnesNom, organisationsNom);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(consulterPersonnes.this, Etape_3.class);
                if(!vide) {
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