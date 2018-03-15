package com.elis.mvalier1.findme.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.elis.mvalier1.findme.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by mvalier1 on 09/02/2018.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter implements SectionIndexer, Filterable {


    private final ExpandableListView expandableListView;
    private Context _context;
    private ArrayList<String> _listDataHeader = null;
    private ArrayList<String> data;
    private HashMap<String, List<String>> _listDataChild;
    private List<String> noms;
    ImageView photoEmp;
    String nomPhoto;
    private Filter filter;

    private String mSections = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public ExpandableListAdapter(Context context, ExpandableListView expandableListView, ArrayList<String> listDataHeader, HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.expandableListView = expandableListView;
        this.data = listDataHeader;
    }


    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_child, null);
        }
        TextView txtListChild =  convertView.findViewById(R.id.lblListItem);
        photoEmp =  convertView.findViewById(R.id.imageButton);
        noms = new ArrayList<>();
        for (int i = 0; i < _listDataHeader.size(); i++) {
            nomPhoto = _listDataHeader.get(i)
                    .replaceAll(" ","_").toLowerCase()
                    .replaceAll("'","").toLowerCase()
                    .replaceAll("-","_").toLowerCase()
                    .replaceAll("é","e").toLowerCase();

            noms.add(nomPhoto);
        }
        for (int i = 0; i < _listDataHeader.size(); i++) {
            photoEmp.setImageResource(convertView.getResources().getIdentifier(noms.get(groupPosition),"drawable", this._context.getPackageName()));
        }
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_groupe, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }

    @Override
    public Object[] getSections() {
        String[] sections = new String[mSections.length()];
        for (int i = 0; i < mSections.length(); i++) {
            sections[i] = String.valueOf(mSections.charAt(i));
        }
        return sections;
    }

    @Override
    public int getPositionForSection(int section) {
        for (int i = section; i >= 0; i--) {
            for (int j = 0; j < getGroupCount(); j++) {
                String groupItem = String.valueOf(getGroup(j));
                if (i == 0) {
                    // For numeric section
                    for (int k = 0; k <= 9; k++) {
                        if (StringMatcher.match(String.valueOf(groupItem.charAt(0)), String.valueOf(k)))
                            return j;
                    }
                } else {
                    if (StringMatcher.match(String.valueOf(groupItem.charAt(0)), String.valueOf(mSections.charAt(i))))
                        return j;
                }
            }
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }


    @Override
    public Filter getFilter(){
        if (filter == null) {
            filter = new MyFilter();
        }
        return filter;
    }

    private class MyFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            String filterText = charSequence.toString().toLowerCase();
//            Log.i(TAG, "filterText "+filterText);
            if (filterText == null || filterText.length() == 0) {
                synchronized (this) {
                    filterResults.values = data;
                    filterResults.count = data.size();
                    Log.i(TAG, "filterResult if "+filterResults.values);
                }
            } else {
                ArrayList<String> filterList = new ArrayList<>();
                ArrayList<String> unFilterList = new ArrayList<>();
                synchronized (this) {
                    unFilterList.addAll(data);
                }
                for (int i = 0, l = unFilterList.size(); i < l; i++) {
                    String m = unFilterList.get(i);
                    if (m.toLowerCase().contains(filterText)) {
                        filterList.add(m);
                    }
                }
                filterResults.values = filterList;
                filterResults.count = filterList.size();
                Log.i(TAG, "filterResult else "+filterResults.values);
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            _listDataHeader = (ArrayList<String>) filterResults.values;
            if(filterResults.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }

        }
    }
}
