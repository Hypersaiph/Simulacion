package com.fuzzyapps.simulacion;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**

 */
public class Adapter extends BaseAdapter {

    Context context;
    ArrayList<String> data;
    private static LayoutInflater inflater = null;

    public Adapter(Context context, ArrayList<String> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.list_row, null);
        TextView a = (TextView) vi.findViewById(R.id.a);
        TextView b = (TextView) vi.findViewById(R.id.b);
        TextView c = (TextView) vi.findViewById(R.id.c);
        TextView d = (TextView) vi.findViewById(R.id.d);
        TextView e = (TextView) vi.findViewById(R.id.e);
        String[] s = data.get(position).split("&&");
        Log.e("s", data.get(position));
        if (position == 0) {
            a.setTypeface(null, Typeface.BOLD);
            b.setTypeface(null, Typeface.BOLD);
            c.setTypeface(null, Typeface.BOLD);
            d.setTypeface(null, Typeface.BOLD);
            e.setTypeface(null, Typeface.BOLD);
        }else{
            a.setTypeface(null);
            b.setTypeface(null);
            c.setTypeface(null);
            d.setTypeface(null);
            e.setTypeface(null);
        }
        //Log.e("array", data.get(position));
        if(s[5].equals("T")){
            a.setText(s[0]);a.setTypeface(null, Typeface.BOLD);
            b.setText(s[1]);b.setTypeface(null, Typeface.BOLD);
            c.setText(s[2]);c.setTypeface(null, Typeface.BOLD);
            d.setText(s[3]);d.setTypeface(null, Typeface.BOLD);
            e.setText(s[4]);e.setTypeface(null, Typeface.BOLD);
        }else{
            a.setText(s[0]);
            b.setText(s[1]);
            c.setText(s[2]);
            d.setText(s[3]);
            e.setText(s[4]);
            a.setTypeface(null);
            b.setTypeface(null);
            c.setTypeface(null);
            d.setTypeface(null);
            e.setTypeface(null);
        }
        if(s[5].equals("gone")){
            a.setText(s[0]);
            a.setVisibility(View.VISIBLE);
            b.setVisibility(View.GONE);
            c.setVisibility(View.GONE);
            d.setVisibility(View.GONE);
            e.setVisibility(View.GONE);
        }else{
            a.setVisibility(View.VISIBLE);
            b.setVisibility(View.VISIBLE);
            c.setVisibility(View.VISIBLE);
            d.setVisibility(View.VISIBLE);
            e.setVisibility(View.VISIBLE);
        }
        return vi;
    }
}