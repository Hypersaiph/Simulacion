package com.fuzzyapps.simulacion;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button registro;
    private AdminSQLiteOpenHelper admin;
    private EditText n;
    private Context context;
    private String nombre;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registro=(Button)findViewById(R.id.button3);
        n=(EditText)findViewById(R.id.editText5);
        textView = (TextView) findViewById(R.id.msg);
        context=this;
        admin = new AdminSQLiteOpenHelper(this,"uniforme", null, 1);
        textView.setText("Esta aplicación tiene el único fin de facilitar y ayudar al usuario en el proceso de los ejercicios sobre Simulación, al ingresar tu Nick y presionar “ACEPTAR” estás de acuerdo con utilizar la aplicación con fines de aprendizaje exclusivamente.");
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(!n.getText().toString().equals("")){
                    //registrar
                    SQLiteDatabase bd = admin.getWritableDatabase();
                    ContentValues registro = new ContentValues();
                    registro.put("nick", n.getText().toString());
                    bd.insert("Usuario", null, registro);
                    bd.close();
                    Toast.makeText(MainActivity.this, "Hola, "+n.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"Escribe tu nick! :3", Toast.LENGTH_SHORT).show();
                }
            }
        });
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select nick from Usuario where id=1", null);
        if (fila.moveToFirst()) {
            nombre=fila.getString(0);
            //concegir el nombre
            Toast.makeText(MainActivity.this, "Hola, "+nombre, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Menu.class);
            startActivity(intent);
            finish();
        }
    }
}
