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
                "Permet la localisation de salle de réunion et du personnel au sein du siège de Saint Cloud.\n" +
                "\n" +
                "Version 1.0. 15/03/2018\n" +
                "\n" +
                "Pour toute demande d'ajout ou de modification, merci de contacter :\n" +
                "\n" +
                " morgan.bochet@elis.com, david.barres@elis.com \n" +
                "ou meryl.valier@elis.com. ");
    }
}
