package com.elis.mvalier1.findme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.elis.mvalier1.findme.R;
import com.elis.mvalier1.findme.controller.ExpandableListAdapter;
import com.elis.mvalier1.findme.model.EmployeSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * Created by mvalier1 on 01/02/2018.
 */


public class EmployeFragment extends Fragment {


    private String[] tokens;
    private EmployeSample sample = new EmployeSample();
    private EditText editText;
    private ExpandableListView expListView;
    private ExpandableListAdapter listAdapter;
    private ArrayList<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private String line;
    private int lastExpandedPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabemploye, container, false);

        editText = rootView.findViewById(R.id.txtsearch);
        expListView = rootView.findViewById(R.id.lvExp);

        prepareListData();


        listAdapter = new ExpandableListAdapter(getContext(), expListView, listDataHeader, listDataChild);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        expListView.setAdapter(listAdapter);
        expListView.setFastScrollEnabled(true);
        expListView.setFastScrollAlwaysVisible(true);
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition)
                    expListView.collapseGroup(lastExpandedPosition);
                lastExpandedPosition = groupPosition;
            }
        });


        listAdapter.notifyDataSetChanged();

        return rootView;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        int i = 0;
        int j = 0;

        InputStream is = getResources().openRawResource(R.raw.exportemploye);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("ISO-8859-1"))
        );

        try {
            while ((line = reader.readLine()) != null) {
                tokens = line.split(";");
                Log.d(TAG, "prepareListData: "+j++);

                sample.setAile(tokens[0]);
                sample.setEtage(tokens[1]);
                sample.setNom(tokens[2]);
                sample.setNumero(tokens[3]);
                sample.setPrenom(tokens[4]);
                sample.setTelephone(tokens[5]);
                sample.setService(tokens[6]);
                sample.setPays(tokens[12]);
                sample.setUsine(tokens[13]);
                sample.setN1manager(tokens[11]);
                sample.setN1job(tokens[10]);
                sample.setCoo(tokens[9]);
                sample.setCodepostal(tokens[8]);
                sample.setVille(tokens[14]);
                sample.setAdresse(tokens[7]);

                String nom = sample.getNom();
                String prenom = sample.getPrenom();
                String etage = sample.getEtage();
                String aile = sample.getAile();
                String telephone = sample.getTelephone();
                String bureau = sample.getNumero();
                String service = sample.getService();
                String adresse = sample.getAdresse();
                String code = sample.getCodepostal();
                String coo = sample.getCoo();
                String n1job = sample.getN1job();
                String n1manager = sample.getN1manager();
                String pays = sample.getPays();
                String usine = sample.getUsine();
                String ville = sample.getVille();

                listDataHeader.add(nom + " " + prenom);

                List<String> infos = new ArrayList<>();
                infos.add("Etage : " + etage +
                        "\nAile : " + aile +
                        "\nBureau n° : " + bureau +
                        "\nTéléphone : " + telephone +
                        "\nService : " + service +
                        "\nAdresse : " + adresse +
                        "\nCode : " + code +
                        "\nVille : " + ville +
                        "\nPays : " + pays +
                        "\nCOO : " + coo +
                        "\nN+1 : " + n1manager +
                        "\nN+1 Job : " + n1job +
                        "\nUsine : " + usine);

                listDataChild.put(listDataHeader.get(i++), infos);

                Collections.sort(listDataHeader);


            }

        } catch (IOException e) {
            Log.wtf("Employe", "Error reading data file on line " + line, e);
            e.printStackTrace();
        }

    }

}
