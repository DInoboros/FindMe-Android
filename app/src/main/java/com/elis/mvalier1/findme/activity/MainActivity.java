package com.elis.mvalier1.findme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elis.mvalier1.findme.R;
import com.elis.mvalier1.findme.controller.Session;

public class MainActivity extends AppCompatActivity {

    EditText login;
    EditText password;
    Button valider;
    int backpress = 0;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);

        session = new Session(getApplicationContext());

        session.setUsename("elis");
        session.setPassword("elis");

        valider = (Button) findViewById(R.id.valider);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (login.getText().toString().equals(session.getUsename())) {
                    if (password.getText().toString().equals(session.getPassword())) {
                        Intent intent = new Intent(MainActivity.this, Recherche.class);
                        startActivity(intent);
                    } else {
                        password.setText("");
                        Toast.makeText(getApplicationContext(), "Mauvais mot de passe !", Toast.LENGTH_LONG).show();
                    }
                } else {
                    login.setText("");
                    password.setText("");
                    Toast.makeText(getApplicationContext(), "Mauvais login et/ou mot passe !", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Le user et le mot de passe est : elis", Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    public void onBackPressed() {
        if (backpress == 0) {
            Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();
            backpress += 1;
        } else {
            moveTaskToBack(true);
            backpress = 0;
        }
    }
}
