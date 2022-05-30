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

public class Etape_2 extends AppCompatActivity {

    private Button previousButton, nextButton;
    private TextInputLayout dateInput, heureInput, planInput, lieuInput;

    private ArrayList etape1Elements, etape2Elements, etape3Elements, etape4Elements;

    private ArrayList<String> personnesNom, organisationsNom;
    private ArrayList enginsImpliqués, typeEngins, immatriculationEngins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape2);

        previousButton = (Button) findViewById(R.id.buttonPrevious);
        nextButton = (Button) findViewById(R.id.buttonNext2);

        dateInput = (TextInputLayout) findViewById(R.id.date2InputLayout);
        heureInput = (TextInputLayout) findViewById(R.id.heureInputLayout);
        planInput = (TextInputLayout) findViewById(R.id.planInputLayout);
        lieuInput = (TextInputLayout) findViewById(R.id.lieuExactInputLayout);

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

        if(etape2Elements == null) {
            etape2Elements = new ArrayList(Arrays.asList());
        }
        if(!etape2Elements.isEmpty()) {
            dateInput.getEditText().setText(etape2Elements.get(0).toString());
            heureInput.getEditText().setText(etape2Elements.get(1).toString());
            planInput.getEditText().setText(etape2Elements.get(2).toString());
            lieuInput.getEditText().setText(etape2Elements.get(3).toString());
        }

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etape_2.this, Etape_1.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etape_2.this, Etape_3.class);
                if(etape2Elements.isEmpty() && !dateInput.equals("") && !heureInput.equals("") && !planInput.equals("") && !lieuInput.equals("")) {
                    etape2Elements.add(dateInput.getEditText().getText().toString().trim());
                    etape2Elements.add(heureInput.getEditText().getText().toString().trim());
                    etape2Elements.add(planInput.getEditText().getText().toString().trim());
                    etape2Elements.add(lieuInput.getEditText().getText().toString().trim());
                }
                replaceElement(etape2Elements, dateInput.getEditText().getText().toString().trim(), 0);
                replaceElement(etape2Elements, heureInput.getEditText().getText().toString().trim(), 1);
                replaceElement(etape2Elements, planInput.getEditText().getText().toString().trim(), 2);
                replaceElement(etape2Elements, lieuInput.getEditText().getText().toString().trim(), 3);
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