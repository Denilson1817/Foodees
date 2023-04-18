package com.denilsonperez.foodees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuPrincipalPacienteActivity extends AppCompatActivity {
    Button cerrarSesion, verPlatillos, verRecetas;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    TextView nombresPrincipalP, correoPrincipalP;
    ProgressBar progresoDatos;

    DatabaseReference Pacientes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_paciente);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Foodees");

        nombresPrincipalP = findViewById(R.id.nombresPrincipalPaciente);
        correoPrincipalP = findViewById(R.id.correosPrincipalPaciente);
        progresoDatos = findViewById(R.id.progresoDatosPaciente);

        Pacientes = FirebaseDatabase.getInstance().getReference("Pacientes");
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        cerrarSesion = findViewById(R.id.btnCerrarSesion);
        verPlatillos = findViewById(R.id.btnPlatillos);
        verRecetas = findViewById(R.id.btnVisualizarRecetas);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salirAplicacion();
            }
        });
    }

    private void salirAplicacion() {
        firebaseAuth.signOut();
        startActivity(new Intent(MenuPrincipalPacienteActivity.this, IniciarSesionActivity.class));
        Toast.makeText(this, "Sesión finalizada", Toast.LENGTH_SHORT).show();
    }

    private void cargaDeDAtos(){
        Pacientes.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Si el usuario existe
                if(snapshot.exists()){
                    progresoDatos.setVisibility(View.GONE);
                    //Mostrar los texview
                    nombresPrincipalP.setVisibility(View.VISIBLE);
                    correoPrincipalP.setVisibility(View.VISIBLE);

                    //Obtener los datos de firebase
                    String nombres = ""+snapshot.child("nombres").getValue();
                    String correo = ""+snapshot.child("correo").getValue();

                    //Setear los datos en los respectivos textview.
                    nombresPrincipalP.setText(nombres);
                    correoPrincipalP.setText(correo);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void comprobarInicioSesion(){
        if(user!=null){
            //El usuario a iniciado sesión
            cargaDeDAtos();
        }else{
            startActivity(new Intent(MenuPrincipalPacienteActivity.this, IniciarSesionActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        comprobarInicioSesion();
        super.onStart();
    }
}