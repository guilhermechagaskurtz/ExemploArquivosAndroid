package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.DocumentsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


public class TelaEx3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ex3);

        //Mostra o conteúdo da pasta pública de Downloads
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Toast.makeText(this, downloadsDir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        File[] files = downloadsDir.listFiles();
        for (File file : files) {
            Toast.makeText(this, file.getName(), Toast.LENGTH_SHORT).show();
        }

        //Testa se o aparelho tem armazenamento externo/público
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "Disponível", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Não disponível", Toast.LENGTH_SHORT).show();
        }
        //VERIFICAR SE O USUÁRIO JÁ DEU PERMISSÃO PARA LEITURA E ESCRITA DO SISTEMA DE ARMAZENAMENTO,
        //OU PEDIR PERMISSÃO CASO NÃO TENHA DADO AINDA
        verificaPermissoes();

    }

    public void verificaPermissoes(){
        //Verifico se a versão do Android do aparelho é 6.0+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissaoLeitura = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            int permissaoEscrita = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissaoLeitura == PackageManager.PERMISSION_GRANTED && permissaoEscrita == PackageManager.PERMISSION_GRANTED) { //Se o usuário já deu permissão de acessar o armazenamento
                visualizarArquivo();
            } else {
                //vamos pedir permissão pro usuário de acessar o sistema de armazenamento
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1234);
            }
        }
        else{ //se a versão for 6.0 para baixo, já vai direto ler os arquivos
            visualizarArquivo();
        }
    }

    //Função que retorna indicando se o usuário deu ou não permissão de leitura/escrita em armazenamento público
    @Override
    public void onRequestPermissionsResult(int codigoRequisicao, String permissoes[], int[] resultados) {
        super.onRequestPermissionsResult(codigoRequisicao, permissoes, resultados);
        if(codigoRequisicao == 1234) { //se for do pedido de permissão de leitura/escrita no armazenamento externo
            // Se a requisição for cancelada, o array resultados será vazio.
            if (resultados.length > 0 && resultados [0] == PackageManager.PERMISSION_GRANTED) {
                /* permissão garantida */
                visualizarArquivo();
            } else {
                /* permissão negada */
                Toast.makeText(this, "Não foi possível ler o arquivo pois o usuário não deu permissão", Toast.LENGTH_SHORT).show();
            }
            return;
        }
    }
    public void visualizarArquivo(){
        TextView txt = (TextView) findViewById(R.id.textConteudoSD);
        //referencia da raiz do cartao de memoria externo
        //File pasta = Environment.getExternalStorageDirectory();
        File pasta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File f = new File(pasta, "arquivo.txt");
        txt.setText("");
        if(f.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(f));
                String linha="";
                while((linha = reader.readLine())!=null){
                    txt.setText(txt.getText()+linha+"\n");
                }
            }
            catch(Exception e){
                mostra("Deu pau"+e.getMessage());
            }
        }
        else{
            mostra("Arquivo nao existe");
        }

    }

    public void mostra(String texto){
        Toast.makeText(this,texto,Toast.LENGTH_LONG).show();
    }

    public void criarArquivoClick(View view) {
        File pasta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File arquivo = new File(pasta, "arquivo.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));
            writer.write("linha1\n");
            writer.write("linha2\n");
            writer.write("linha3\n");
            writer.write("linha4\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visualizarArquivoClick(View view) {
        verificaPermissoes();
    }



}
