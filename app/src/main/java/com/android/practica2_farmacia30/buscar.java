package com.android.practica2_farmacia30;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class buscar extends AppCompatActivity {

    EditText p_nombre;

    private String codigoBuscado,nombreBuscado,descripcionBuscado,precioBuscado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_index);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF0000")));

        p_nombre= (EditText) findViewById(R.id.busc);
    }

    public void buscador(View view) {

        String nom = p_nombre.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bd_productos", null, 1);
        SQLiteDatabase BdD = admin.getReadableDatabase();

        if (!nom.isEmpty()) {

            String[] campos = {"codigo","nombre","descripcion","precio"};
           //Cursor fila = BdD.rawQuery("select codigo,nombre,descripcion,precio from productos", null);
            //Cursor fila = BdD.rawQuery("select codigo,nombre,descripcion,precio from productos where nombre=Sergio", null);
            Cursor fila = BdD.rawQuery("select * from productos where descripcion='"+nom+"'",null);
            //Cursor fila=BdD.query("productos", campos, null, null,
                  //  null, nom, null, null);

           try {
               fila.moveToFirst();
               this.codigoBuscado = fila.getString(0);
               this.nombreBuscado = fila.getString(1);
               this.descripcionBuscado = fila.getString(2);
               this.precioBuscado = fila.getString(3);
               BdD.close();
               Intent intento = new Intent(this,ver_resultados.class);
               intento.putExtra("codigo",this.codigoBuscado);
               intento.putExtra("nombre",this.nombreBuscado);
               intento.putExtra("descripcion",this.descripcionBuscado);
               intento.putExtra("precio",this.precioBuscado);
               startActivity(intento);
           }catch (Exception e){
               Toast.makeText(this, "El producto " + nom + " no existe ", Toast.LENGTH_SHORT).show();
           }
            /*if (fila.moveToFirst()) {
                this.codigoBuscado = fila.getString(0);
                this.nombreBuscado = fila.getString(1);
                this.descripcionBuscado = fila.getString(2);
                this.precioBuscado = fila.getString(3);
                BdD.close();
                Intent intento = new Intent(this,ver_resultados.class);
                intento.putExtra("codigo",this.codigoBuscado);
                intento.putExtra("nombre",this.nombreBuscado);
                intento.putExtra("descripcion",this.descripcionBuscado);
                intento.putExtra("precio",this.precioBuscado);
               startActivity(intento);
            } else {
                Toast.makeText(this, "El producto no existe", Toast.LENGTH_SHORT).show();
                BdD.close();
            }*/

       } else {
            Toast.makeText(this, "El campo Nombre esta vacio", Toast.LENGTH_SHORT).show();
        }


    }

}