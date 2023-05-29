package com.denilsonperez.foodees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.denilsonperez.foodees.Model.Platillo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuPrincipalPacienteActivity extends AppCompatActivity {
    Button cerrarSesion;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView nombresPrincipalP, correoPrincipalP;
    ProgressBar progresoDatos;
    DatabaseReference Pacientes;
    Intent recibir;
    String Lista;
    ListView listaReceta;
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
        listaReceta = findViewById(R.id.ListaReceta);

        Pacientes = FirebaseDatabase.getInstance().getReference("Paciente");
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        cerrarSesion = findViewById(R.id.btnCerrarSesion);
        inicializarFirebase();
        listarDatos();


        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salirAplicacion();
            }
        });
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    private void listarDatos(){
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference pacienteRef = databaseRef.child("Paciente");
        pacienteRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> recetasList = new ArrayList<>();
                //listaDatosPlatillo.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String receta = snapshot1.child("Receta").getValue(String.class);
                    // Agrega el valor de "Receta" a la lista
                    recetasList.add(receta);
                }
                // Configura el ListView con los datos obtenidos
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MenuPrincipalPacienteActivity.this, android.R.layout.simple_list_item_1, recetasList);
                listaReceta.setAdapter(adapter);
                    //arrayAdapterPlatillo = new ArrayAdapter<Platillo>(MenuPrincipalPacienteActivity.this, android.R.layout.simple_list_item_1, listaDatosPlatillo);
                    //lvDatosPlatillo.setAdapter(arrayAdapterPlatillo);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void salirAplicacion() {
        firebaseAuth.signOut();
        //startActivity(new Intent(MenuPrincipalPacienteActivity.this, IniciarSesionActivity.class));
        Intent intent = new Intent(MenuPrincipalPacienteActivity.this, IniciarSesionActivity.class);
        intent.putExtra("Lista",Lista);
        startActivity(intent);
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