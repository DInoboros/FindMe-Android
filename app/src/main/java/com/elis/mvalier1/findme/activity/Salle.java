package com.elis.mvalier1.findme.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.elis.mvalier1.findme.R;

/**
 * Created by mvalier1 on 17/01/2018.
 */

public class Salle extends AppCompatActivity {

    String nom;
    String aile;
    String niveau;
    String cote;
    String emplacement;
    Bundle bundle;
    TextView textView;
    ImageView plan;
    ImageView salleAP;
    AlertDialog alertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salle);

        bundle = getIntent().getExtras();

        nom = bundle.getString("NomSalle");
        aile = bundle.getString("AileSalle");
        niveau = bundle.getString("NiveauSalle");
        cote = bundle.getString("CoteSalle");
        emplacement = bundle.getString("EmplacementSalle");

        Log.d("nom", nom);
        Log.d("aile", aile);
        Log.d("niveau", niveau);
        Log.d("cote", cote);
        Log.d("emplacement", emplacement);

        plan = (ImageView) findViewById(R.id.plan);
        if (nom.equals("Douro"))
            plan.setImageResource(getResources().getIdentifier("plan_douro", "drawable", getApplicationContext().getPackageName()));
        else if (nom.equals("Prado"))
            plan.setImageResource(getResources().getIdentifier("plan_prado", "drawable", getApplicationContext().getPackageName()));
        else if (niveau.equals("rdj"))
            plan.setImageResource(getResources().getIdentifier("plan_etage_" + niveau + "_aile_" + aile, "drawable", getApplicationContext().getPackageName()));
        else
            plan.setImageResource(getResources().getIdentifier("plan_etage_" + niveau + "_aile_" + aile + "_" + cote + "_" + emplacement, "drawable", getApplicationContext().getPackageName()));

        textView = (TextView) findViewById(R.id.nomSalle);
        textView.setText(nom);

        salleAP = (ImageView) findViewById(R.id.salleAP);
        salleAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog = new AlertDialog.Builder(Salle.this).create();
                alertDialog.setTitle("A propos de cette salle");
                alertDialog.setIcon(R.drawable.logo_a_propos);

                if (emplacement.equals("milieu"))
                    alertDialog.setMessage("Aile : " + aile.toUpperCase() +
                            "\nEtage : " + niveau.toUpperCase() +/*
                                    "\nEtage : "+niveau+
                                    "\nEtage : "+niveau+
                                    "\nEtage : "+niveau+
                                    "\nEtage : "+niveau*/
                            "\nLa salle se trouve sur votre " + cote + " vers le " + emplacement + " de l'aile");
                else if (emplacement.equals("droite") || emplacement.equals("gauche"))
                    alertDialog.setMessage("Aile : " + aile.toUpperCase() +
                            "\nEtage : " + niveau.toUpperCase() +/*
                                    "\nEtage : "+niveau+
                                    "\nEtage : "+niveau+
                                    "\nEtage : "+niveau+
                                    "\nEtage : "+niveau*/
                            "\nLa salle se trouve sur votre " + cote + " sur la " + emplacement + " de l'aile");
                else if (nom.equals("Douro"))
                    alertDialog.setMessage(/*"Aile : " + aile.toUpperCase() +*/
                            "Etage : " + niveau.toUpperCase() +/*
                                    "\nEtage : "+niveau+
                                    "\nEtage : "+niveau+
                                    "\nEtage : "+niveau+
                                    "\nEtage : "+niveau*/
                            "\nLa salle se trouve sur votre " + emplacement);
                else
                    alertDialog.setMessage("Aile : " + aile.toUpperCase() +
                            "\nEtage : " + niveau.toUpperCase()/*+
                                    "\nEtage : "+niveau+
                                    "\nEtage : "+niveau+
                                    "\nEtage : "+niveau+
                                    "\nEtage : "+niveau*/);

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.apropos:
                Intent intent = new Intent(Salle.this, Apropos.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
