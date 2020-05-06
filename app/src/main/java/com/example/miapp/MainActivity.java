package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText numero1;
    private EditText numero2;
    private TextView resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero1 = (EditText) findViewById(R.id.numero1);
        numero2 = (EditText) findViewById(R.id.numero2);
        resultado = (TextView) findViewById(R.id.resultado);
    }

    public void Sumar(View view){
        String valorNumero1 = numero1.getText().toString();
        String valorNumero2 = numero2.getText().toString();

        int valorN1 = Integer.parseInt(valorNumero1);
        int valorN2 = Integer.parseInt(valorNumero2);

        int suma = valorN1+valorN2;

        String resultadoFuncion = String.valueOf(suma);
        resultado.setText(resultadoFuncion);

    }
}
