package com.denilsonperez.foodees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.denilsonperez.foodees.Model.Paciente;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VisualizarPacientesActivity extends AppCompatActivity {
    private List<Paciente> listDatosPacientes = new ArrayList<Paciente>();
    private int selectedItemPosition = -1;
    ArrayAdapter<Paciente> arrayAdapterPaciente;
    EditText txtNombrePaciente , txtCorreoPaciente, txtContrasenaPaciente;
    ListView lvDatosPacientes;
    //Para el manejo de datos en firebase
    FirebaseDatabase firebaseDataBase;
    DatabaseReference databaseReference;
    Paciente pacienteSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_pacientes);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Foodees");
        txtNombrePaciente = findViewById(R.id.txtNombrePaciente);
        txtCorreoPaciente = findViewById(R.id.txtCorreoPaciente);
        txtContrasenaPaciente = findViewById(R.id.txtContrasenaPaciente);
        lvDatosPacientes = findViewById(R.id.lvDatosPacientes);
        inicializarFirebase();
        ListarDatos();

        lvDatosPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemPosition = i;
                pacienteSeleccionado = (Paciente) adapterView.getItemAtPosition(i);
                txtNombrePaciente.setText(pacienteSeleccionado.getNombres());
                txtCorreoPaciente.setText(pacienteSeleccionado.getCorreo());
                txtContrasenaPaciente.setText(pacienteSeleccionado.getContrasena());
            }
        });
    }

    private void ListarDatos() {
        databaseReference.child("Paciente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDatosPacientes.clear();
                for(DataSnapshot objSnapShot : snapshot.getChildren()){
                    Paciente p = objSnapShot.getValue(Paciente.class);
                    listDatosPacientes.add(p);

                    arrayAdapterPaciente = new ArrayAdapter<Paciente>(VisualizarPacientesActivity.this, android.R.layout.simple_list_item_1, listDatosPacientes);
                    lvDatosPacientes.setAdapter(arrayAdapterPaciente);
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
        switch (item.getItemId()){
            case R.id.icon_add_dieta:{
                if(selectedItemPosition != -1){
                    Paciente p = new Paciente();
                    String idPaciente="";
                    //Pasar dieta al paciente
                    idPaciente = pacienteSeleccionado.getUid();

                    //startActivity(new Intent(VisualizarPacientesActivity.this, AgregarDietasActivity.class));
                    Intent intent = new Intent(VisualizarPacientesActivity.this, AgregarDietasActivity.class);
                    intent.putExtra("idPaciente", idPaciente);
                    startActivity(intent);
                    Toast.makeText(this, "Agregar dieta", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Selecciona un elemento", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.icon_actualizar:{
                Paciente p = new Paciente();
                p.setUid(pacienteSeleccionado.getUid());
                p.setNombres(txtNombrePaciente.getText().toString().trim()); //El metodo trim hace que se ignoren los espacios en blanco.
                p.setCorreo(txtCorreoPaciente.getText().toString().trim());
                p.setContrasena(txtContrasenaPaciente.getText().toString().trim());
                databaseReference.child("Paciente").child(p.getUid()).setValue(p);
                Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show();
                limpiarEditText();
                break;
            }
            case R.id.icon_delete:{
                Paciente p = new Paciente();
                p.setUid(pacienteSeleccionado.getUid());
                databaseReference.child("Paciente").child(p.getUid()).removeValue();
                Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
                limpiarEditText();
                break;
            }
            default:break;
        }
        return true;
    }
    private void limpiarEditText() {
        txtNombrePaciente.setText("");
        txtCorreoPaciente.setText("");
        txtContrasenaPaciente.setText("");
    }

}