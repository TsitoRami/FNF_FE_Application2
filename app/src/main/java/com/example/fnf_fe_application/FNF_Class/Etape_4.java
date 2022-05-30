package com.example.fnf_fe_application.FNF_Class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.fnf_fe_application.R;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class Etape_4 extends AppCompatActivity {

    private Button previousButton, nextButton;

    private TextInputLayout descriptionInput, responsableInput, dateInput;

    private ArrayList etape1Elements, etape2Elements, etape3Elements, etape4Elements;

    private ArrayList<String> personnesNom, organisationsNom;
    private ArrayList enginsImpliqués, typeEngins, immatriculationEngins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape4);

        previousButton = (Button) findViewById(R.id.buttonPrevious4);
        nextButton = (Button) findViewById(R.id.buttonNext4);

        descriptionInput = (TextInputLayout) findViewById(R.id.description4InputLayout);
        responsableInput = (TextInputLayout) findViewById(R.id.responsableInputLayout);
        dateInput = (TextInputLayout) findViewById(R.id.date4InputLayout);

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

        if(etape4Elements == null) {
            etape4Elements = new ArrayList(Arrays.asList());
        }
        if(!etape4Elements.isEmpty()) {
            descriptionInput.getEditText().setText(etape4Elements.get(0).toString());
            responsableInput.getEditText().setText(etape4Elements.get(1).toString());
            dateInput.getEditText().setText(etape4Elements.get(2).toString());
        }

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etape_4.this, Etape_3.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etape_4.this, FNF_Confirmation.class);
                if(etape4Elements.isEmpty() && !dateInput.equals("") && !descriptionInput.equals("") && !responsableInput.equals("")) {
                    etape4Elements.add(descriptionInput.getEditText().getText().toString().trim());
                    etape4Elements.add(responsableInput.getEditText().getText().toString().trim());
                    etape4Elements.add(dateInput.getEditText().getText().toString().trim());
                }
                replaceElement(etape2Elements, descriptionInput.getEditText().getText().toString().trim(), 0);
                replaceElement(etape2Elements, responsableInput.getEditText().getText().toString().trim(), 1);
                replaceElement(etape2Elements, dateInput.getEditText().getText().toString().trim(), 2);
                transferElements(intent);
                startActivity(intent);
            }
        });
    }
    private void replaceElement(ArrayList liste, String newElement, int elementPosition){
        if(!liste.get(elementPosition).equals(newElement)) {
            liste.set(elementPosition, newElement);
        }
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