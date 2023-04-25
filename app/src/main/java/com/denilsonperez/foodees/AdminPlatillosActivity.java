package com.denilsonperez.foodees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.denilsonperez.foodees.Model.Platillo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminPlatillosActivity extends AppCompatActivity {
    private List<Platillo> listDatosPlatillos = new ArrayList<>();
    private int selectedItemPosition = -1;
    ArrayAdapter<Platillo>arrayAdapterPlatillo;
    EditText txtPlatillo;
    ListView lvDatosPlatillos;
    //Para el manejo de datos en firebase
    FirebaseDatabase firebaseDataBase;
    DatabaseReference databaseReference;
    Platillo platilloSeleccionado;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_platillos);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Foodees");
        txtPlatillo = findViewById(R.id.txtPlatillos);
        lvDatosPlatillos = findViewById(R.id.lvDatosPlatillos);
        inicializarFirebase();
        ListarDatos();

        lvDatosPlatillos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemPosition = i;
                platilloSeleccionado = (Platillo) adapterView.getItemAtPosition(i);
                txtPlatillo.setText(platilloSeleccionado.getPlato());
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

                    arrayAdapterPlatillo = new ArrayAdapter<Platillo>(AdminPlatillosActivity.this, android.R.layout.simple_list_item_1, listDatosPlatillos);
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
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String platillo = txtPlatillo.getText().toString();
        switch (item.getItemId()){
            case R.id.icon_add_dieta:{
                if(platillo.equals("")){
                    validacion();
                }else{
                    Platillo p = new Platillo();
                    p.setUid(UUID.randomUUID().toString());
                    p.setPlato(platillo);
                    databaseReference.child("Platillo").child(p.getUid()).setValue(p);
                    Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                    limpiarEditText();
                }
                break;
            }
            case R.id.icon_actualizar:{
                Platillo p = new Platillo();
                p.setUid(platilloSeleccionado.getUid());
                p.setPlato(txtPlatillo.getText().toString().trim());
                databaseReference.child("Platillo").child(p.getUid()).setValue(p);
                Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show();
                limpiarEditText();
                break;
            }
            case R.id.icon_delete:{
                Platillo p = new Platillo();
                p.setUid(platilloSeleccionado.getUid());
                databaseReference.child("Platillo").child(p.getUid()).removeValue();
                Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
                limpiarEditText();
                break;
            }
            default:break;
        }
        return true;
    }

    private void validacion() {
        String platillo = txtPlatillo.getText().toString();
        if (platillo.equals("")){
            txtPlatillo.setError("Campo requerido");
        }
    }

    private void limpiarEditText() {
        txtPlatillo.setText("");
    }
}