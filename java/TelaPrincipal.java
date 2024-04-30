package com.example.myapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TelaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);
    }

    public void abreTelaEx1(View v){
        Intent it = new Intent(this, TelaEx1.class);
        startActivity(it);
    }

    public void abreTelaEx2(View v){
        Intent it = new Intent(this, TelaEx2.class);
        startActivity(it);
    }

    public void abreTelaEx3(View v){
        Intent it = new Intent(this, TelaEx3.class);
        startActivity(it);
    }
}
