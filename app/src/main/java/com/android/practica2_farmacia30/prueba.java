package com.android.practica2_farmacia30;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class prueba extends AppCompatActivity {

    public EditText et_codigo, et_descripcion, et_precio, et_nombre ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_index);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF0000")));
        et_codigo = findViewById(R.id.ccod);
        et_descripcion = findViewById(R.id.cnom);
        et_precio = findViewById(R.id.cdes);
        et_nombre = findViewById(R.id.cpre);
    }

    public void Eliminar(View view) {


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bd_productos", null, 1);
        SQLiteDatabase BdD = admin.getWritableDatabase();
        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()) {
            int cantidad = BdD.delete("productos", "codigo=" + codigo, null);
            BdD.close();
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");
            et_nombre.setText("");
            if (cantidad == 1) {

                Toast.makeText(this, "El producto ha sido eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El producto no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Falta introducir el código del producto", Toast.LENGTH_SHORT).show();
        }
    }


    public void Registrar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "bd_productos", null, 1);

        SQLiteDatabase BdD = admin.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();
        String nombre = et_nombre.getText().toString();
        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            Long a = BdD.insert("productos", null, registro);
            BdD.close();
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");
            et_nombre.setText("");

            if(a==-1) {
                Toast.makeText(this, "Producto ya existe o se puso mal algun dato \n Comprueba que el codigo y precio sea un Numero", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Producto registrado con Codigo " + a, Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
        }
    }

    public void Modificar(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "bd_productos", null, 1);

        SQLiteDatabase BdD = admin.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();
        String nombre = et_nombre.getText().toString();

        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);
            registro.put("nombre", nombre);
            int cantidad = BdD.update( "productos", registro,"codigo=" + codigo,null);
            BdD.close();

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");
            et_nombre.setText("");

            if (cantidad == 1) {

                Toast.makeText(this, "El producto ha sido modificado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El producto no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
        }

    }
}