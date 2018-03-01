package com.elis.mvalier1.findme.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.elis.mvalier1.findme.R;

/**
 * Created by mvalier1 on 17/01/2018.
 */

public class Apropos extends AppCompatActivity {

    TextView apropos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apropos);

        apropos = findViewById(R.id.apropos);
        apropos.setText("Application développée par la DTSI.\n" +
                "\n" +
                "Permet la recherche de salle de réunion et d'informations sur le personnel au sein du siège de Saint Cloud.\n" +
                "\n" +
                "Version bêta.\n" +
                "\n" +
                "Pour toute demande d'ajout ou de modification, merci de contacter :\n" +
                "\n" +
                " meryl.valier@elis.com ou morgan.bochet@elis.com. ");
    }
}
