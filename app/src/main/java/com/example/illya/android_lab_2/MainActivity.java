package com.example.illya.android_lab_2;

import java.io.*;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            fileOutputStream = openFileOutput("data.bin",  Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            System.out.println("Error: "+e.getMessage());
            fileOutputStream = null;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button okButton = (Button) findViewById(R.id.okButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Button historyButton = (Button) findViewById(R.id.history);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final EditText editText = (EditText) findViewById(R.id.editText);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selected = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                String text = editText.getText().toString();
                String font = (String) selected.getText();
                ItemListDialogFragment.newInstance(text, font).show(getSupportFragmentManager(), "dialog");
                writeFile(text);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("file_output", readFile());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            fileInputStream.close();
            fileOutputStream.close();
        }
        catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    private void writeFile(String str) {
        if (fileOutputStream != null && !str.equals("")) {
            try {
                fileOutputStream.write((str + "\n").getBytes());
                System.out.println(str + "\n");
            }
            catch(IOException e){
                System.out.println("Error: "+e.getMessage());
            }
        }
    }

    private String readFile() {
        String output = "";
        try {
            fileInputStream = openFileInput("data.bin");
            byte[] data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            output = new String(data, "UTF-8");
            fileInputStream.close();
        }
        catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
        return output;
    }
}
