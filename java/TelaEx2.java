package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TelaEx2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ex2);
        visualizarArquivo();
    }

    public void visualizarArquivo(){
        TextView txt = (TextView) findViewById(R.id.textConteudo);
        txt.setText("");
        try{
            //abrir o fluxo de bytes do arquivo.txt da pasta assets
            InputStream in = this.getAssets().open("arquivo.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String linha="";
            String conteudo = "";
            //le as linhas do arquivo enquanto há linhas para ler
            while((linha = reader.readLine())!=null){
                conteudo+=linha+"\n";//concatena as linhas do arquivo na variável conteúdo, + \n
            }
            txt.setText(conteudo);
        }catch(Exception e){
            Toast.makeText(this,"Arquivo não encontrado",Toast.LENGTH_LONG).show();
        }
    }
}
