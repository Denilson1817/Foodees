package com.denilsonperez.foodees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.denilsonperez.foodees.Model.Paciente;
import com.denilsonperez.foodees.Model.Platillo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AgregarDietasActivity extends AppCompatActivity {
    private List<Platillo> listDatosPlatillos = new ArrayList<>();
    ArrayAdapter<Platillo>arrayAdapterPlatillo;
    ListView lvDatosPlatillos;
    private int selectedItemPosition = -1;
    //Para el manejo de datos en firebase
    FirebaseDatabase firebaseDataBase;
    DatabaseReference databaseReference;
    Platillo platilloSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_dietas);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Foodees");
        lvDatosPlatillos = findViewById(R.id.lvDatosPlatillos);
        inicializarFirebase();
        ListarDatos();
        lvDatosPlatillos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemPosition = i;
                platilloSeleccionado = (Platillo) adapterView.getItemAtPosition(i);
            }
        });
    }

    private void ListarDatos() {
        databaseReference.child("Platillo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDatosPlatillos.clear();
                for(DataSnapshot objSnapShot : snapshot.getChildren()){
                    Platillo p = objSnapShot.getValue(Platillo.class);
                    listDatosPlatillos.add(p);

                    arrayAdapterPlatillo = new ArrayAdapter<Platillo>(AgregarDietasActivity.this, android.R.layout.simple_list_item_multiple_choice, listDatosPlatillos);
                    lvDatosPlatillos.setAdapter(arrayAdapterPlatillo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDataBase = FirebaseDatabase.getInstance();
        //firebaseDataBase.setPersistenceEnabled(true); --> Esto es una mala practica para la persistencia de la BD.
        databaseReference =  firebaseDataBase.getReference();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agregar_dieta,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.item_agregarDieta:{
                if(selectedItemPosition != -1){
                    String itemSelected = "Selected items: \n";
                    for(int i=0;i<lvDatosPlatillos.getCount();i++){
                        if(lvDatosPlatillos.isItemChecked(i)){
                            itemSelected += lvDatosPlatillos.getItemAtPosition(i) + "\n";
                        }
                    }
                    Toast.makeText(this, itemSelected, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Selecciona un elemento", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            default:break;
        }
        return true;
    }

}