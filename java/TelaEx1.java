package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class TelaEx1 extends AppCompatActivity {

    public final String NOME_ARQUIVO = "string.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ex1);
        visualizarArquivo();
    }

    public void salvarClick(View v){
        salvarArquivo();
        visualizarArquivo();
    }

    public void deletarClick(View v){
        deletarArquivo();
        visualizarArquivo();
    }

    public void deletarArquivo(){
        deleteFile(NOME_ARQUIVO);//deleta o arquivo
    }

    public void salvarArquivo(){
        try{
            //cria o arquivo se não existe, se não, abre no modo APPEND
            FileOutputStream out = openFileOutput(NOME_ARQUIVO, MODE_APPEND);//objeto utilizado para gravar BYTES no arquivo
            EditText edit = (EditText) findViewById(R.id.editText1);
            String msg = edit.getText().toString();
            out.write(msg.getBytes()); //grava a mensagem digitada no arquivo
            out.write("\n".getBytes());//grava uma quebra de linha no arquivo
            out.close(); //fecha o arquivo
        }
        catch (Exception e){
            Toast.makeText(this,"Erro ao salvar no arquivo",Toast.LENGTH_LONG).show();
        }
    }
    public void visualizarArquivo(){
        EditText edit = (EditText) findViewById(R.id.editConteudo);
        try{
            File f = getFileStreamPath(NOME_ARQUIVO);
            if(f.exists()){//testa se o arquivo existe
                FileInputStream in = openFileInput(NOME_ARQUIVO);//abrindo o fluxo de bytes do arquivo
                int tamanho = in.available();//retorna o tamanho do arquivo em bytes
                byte buffer[] = new byte[tamanho];
                in.read(buffer);//le os dados do arquivo e carrega os mesmos no buffer de bytes
                String s = new String(buffer); //transformo o buffer de bytes em string
                edit.setText(s);
            }
            else{
                edit.setText("");
            }
        } catch(Exception e){
            Toast.makeText(this,"Arquivo não encontrado",Toast.LENGTH_LONG).show();
        }
    }

}
