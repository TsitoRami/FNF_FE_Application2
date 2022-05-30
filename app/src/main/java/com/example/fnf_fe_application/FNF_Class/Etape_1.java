package com.example.fnf_fe_application.FNF_Class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.fnf_fe_application.MainActivity;
import com.example.fnf_fe_application.R;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class Etape_1 extends AppCompatActivity {

    private TextInputLayout nomInput, fonctionInput, serviceInput, telInput, mailInput;
    private Button next, annuler;

    private ArrayList etape1Elements, etape2Elements, etape3Elements, etape4Elements;

    private ArrayList<String> personnesNom, organisationsNom;
    private ArrayList enginsImpliqués, typeEngins, immatriculationEngins;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape1);


        nomInput = (TextInputLayout) findViewById(R.id.planInputLayout);
        fonctionInput = (TextInputLayout) findViewById(R.id.fonctionInputlayout);
        serviceInput = (TextInputLayout) findViewById(R.id.serviceInputLayout);
        telInput = (TextInputLayout) findViewById(R.id.telInputLayout);
        mailInput = (TextInputLayout) findViewById(R.id.mailInputLayout);

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

        if(!this.getIntent().hasExtra("etape1List")){
            etape1Elements = new ArrayList(Arrays.asList());
            etape2Elements = new ArrayList(Arrays.asList());
            etape3Elements = new ArrayList(Arrays.asList());
            etape4Elements = new ArrayList(Arrays.asList());
        }
        else {
            etape1Elements = this.getIntent().getExtras().getStringArrayList("etape1List");
            etape2Elements = this.getIntent().getExtras().getStringArrayList("etape2List");
            etape3Elements = this.getIntent().getExtras().getStringArrayList("etape3List");
            etape4Elements = this.getIntent().getExtras().getStringArrayList("etape4List");
        }

        if(!etape1Elements.isEmpty()) {
            nomInput.getEditText().setText(etape1Elements.get(0).toString());
            fonctionInput.getEditText().setText(etape1Elements.get(1).toString());
            serviceInput.getEditText().setText(etape1Elements.get(2).toString());
            telInput.getEditText().setText(etape1Elements.get(3).toString());
            mailInput.getEditText().setText(etape1Elements.get(4).toString());
        }

        next = (Button) findViewById(R.id.nextButton1);
        annuler = (Button) findViewById(R.id.annulerButton1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etape_1.this, Etape_2.class);
                if(etape1Elements.isEmpty() && !nomInput.equals("") && !fonctionInput.equals("") && !serviceInput.equals("") && !telInput.equals("") && !mailInput.equals("")) {
                    etape1Elements.add(nomInput.getEditText().getText().toString().trim());
                    etape1Elements.add(fonctionInput.getEditText().getText().toString().trim());
                    etape1Elements.add(serviceInput.getEditText().getText().toString().trim());
                    etape1Elements.add(telInput.getEditText().getText().toString().trim());
                    etape1Elements.add(mailInput.getEditText().getText().toString().trim());
                }
                replaceElement(etape1Elements, nomInput.getEditText().getText().toString().trim(), 0);
                replaceElement(etape1Elements, fonctionInput.getEditText().getText().toString().trim(), 1);
                replaceElement(etape1Elements, serviceInput.getEditText().getText().toString().trim(), 2);
                replaceElement(etape1Elements, telInput.getEditText().getText().toString().trim(), 3);
                replaceElement(etape1Elements, mailInput.getEditText().getText().toString().trim(), 4);
                transferElements(intent);
                startActivity(intent);
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etape_1.this, MainActivity.class);
                intent.putExtra("textPrincipal", "Formulaire annulé !\n" +
                        "Vous pouvez choisir d’en faire un autre");
                intent.putExtra("code", 1);
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