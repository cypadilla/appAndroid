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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText contraUsuario,correoUsuario;
    private Button botonRegistro,botonIngresar;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        contraUsuario = (EditText) findViewById(R.id.correoElectronico);
        correoUsuario = (EditText) findViewById(R.id.password);

        progressDialog = new ProgressDialog(this);

        botonRegistro = (Button) findViewById(R.id.registroBoton);
        botonRegistro.setOnClickListener(this);

        botonIngresar = (Button) findViewById(R.id.ingresarBoton);
        botonIngresar.setOnClickListener(this);



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

            String correo = correoUsuario.getText().toString().trim();
            String contra = contraUsuario.getText().toString().trim();

            if(TextUtils.isEmpty(correo)){
                Toast.makeText(this,"Ingrese algo no sea concha",Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(contra)){
                Toast.makeText(this,"Ingrese algo no sea concha",Toast.LENGTH_LONG).show();
                return;
            }

            progressDialog.setMessage("Login en proceso...");
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(correo,contra)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Usuario logueado correctamente",Toast.LENGTH_LONG).show();
                            }else{
                                if(task.getException()instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(MainActivity.this,"no se logueo correctamente ",Toast.LENGTH_LONG).show();
                                }
                            }
                            progressDialog.dismiss();
                        }
                    });
        }

       ;

    }


