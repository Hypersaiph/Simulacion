package com.fuzzyapps.simulacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RandomGenerationResult extends AppCompatActivity {
    private Button atras;
    private ListView listView;
    final ArrayList<String> lista = new ArrayList<String>();
    final ArrayList<Integer> Xn = new ArrayList<>();
    final ArrayList<Double> x = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_generation_result);
        atras=(Button)findViewById(R.id.button2);
        Bundle bundle = getIntent().getExtras();
        String a_=bundle.getString("a");
        String c_=bundle.getString("c");
        String x0_=bundle.getString("x0");
        String m_=bundle.getString("m");
        String formato=bundle.getString("formato");
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
        listView = (ListView) findViewById(R.id.listView);
        lista.add("n&&Xn&&(a*Xø+c)/m&&Xn+1&&#NDU&&T");//(a*Xø+c)/m
        int a=Integer.parseInt(a_);
        int c=Integer.parseInt(c_);
        int x0=Integer.parseInt(x0_);Xn.add(x0);
        int m=Integer.parseInt(m_);
        int entero;
        int xn1;
        int cont=0;
        int r_ant=0;
        try {
            do {
                entero = (a * x0 + c) / m;
                xn1 = (a * x0 + c) % m;
                if (Xn.contains(xn1)) {
                    if (formato.equals("A")) {
                        //85/100
                        lista.add(cont + "&&" + x0 + "&&" + entero + "+" + xn1 + "/" + m + "&&(" + xn1 + ")&&" + xn1 + "/" + m +"&&T");
                    } else {
                        //0.85
                        double aleatorio = (double) xn1 / m;
                        lista.add(cont + "&&" + x0 + "&&" + entero + "+" + xn1 + "/" + m + "&&(" + xn1 + ")&&" + aleatorio +"&&T");
                    }
                    //Buscar el que esta repetido
                    for (int i=0; i < lista.size(); i++){
                        if(lista.get(i).split("&&")[1].equals(xn1+"")){
                            String s[] = lista.get(i).split("&&");
                            String aux = s[0] + "&&(" + s[1]+ ")&&" + s[2]+ "&&" + s[3]+ "&&" + s[4] + "&&T";
                            lista.set(i,aux);
                        }
                    }
                    break;
                }else{
                    Xn.add(xn1);
                    x.add((double)xn1/m);
                    if (r_ant == xn1) {
                        //lista.add("Comienza a repetirse:"+r_ant+" infinitamente...");
                        lista.add("Repetición de:" + r_ant + " indefinidamente..&&1&&2&&3&&4&&gone");
                        break;
                    } else {
                        if (formato.equals("A")) {
                            //85/100
                            //
                            lista.add(cont + "&&" + x0 + "&&" + entero + "+" + xn1 + "/" + m + "&&" + xn1 + "&&" + xn1 + "/" + m +"&&F");
                        } else {
                            //0.85
                            double aleatorio = (double) xn1 / m;
                            lista.add(cont + "&&" + x0 + "&&" + entero + "+" + xn1 + "/" + m + "&&" + xn1 + "&&" + aleatorio +"&&F");
                        }
                    }
                }
                x0 = xn1;
                cont++;
                r_ant = xn1;
            }while(cont <= 1000);
        }catch (Exception e){
            Toast.makeText(this, "División entre 0.", Toast.LENGTH_LONG).show();
        }
        listView.setAdapter(new Adapter(this,lista));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.analisys:
                if(x.size() > 2) {
                    Intent intent=new Intent(RandomGenerationResult.this ,Test.class);
                    intent.putExtra("x",x);
                    intent.putExtra("from_generator",true);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "El vector x no tiene datos suficientes.", Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
