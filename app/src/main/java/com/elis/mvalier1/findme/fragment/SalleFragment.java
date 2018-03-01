package com.elis.mvalier1.findme.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.elis.mvalier1.findme.db.DBHandler;
import com.elis.mvalier1.findme.R;
import com.elis.mvalier1.findme.activity.Salle;

/**
 * Created by mvalier1 on 01/02/2018.
 */

public class SalleFragment extends Fragment{

    ListView listView;
    EditText editText;
    Cursor cursor;
    private DBHandler db;
    private SimpleCursorAdapter dataAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabsalle, container, false);

        db = new DBHandler(getActivity());
        db.open();
        db.deleteAllSalles();
        db.insertSalles();

        cursor = db.fetchAllSalles();

        String[] columns = new String[]{
                DBHandler.NOM

        };

        int[] to = new int[]{
                R.id.txtitem,
        };

        dataAdapter = new SimpleCursorAdapter(
                getActivity(), R.layout.list_item, cursor, columns, to, 0
        );

        listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(dataAdapter);
        listView.setTextFilterEnabled(true);


        editText = (EditText) rootView.findViewById(R.id.txtsearch);
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                dataAdapter.getFilter().filter(s.toString());
                db.fetchAllSalles();
            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {

            @Override
            public Cursor runQuery(CharSequence constraint) {
                return cursor = db.fetchSallesByName(constraint.toString());

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), Salle.class);
                intent.putExtra("NomSalle", cursor.getString(cursor.getColumnIndex("nom")));
                intent.putExtra("AileSalle", cursor.getString(cursor.getColumnIndex("aile")));
                intent.putExtra("NiveauSalle", cursor.getString(cursor.getColumnIndex("niveau")));
                intent.putExtra("CoteSalle", cursor.getString(cursor.getColumnIndex("cote")));
                intent.putExtra("EmplacementSalle", cursor.getString(cursor.getColumnIndex("emplacement")));
                startActivity(intent);
            }
        });

        return rootView;
    }

}
