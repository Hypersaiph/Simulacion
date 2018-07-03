package com.fuzzyapps.simulacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Geovani on 21/03/2017.
 */

public class Adapter_two extends BaseAdapter {

    ArrayList<String> data;
    ArrayList<String> number;
    Context context;
    private static LayoutInflater inflater = null;

    public Adapter_two(Context context, ArrayList<String> data, ArrayList<String> number) {
        // TODO Auto-generated constructor stub
        this.data = data;
        this.number = number;
        this.context = context;
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
            vi = inflater.inflate(R.layout.list_row_two, null);
        TextView a = (TextView) vi.findViewById(R.id.a);
        TextView b = (TextView) vi.findViewById(R.id.b);
        //String[] s = data.get(position).split("&&");
        a.setText(number.get(position));
        b.setText(data.get(position));

        return vi;
    }
}