package com.denilsonperez.foodees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AgregarPacienteActivity extends AppCompatActivity {

    EditText codigoEtPaciente, nombreEtPaciente, correoEtPaciente, contrasenaEtPaciente, confirmarContrasenaEtPaciente;
    Button btnRegistrarPaciente;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    String nombreP = "", correoP = "", passwordP = "", confirmarPasswordP = "", codigoP="";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_paciente);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Registrar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //inicializar las vistas
        codigoEtPaciente = findViewById(R.id.codigoPaciente);
        nombreEtPaciente = findViewById(R.id.nombreEtPaciente);
        correoEtPaciente = findViewById(R.id.correoEtPaciente);
        contrasenaEtPaciente = findViewById(R.id.contrasenaEtPaciente);
        confirmarContrasenaEtPaciente = findViewById(R.id.confirmarContrasenaEtPaciente);
        btnRegistrarPaciente = findViewById(R.id.btnRegistrarPaciente);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(AgregarPacienteActivity.this);
        progressDialog.setTitle("Espere por favor");
        progressDialog.setCanceledOnTouchOutside(false);

        btnRegistrarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDatos();
            }
        });

    }

    private void validarDatos() {
        codigoP = codigoEtPaciente.getText().toString();
        nombreP = nombreEtPaciente.getText().toString();
        correoP = correoEtPaciente.getText().toString();
        passwordP = contrasenaEtPaciente.getText().toString();
        confirmarPasswordP = confirmarContrasenaEtPaciente.getText().toString();

        if(TextUtils.isEmpty(nombreP)){
            Toast.makeText(this, "Ingrese nombre", Toast.LENGTH_SHORT).show();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(correoP).matches()){
            Toast.makeText(this, "Ingrese correo", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(codigoP)) {
            Toast.makeText(this, "Ingrese un código para el paciente", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(passwordP)){
            Toast.makeText(this, "Ingrese contraseña", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(confirmarPasswordP)){
            Toast.makeText(this, "Confirme contraseña", Toast.LENGTH_SHORT).show();
        }else if(!passwordP.equals(confirmarPasswordP)){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }else{
            crearCuenta();
        }
    }

    private void crearCuenta() {
        progressDialog.setMessage("Creando su cuenta");
        progressDialog.show();
        //Crear un usuario en firebase
        firebaseAuth.createUserWithEmailAndPassword(correoP, passwordP)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //
                        guardarInformacion();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(AgregarPacienteActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void guardarInformacion() {
        progressDialog.setMessage("Guardando su información");
        progressDialog.dismiss();

        //Obtener la identificación de usuario actual
        String uid = firebaseAuth.getUid();
        //Configurar datos para agregar en la base de datos
        HashMap<String, String> Datos = new HashMap<>();
        Datos.put("uid",uid);
        Datos.put("codigoPaciente", codigoP);
        Datos.put("correo",correoP);
        Datos.put("nombres",nombreP);
        Datos.put("contraseña", passwordP);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Pacientes");
        databaseReference.child(uid)
                .setValue(Datos)
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void unused){
                        progressDialog.dismiss();
                        Toast.makeText(AgregarPacienteActivity.this, "Cuenta creada", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(AgregarPacienteActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}