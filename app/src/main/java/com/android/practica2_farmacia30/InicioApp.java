package com.android.practica2_farmacia30;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class InicioApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_index);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF0000")));
    }

    public void verListado(View view){
        Intent intent = new Intent(this,buscar.class);
        startActivity(intent);
    }

    public void administrar(View view){
        Intent intent = new Intent(this,prueba.class);
        startActivity(intent);
    }

    public void ayuda(View view){
        Intent intent = new Intent (this,ayuda.class);
        startActivity(intent);
    }
}