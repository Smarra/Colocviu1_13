package com.example.colocviu1_13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Colocviu1_13SecondaryActivity extends AppCompatActivity {

    Button buttonRegister, buttonCancel;
    TextView textInstructions;
    String instr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_activity);

        buttonCancel = (Button)findViewById(R.id.cancelButton);
        buttonRegister = (Button)findViewById(R.id.registerButton);
        textInstructions = (TextView)findViewById(R.id.textInstructions);

        Intent intent = getIntent();

        if (intent != null) {
            instr = intent.getStringExtra("INSTRUCTIONS");
            if (instr != null) {
                textInstructions.setText(instr);
            } else {
                Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
            }
        }

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1 , new Intent());
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0 , new Intent());
                finish();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 404:
                Toast.makeText(this, "Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
                break;
        }
    }

}
