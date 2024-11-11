package com.bmdi.taskmaster2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.preference.PreferenceManager;
import android.widget.AdapterView;
//Librerias del mapa
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//importo librerias
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    //Declarar atributos
    private EditText usuarioEditText;
    private EditText contraseñaEditText;

    private Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazo atributos con los Id correspondientes del Main
        usuarioEditText = findViewById(R.id.usuario);
        contraseñaEditText = findViewById(R.id.contraseña);
        spinner = findViewById(R.id.spinnerRoles);
        String[] roles = {"Administrador", "Usuario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Asignar el ArrayAdapter al Spinner
        spinner.setAdapter(adapter);








        }









    //Metodo OnClick para acceder dependiendo del usuario
    public void onClickAcceder(View view){
        //Declaración de variables para obtener los datos ingresados
        String user = usuarioEditText.getText().toString().trim();
        String pass = contraseñaEditText.getText().toString().trim();
        String rol = spinner.getSelectedItem().toString();

        if (user.isEmpty()){
            Toast.makeText(this,"Ingrese el usuario",Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.isEmpty()){
            Toast.makeText(this,"Ingrese la contraseña",Toast.LENGTH_SHORT).show();
            return;
        }

        if (user.equals("usuario1") && pass.equals("1234") && rol.equals("Usuario") ){
            Intent intent = new Intent(this,crud.class);
            startActivity(intent);
        }
        else if (user.equals("admin") && pass.equals("admin") && rol.equals("Administrador")){
            Intent intent = new Intent(this,Admin.class);
            startActivity(intent);
        }
        else {
            //Si las credenciales son incorrectas aparecera un mensaje
            Toast.makeText(this,"Credenciales incorrectas",Toast.LENGTH_SHORT).show();
        }
    }

    public void accederEncuesta(View view){
        Intent intent = new Intent(this,Thread.class);
        startActivity(intent);
    }
    public void accederMapa(View view){
        Intent intent = new Intent(this,Mapa.class);
        startActivity(intent);
    }
    public void accederMultimedia(View view){
        Intent intent = new Intent(this,Web.class);
        startActivity(intent);
    }



}
