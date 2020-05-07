package com.example.miapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText numero1;
    private EditText numero2;
    private Button botonRegistro,botonIngresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonRegistro = (Button) findViewById(R.id.registroBoton);
        botonRegistro.setOnClickListener(this);

        botonIngresar = (Button) findViewById(R.id.ingresarBoton);
        botonIngresar.setOnClickListener(this);

        numero1 = (EditText) findViewById(R.id.correoElectronico);
        numero2 = (EditText) findViewById(R.id.password);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ingresarBoton:
                validacionIngreso();
                break;
            case R.id.registroBoton:
                Intent intencion = new Intent(this,Registro.class);
                startActivity(intencion);
                break;

        }

    }

    public void validacionIngreso(){
        String valorNumero1 = numero1.getText().toString();
        String valorNumero2 = numero2.getText().toString();



       ;

    }

}
