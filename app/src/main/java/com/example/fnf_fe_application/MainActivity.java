package com.example.fnf_fe_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fnf_fe_application.FE_Class.Fiche_Execution;
import com.example.fnf_fe_application.FNF_Class.Etape_1;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button fnfbutton;
    private Button febutton;
    private Button connectButton;

    private TextView messageAccueil;

    private LinearLayout layoutText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String texte = this.getIntent().getStringExtra("textPrincipal");
        int code = this.getIntent().getIntExtra("code", 0);

        fnfbutton = (Button) findViewById(R.id.buttonFNF);
        febutton = (Button) findViewById(R.id.buttonFE);
        connectButton = (Button) findViewById(R.id.buttonConnect);
        messageAccueil = (TextView) findViewById(R.id.textPrincipal);
        layoutText = (LinearLayout) findViewById(R.id.linearLayout3);

        setTextAndColor(messageAccueil, texte, code, layoutText);

        //Les clickListeners
        fnfbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Etape_1.class);
                startActivity(intent);
            }
        });

        febutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Fiche_Execution.class);
                startActivity(intent);
            }
        });

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, loginPage.class);
                intent.putExtra("logBefore", true);
                startActivity(intent);
            }
        });

    }
    private void setTextAndColor(TextView textView, String texte, int code, LinearLayout layout) {
        if(code == 1){
            textView.setText(texte);
            layout.setBackgroundColor(Color.argb(255, 255, 0, 0));
        }
        else if (code == 2){
            textView.setText(texte);
            layout.setBackgroundColor(Color.argb(255, 0, 255, 0));
        }
    }
}