package com.example.colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button navigateButton, buttonNorth, buttonEast, buttonWest, buttonSouth, buttonIntent;
    TextView textView, textSum;
    boolean n = false, s = false, e = false, w = false;

    private void increaseSum() {
        int sum = Integer.parseInt(textSum.getText().toString());
        String newSum = String.valueOf(sum + 1);
        textSum.setText(newSum);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigateButton = (Button)findViewById(R.id.buttonNavigate);
        buttonNorth = (Button)findViewById(R.id.buttonNorth);
        buttonEast = (Button)findViewById(R.id.buttonEast);
        buttonWest = (Button)findViewById(R.id.buttonWest);
        buttonSouth = (Button)findViewById(R.id.buttonSouth);
        textView = (TextView)findViewById(R.id.textView);
        textSum = (TextView)findViewById(R.id.textSum);

        buttonNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textView.getText().toString();
                String adder = ((Button)v).getText().toString();
                String newtext = text + " " + adder;
                textView.setText(newtext);

                increaseSum();
                n = true;
            }
        });

        buttonEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textView.getText().toString();
                String adder = ((Button)v).getText().toString();
                String newtext = text + " " + adder;
                textView.setText(newtext);

                increaseSum();
                e = true;
            }
        });

        buttonWest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textView.getText().toString();
                String adder = ((Button)v).getText().toString();
                String newtext = text + " " + adder;
                textView.setText(newtext);

                increaseSum();
                w = true;
            }
        });

        buttonSouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textView.getText().toString();
                String adder = ((Button)v).getText().toString();
                String newtext = text + " " + adder;
                textView.setText(newtext);

                increaseSum();
                s = true;
            }
        });


        if (savedInstanceState == null) {
            Log.d("TAG", "onCreate() method was invoked without a previous state");
        } else {
            Log.d("TAG", "onCreate() method was invoked WITH a previous state");
            if (savedInstanceState.containsKey("SUM")) {
                textSum.setText(savedInstanceState.getString("SUM"));
            }
        }

        // INTENTS
        buttonIntent = (Button)findViewById(R.id.buttonIntent);
        buttonIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = textView.getText().toString();
                Intent intent = new Intent("android.intent.action.Colocviu.Secondary");
                intent.putExtra("INSTRUCTIONS", txt);
                startActivityForResult(intent, 404);

                if (n && e && w && s) {
                    Intent intent2 = new Intent(getApplicationContext(), Colocviu1_13Service.class);
                    intent2.putExtra("INSTRS", txt);
                    getApplicationContext().startService(intent2);
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch(requestCode) {
            case 404:
                if (resultCode == 1)
                    Toast.makeText(this, "Register pressed", Toast.LENGTH_LONG).show();
                if (resultCode == 1)
                    Toast.makeText(this, "Cancel pressed", Toast.LENGTH_LONG).show();
                textView.setText("Directions:");
                textSum.setText("0");
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("SUM", textSum.getText().toString());
    }
}
