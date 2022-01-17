package com.android.practica2_farmacia30;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ver_resultados extends AppCompatActivity {

    private String codigo,nombre,descripcion,precio;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_resultados);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_index);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF0000")));

        this.codigo= (String) getIntent().getExtras().getString("codigo");
        this.nombre= (String) getIntent().getExtras().getString("nombre");
        this.descripcion= (String) getIntent().getExtras().getString("descripcion");
        this.precio= (String) getIntent().getExtras().getString("precio");

        TextView cod = findViewById(R.id.r_codigo);
        cod.setText(this.codigo);
        cod = findViewById(R.id.r_nombre);
        cod.setText(this.nombre);
        cod = findViewById(R.id.r_descripcion);
        cod.setText(this.descripcion);
        cod = findViewById(R.id.r_precio);
        cod.setText(this.precio);

        videoView = (VideoView) findViewById(R.id.videoView);
        String path = "android.resource://"+ getPackageName() + "/" + R.raw.video;
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();
    }

    public void volver(View view){
        Intent intent = new Intent(this,InicioApp.class);
        startActivity(intent);
    }
}