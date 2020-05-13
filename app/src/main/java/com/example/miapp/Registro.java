package com.example.miapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Registro extends AppCompatActivity implements View.OnClickListener {
  private EditText correoUsuario;
  private EditText contraUsuario;
  private Button registrarBoton;
  private Button volverButton;
  private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        correoUsuario = (EditText) findViewById(R.id.nombreRegistro);
        contraUsuario = (EditText) findViewById(R.id.contraRegistro);
        registrarBoton = (Button) findViewById(R.id.registroBoton1);
        registrarBoton.setOnClickListener(this);
        volverButton = (Button) findViewById(R.id.regresarInicio);
        volverButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registroBoton1:
                registroUsuario();
                break;
            case R.id.regresarInicio:
                Intent intencion = new Intent(this,MainActivity.class);
                startActivity(intencion);
        }

    }

    public void registroUsuario(){

        String correo = correoUsuario.getText().toString().trim();
        String contra = contraUsuario.getText().toString().trim();

        if(TextUtils.isEmpty(correo)){
            Toast.makeText(this,"Debe ingresar un correo electronico ",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(contra)){
            Toast.makeText(this,"Debe ingresar una contraseña ",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registro en proceso...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(correo,contra)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Registro.this,"Se registro correctamente",Toast.LENGTH_LONG).show();

                        }else{
                            if(task.getException()instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(Registro.this,"Este usuario ya esta registrado",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(Registro.this,"No Se registro correctamente",Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}
