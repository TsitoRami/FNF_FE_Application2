package com.example.fnf_fe_application.FE_Class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.fnf_fe_application.Connect_After;
import com.example.fnf_fe_application.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class Fiche_Execution extends AppCompatActivity {

    private Button confirmerButtonFE, ajoutTravail, ajoutMateriel;
    private Button consulterMateriel, consulterTravaux;
    private Button annexe1Button, annexe2Button;

    private RadioButton aeroButton, sibButton, semButton;
    private RadioButton etudeButton, analyseButton, interventionButton;

    private ArrayList datesListTravaux, designationListTravaux, intervenantListTravaux, tempsPassedListTravaux;
    private ArrayList datesList, designationList, quantityList, obsList;

    private ArrayList FEElements;
    private String typeIntervention, natureIntervention;
    private TextInputLayout responsableInput, ficheFNFInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_execution);

        confirmerButtonFE = (Button) findViewById(R.id.buttonConfirmFE);
        ajoutTravail = (Button) findViewById(R.id.buttonAjoutTravail);
        ajoutMateriel = (Button) findViewById(R.id.buttonAjouterMateriel);

        consulterMateriel = (Button) findViewById(R.id.buttonConsulterMateriel);
        consulterTravaux = (Button) findViewById(R.id.buttonConsulterTravail);

        annexe1Button = (Button) findViewById(R.id.buttonAnnexe1);
        annexe2Button = (Button) findViewById(R.id.buttonAnnexe2);

        aeroButton = (RadioButton) findViewById(R.id.radioButtonIntervention1);
        sibButton = (RadioButton) findViewById(R.id.radioButtonIntervention2);
        semButton = (RadioButton) findViewById(R.id.radioButtonIntervention3);

        etudeButton = (RadioButton) findViewById(R.id.radioButtonNatureIntervention1);
        analyseButton = (RadioButton) findViewById(R.id.radioButtonNatureIntervention2);
        interventionButton = (RadioButton) findViewById(R.id.radioButtonNatureIntervention3);

        responsableInput = (TextInputLayout) findViewById(R.id.responsableInputLayout);
        ficheFNFInput = (TextInputLayout) findViewById(R.id.ficheFNFInputLayout);

        if (!this.getIntent().hasExtra("FEListElements")){
            FEElements = new ArrayList(Arrays.asList());
            aeroButton.setChecked(true);
            typeIntervention = "Aéro";
            etudeButton.setChecked(true);
            natureIntervention = "Étude";
        }
        else{
            FEElements = this.getIntent().getExtras().getStringArrayList("FEListElements");
        }
        if(!FEElements.isEmpty()) {
            responsableInput.getEditText().setText(FEElements.get(0).toString());
            ficheFNFInput.getEditText().setText(FEElements.get(1).toString());
            checkAutoTypeIntervention(FEElements.get(2).toString());
            checkAutoNatureIntervention(FEElements.get(3).toString());
        }
        checkUncheck(aeroButton, sibButton, semButton);
        checkUncheck(etudeButton, analyseButton, interventionButton);

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

        ajoutMateriel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fiche_Execution.this, ajoutMateriel.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        ajoutTravail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fiche_Execution.this, ajoutTravail.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        confirmerButtonFE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fiche_Execution.this, Connect_After.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        consulterMateriel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fiche_Execution.this, consulterMateriel.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        consulterTravaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fiche_Execution.this, consulterTravaux.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        annexe1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory() + "/";
                Uri uri = Uri.parse(path);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(uri, "*/*");
                startActivity(intent);
            }
        });

        annexe2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory() + "/";
                Uri uri = Uri.parse(path);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(uri, "*/*");
                startActivity(intent);
            }
        });
    }

    //Mettre les listeners pour avoir qu'un seul bouton check
    private void checkUncheck(RadioButton r1, RadioButton r2, RadioButton r3){
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r2.isChecked() || r3.isChecked()){
                    r2.setChecked(false);
                    r3.setChecked(false);
                }
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r1.isChecked() || r3.isChecked()){
                    r1.setChecked(false);
                    r3.setChecked(false);
                }
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r2.isChecked() || r1.isChecked()){
                    r2.setChecked(false);
                    r1.setChecked(false);
                }
            }
        });
    }
    private void defineStringTypeIntervention(){
        if(aeroButton.isChecked()){
            typeIntervention = "Aéro";
        }
        else if(sibButton.isChecked()){
            typeIntervention = "SIB";
        }
        else {
            typeIntervention = "SEM";
        }
    }
    private void defineStringNatureIntervention(){
        if(etudeButton.isChecked()){
            natureIntervention = "Étude";
        }
        else if(analyseButton.isChecked()){
            natureIntervention = "Analyse";
        }
        else{
            natureIntervention = "Intervention";
        }
    }
    private void transferElements(Intent intent){
        defineFEElements();
        intent.putExtra("FEListElements", FEElements);
        intent.putExtra("datesTravauxListe",datesListTravaux);
        intent.putExtra("designationTravauxListe",designationListTravaux);
        intent.putExtra("intervenantTravauxListe",intervenantListTravaux);
        intent.putExtra("tempsPasséListe",tempsPassedListTravaux);
        intent.putExtra("datesMaterielListe",datesList);
        intent.putExtra("designationMaterielListe",designationList);
        intent.putExtra("quantitéMaterielListe",quantityList);
        intent.putExtra("observationListe",obsList);
    }
    private void checkAutoTypeIntervention(String checked){
        if(checked.equals("Aéro")){
            aeroButton.setChecked(true);
        }
        else if (checked.equals("SIB")){
            sibButton.setChecked(true);
        }
        else {semButton.setChecked(true);}
        checkUncheck(aeroButton, sibButton, semButton);
    }
    private void checkAutoNatureIntervention(String checked){
        if(checked.equals("Étude")){
            etudeButton.setChecked(true);
        }
        else if (checked.equals("Analyse")){
            analyseButton.setChecked(true);
        }
        else {interventionButton.setChecked(true);}
        checkUncheck(etudeButton, analyseButton, interventionButton);
    }
    private void defineFEElements(){
        defineStringNatureIntervention();
        defineStringTypeIntervention();
        if(FEElements.isEmpty()
                && !responsableInput.equals("")
                && !ficheFNFInput.equals("")){
            FEElements.add(responsableInput.getEditText().getText().toString().trim());
            FEElements.add(ficheFNFInput.getEditText().getText().toString().trim());
            FEElements.add(typeIntervention);
            FEElements.add(natureIntervention);
        }
        replaceElement(FEElements, responsableInput.getEditText().getText().toString().trim(), 0);
        replaceElement(FEElements, ficheFNFInput.getEditText().getText().toString().trim(), 1);
        replaceElement(FEElements, typeIntervention, 2);
        replaceElement(FEElements, natureIntervention, 3);
    }
    private void replaceElement(ArrayList liste, String newElement, int elementPosition){
        if(!liste.get(elementPosition).equals(newElement)) {
            liste.set(elementPosition, newElement);
        }
    }

}