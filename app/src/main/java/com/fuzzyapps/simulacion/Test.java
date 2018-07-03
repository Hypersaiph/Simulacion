package com.fuzzyapps.simulacion;

import android.content.DialogInterface;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class Test extends AppCompatActivity {
    private ListView listView;
    public ArrayList<Double> random_;
    public ArrayList<String> random_str;
    public ArrayList<Integer> number;
    public ArrayList<String> number_str;
    LayoutInflater layoutInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        listView = (ListView) findViewById(R.id.listView);
        layoutInflater = getLayoutInflater();
        random_ =  new ArrayList<Double>();
        random_str = new ArrayList<String>();
        number = new ArrayList<Integer>();
        number_str = new ArrayList<String>();
        random_str.add("# Aleatorio");
        actualizarListview();
        Toast.makeText(this,"Zona incompleta.",Toast.LENGTH_SHORT).show();
        try{
            Bundle bundle = getIntent().getExtras();
            boolean fromGenerator = bundle.getBoolean("from_generator");
            if(fromGenerator){
                random_ = (ArrayList<Double>) getIntent().getSerializableExtra("x");
                actualizarListview();
            }
        }catch (Exception e){

        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAlertDialog(true, 0, "");
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //update
                if(position != 0) {
                    String item = listView.getItemAtPosition(position).toString();
                    displayAlertDialog(false, position, item);
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if(position != 0) {
                    String item = listView.getItemAtPosition(position).toString();
                    //delete
                    AlertDialog.Builder builder = new AlertDialog.Builder(Test.this);
                    builder.setTitle("Desea eliminar el # aleatorio, " + item + "?");
                    builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            random_.remove(position-1);
                            random_str.remove(position);
                            actualizarListview();
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
                return true;
            }
        });
    }
    public void actualizarListview(){
        number.clear();
        number_str.clear();
        number_str.add("#");
        for(int i=0; i<random_.size(); i++){
            number.add(i+1);
            number_str.add(""+(i+1));
        }
        listView.setAdapter(null);
        setAdaptertoListView();
        Log.e("update - numb", number_str.toString());
        Log.e("update - rand", random_str.toString());
    }
    private void setAdaptertoListView() {
        listView.setAdapter(null);
        listView.setAdapter(new Adapter_two(this, random_str,number_str));
    }
    private void displayAlertDialog(final boolean nuevo, final int position, final String item) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Test.this);
        View view= layoutInflater.inflate(R.layout.menu_random_number, null);
        final EditText input = (EditText)view.findViewById(R.id.input);
        String messgA = "";
        if(nuevo) {
            builder.setTitle("Ingresar el # aleatorio");
             messgA = "Agregar";
        }else{
            builder.setTitle("Desea actualizar el # aleatorio, "+item+"?");
            messgA = "Actualizar";
        }
        builder.setView(view);
        builder.setPositiveButton(messgA, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(!input.getText().toString().equals("")){
                    if(nuevo) {
                        //Toast.makeText(Test.this, input.getText().toString(),Toast.LENGTH_SHORT).show();
                        Double d = Double.parseDouble(input.getText().toString());
                        random_.add(d);
                        random_str.add(input.getText().toString());
                    }else{
                        /*Log.e("pos",""+(position-1));
                        Log.e("random_",""+random_.toString());
                        Log.e("random_s",""+random_.size());
                        Log.e("item",""+item);*/
                        random_.set((position-1), Double.parseDouble(input.getText().toString()));
                        random_str.set(position, input.getText().toString());
                    }
                    actualizarListview();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.cancel();
            }
        });
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_test, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ks:
                return true;
            case R.id.independencia:
                return true;
            case R.id.frecuencias:
                return true;
            case R.id.series:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
