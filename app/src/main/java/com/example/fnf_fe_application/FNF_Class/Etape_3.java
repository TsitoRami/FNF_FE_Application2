package com.example.fnf_fe_application.FNF_Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.example.fnf_fe_application.R;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Etape_3 extends AppCompatActivity {

    private static final int REQUEST_PICK_FILE = 1;

    private Context mContext;

    private Button previousButton, nextButton, helpButton, annexeButton;
    private Button ajoutPersonnes, ajoutEngins, consulterPersonnes, consulterEngins;

    private PopupWindow mPopupWindow;

    private ConstraintLayout layoutStep3;

    private AutoCompleteTextView eventype, classe, impact;

    private TextInputLayout eventypeInput, classeInput, impactInput, causesInput, descriptionInput;

    private ArrayList etape1Elements, etape2Elements, etape3Elements, etape4Elements;

    private ArrayList<String> personnesNom, organisationsNom;
    private ArrayList enginsImpliqués, typeEngins, immatriculationEngins;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape3);

        previousButton = (Button) findViewById(R.id.buttonPrevious3);
        nextButton = (Button) findViewById(R.id.buttonNext3);
        helpButton = (Button) findViewById(R.id.buttonHelp);
        annexeButton = (Button) findViewById(R.id.buttonAnnexe);
        ajoutPersonnes = (Button) findViewById(R.id.ajoutPersonne);
        ajoutEngins = (Button) findViewById(R.id.ajoutEngin);
        consulterPersonnes = (Button) findViewById(R.id.voirPersonnes);
        consulterEngins = (Button) findViewById(R.id.voirEngins);

        eventype = (AutoCompleteTextView) findViewById(R.id.eventypeInput);
        classe = (AutoCompleteTextView) findViewById(R.id.classInput);
        impact = (AutoCompleteTextView) findViewById(R.id.impactInput);

        eventypeInput = (TextInputLayout) findViewById(R.id.eventypeInputLayout);
        classeInput = (TextInputLayout) findViewById(R.id.classInputLayout);
        impactInput = (TextInputLayout) findViewById(R.id.impactInputLayout);
        causesInput = (TextInputLayout) findViewById(R.id.causeInputLayout);
        descriptionInput = (TextInputLayout) findViewById(R.id.descriptionTypeInputLayout);

        mContext = getApplicationContext();

        layoutStep3 = (ConstraintLayout) findViewById(R.id.step3Layout);

        //Définition des éléments du FNF sauvegardés
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

        if(etape3Elements == null) {
            etape3Elements = new ArrayList(Arrays.asList());
        }
        if(!etape3Elements.isEmpty()) {
            eventypeInput.getEditText().setText(etape3Elements.get(0).toString());
            classeInput.getEditText().setText(etape3Elements.get(1).toString());
            impactInput.getEditText().setText(etape3Elements.get(2).toString());
            causesInput.getEditText().setText(etape3Elements.get(3).toString());
            descriptionInput.getEditText().setText(etape3Elements.get(4).toString());
        }

        //Définition des listes pour l'autocompletion
        ArrayAdapter<String> eventTypes = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, EVENTS);
        ArrayAdapter<String> classes = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, CLASSES);
        ArrayAdapter<String> impacts = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, IMPACTS);

        eventype.setAdapter(eventTypes);
        classe.setAdapter(classes);
        impact.setAdapter(impacts);

        //Suggestion automatique
        eventype.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                eventype.showDropDown();
                return false;
            }
        });
        classe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                classe.showDropDown();
                return false;
            }
        });
        impact.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                impact.showDropDown();
                return false;
            }
        });


        //Les listeners des boutons
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etape_3.this, Etape_2.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etape_3.this, Etape_4.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        //Popup help description
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                @SuppressLint("InflateParams") View customView = inflater.inflate(R.layout.description,null);
                mPopupWindow = new PopupWindow(
                        customView,
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );

                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                    }
                });

                mPopupWindow.showAtLocation(layoutStep3, Gravity.CENTER,0,0);
            }
        });

        //Listener des boutons d'ajout et de consultation des personnes et engins impliqués
        ajoutPersonnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etape_3.this, ajoutPersonnes.class);
                transferElements(intent);
                startActivity(intent);
            }
        });
        ajoutEngins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etape_3.this, ajoutEngins.class);
                transferElements(intent);
                startActivity(intent);
            }
        });
        consulterPersonnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etape_3.this, consulterPersonnes.class);
                transferElements(intent);
                startActivity(intent);
            }
        });
        consulterEngins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Etape_3.this, consulterEngins.class);
                transferElements(intent);
                startActivity(intent);
            }
        });

        //Annexe Button
        annexeButton.setOnClickListener(new View.OnClickListener() {
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
    private static final String[] EVENTS = new String[] {
            "Balisage lumineux, marques et panneaux",
            "Collision",
            "Clôture",
            "Dégradation de la chaussée",
            "Déversement",
            "Encombrement de l'aire de mouvement",
            "Etat de la surface",
            "Incursion",
            "Péril annimalier",
            "Présence d'objet (FOD)",
            "Problèmes liés aux conditions météo",
            "Problèmes liés ax opérations handling"
    };
    private static final String[] CLASSES = new String[] {
            "Condition latente",
            "Incident",
            "Accident : Lésions corporelles",
            "Accident : Dommages matériels",
            "Impact : IRGHO",
            "Impact : IRGAV",
            "Impact : IRGIT"
    };
    private static final String[] IMPACTS = new String[] {
            "Environnement",
            "Sécurité",
            "Sûreté"
    };

    private void replaceElement(ArrayList liste, String newElement, int elementPosition){
        if(!liste.get(elementPosition).equals(newElement)) {
            liste.set(elementPosition, newElement);
        }
    }

    private void transferElements (Intent intent){
        if(etape3Elements.isEmpty() && !eventypeInput.equals("") && !classeInput.equals("") && !impactInput.equals("") && !causesInput.equals("") && !descriptionInput.equals("")) {
            etape3Elements.add(eventypeInput.getEditText().getText().toString().trim());
            etape3Elements.add(classeInput.getEditText().getText().toString().trim());
            etape3Elements.add(impactInput.getEditText().getText().toString().trim());
            etape3Elements.add(causesInput.getEditText().getText().toString().trim());
            etape3Elements.add(descriptionInput.getEditText().getText().toString().trim());
        }
        replaceElement(etape3Elements, eventypeInput.getEditText().getText().toString().trim(), 0);
        replaceElement(etape3Elements, classeInput.getEditText().getText().toString().trim(), 1);
        replaceElement(etape3Elements, impactInput.getEditText().getText().toString().trim(), 2);
        replaceElement(etape3Elements, causesInput.getEditText().getText().toString().trim(), 3);
        replaceElement(etape3Elements, descriptionInput.getEditText().getText().toString().trim(), 4);
        intent.putExtra("etape1List", etape1Elements);
        intent.putExtra("etape2List", etape2Elements);
        intent.putExtra("etape3List", etape3Elements);
        intent.putExtra("etape4List", etape4Elements);
        intent.putExtra("nomListe", personnesNom);
        intent.putExtra("organisationListe", organisationsNom);
        intent.putExtra("enginsListe",enginsImpliqués);
        intent.putExtra("typeEnginsListe",typeEngins);
        intent.putExtra("immatriculationList",immatriculationEngins);
    }

}