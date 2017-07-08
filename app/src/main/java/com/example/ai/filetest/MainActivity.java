package com.example.ai.filetest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= (EditText) findViewById(R.id.add);
        String content= load();
        if(!content.isEmpty()){
            editText.setText(content);
            editText.setSelection(content.length());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String buf= editText.getText().toString();
        save(buf);
    }

    private String load(){
        FileInputStream in=null;
        BufferedReader bufferedReader=null;
        StringBuilder content= new StringBuilder();
        try{
            in= openFileInput("data");
            bufferedReader= new BufferedReader(new InputStreamReader(in));
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return content.toString();
    }
    private void save(String buf){
        FileOutputStream out= null;
        BufferedWriter writer=null;
        try {
            out= openFileOutput("data", Context.MODE_APPEND);
            writer= new BufferedWriter(new OutputStreamWriter(out));
            writer.write(buf);
        } catch (IOException e) {
        e.printStackTrace();
        }finally {
            try {
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
