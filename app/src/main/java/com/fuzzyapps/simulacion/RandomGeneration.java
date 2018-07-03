package com.fuzzyapps.simulacion;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RandomGeneration extends AppCompatActivity {
    private Button siguiente;
    private EditText a,c,x0,m;
    private CheckBox c1,c2;
    private String formato;
    private TextView bienvenido;
    private AdminSQLiteOpenHelper admin;
    private String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_generation);
        siguiente=(Button)findViewById(R.id.button);
        admin = new AdminSQLiteOpenHelper(this,"uniforme", null, 1);
        a=(EditText)findViewById(R.id.editText);
        c=(EditText)findViewById(R.id.editText3);
        x0=(EditText)findViewById(R.id.editText2);
        m=(EditText)findViewById(R.id.editText4);
        c1=(CheckBox)findViewById(R.id.checkBox);//85/100
        c2=(CheckBox)findViewById(R.id.checkBox2);//0.85
        c1.setChecked(true);
        formato="A";
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                c1.setChecked(true);
                c2.setChecked(false);
                formato="A";
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                c1.setChecked(false);
                c2.setChecked(true);
                formato="B";
            }
        });
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(!a.getText().toString().equals("") && !c.getText().toString().equals("") && !x0.getText().toString().equals("") && !m.getText().toString().equals("")){
                    Intent intent=new Intent(RandomGeneration.this ,RandomGenerationResult.class);
                    //Bundle b=new Bundle();
                    intent.putExtra("a",a.getText().toString());
                    intent.putExtra("c",c.getText().toString());
                    intent.putExtra("x0",x0.getText().toString());
                    intent.putExtra("m",m.getText().toString());
                    intent.putExtra("formato",formato);
                    startActivity(intent);
                }else{
                    Toast.makeText(RandomGeneration.this,"Faltan Datos!! :3", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
