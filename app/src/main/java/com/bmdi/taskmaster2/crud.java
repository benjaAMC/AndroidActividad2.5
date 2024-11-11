package com.bmdi.taskmaster2;

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




import androidx.appcompat.app.AppCompatActivity;



public class crud extends AppCompatActivity {

    EditText edtId, edtNombre, edtTarea;
    ListView lista;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);


        //Defino los compos del formulario
        edtId = (EditText) findViewById(R.id.edtId);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtTarea = (EditText) findViewById(R.id.edtTarea);

        lista = (ListView) findViewById(R.id.lstLista);
        CargarLista();
    }

    //metodo para cargar la lista de datos
    public void CargarLista() {
        //llamamos la clase DataHelper para manipular la BDD
        DataHelper dh = new DataHelper(this, "tarea.db", null, 1);
        //Activo el modo de escritura de la BDD
        SQLiteDatabase bd = dh.getWritableDatabase();
        //Ejecutamos la consulta para cargar los datos de la tabla
        Cursor c = bd.rawQuery("SELECT id, nombre, tarea FROM tarea", null);
        //el resultado obtenido lo organizo en la lista
        String[] arr = new String[c.getCount()];
        //verificamos si al menos la tabla tiene al menos 1 resultado
        if (c.moveToFirst() == true) {
            //indice del Array
            int i = 0;
            //utilizamos de do while para utilizar un bucle para imprimir resultados

            do {
                //mostrara los datos en la lista
                String linea = "||" + c.getInt(0) + "||" + c.getString(1) + "||" + c.getString(2) + "||";
                //Cuando aparecen los datos se ordena en el Array
                arr[i] = linea;
                i++;    //itera dependiendo de los datos registrado en la tabla
                //se repite hasta que el retorno de datos sea falta
            } while (c.moveToNext() == true);
            //creo un arrayAdapter para agregar los resultados obtenidos a la lista
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, arr);
            //asigno el arreglo a la lista
            lista.setAdapter(adapter);
            bd.close();
        }
    }

    public void onClickAgregar(View view) {
        DataHelper dh = new DataHelper(this, "tarea.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        //creo un contentValues que contendra los valores insertados
        ContentValues reg = new ContentValues();
        reg.put("id", edtId.getText().toString());
        reg.put("nombre", edtNombre.getText().toString());
        reg.put("tarea", edtTarea.getText().toString());


        //inserto los datos en la BDD y retorno una respuesta
        long resp = bd.insert("tarea", null, reg);
        bd.close();
        //verifico si el dato se inserta
        if (resp == -1) {
            Toast.makeText(this, "no se registro", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Se Agrego registro", Toast.LENGTH_LONG).show();
        }
        CargarLista();
        limpiar();
    }

    //metodo para limpiar los campos de texto
    public void limpiar() {
        edtId.setText("");
        edtNombre.setText("");
        edtTarea.setText("");
    }



    //metodo modificar
    public void onClickModificar(View view){
        DataHelper dh = new DataHelper(this,"tarea.db",null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        //Envio los datos a modificar
        reg.put("id",edtId.getText().toString());
        reg.put("nombre",edtNombre.getText().toString());
        reg.put("tarea",edtTarea.getText().toString());

        //actualizo el registro de la base de datos por el rut
        long resp = bd.update("tarea",reg, "id=? ", new String[]{edtId.getText().toString()});
        bd.close();
        //envio la respuesta al usuario
        if (resp == -1){
            Toast.makeText(this,"no se registro en la DB",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Dato Modificado", Toast.LENGTH_LONG).show();
        }
        CargarLista();
        limpiar();
    }
    //metodo eliminar
    public void onClickEliminar(View view){
        DataHelper dh = new DataHelper(this,"tarea.db",null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        //ingreso el rut del registro a modificar
        String IdEliminar = edtId.getText().toString();
        //query para eliminar el registro

        long respuesta = bd.delete("tarea","id=" + IdEliminar,null);
        bd.close();
        //envio la respuesta al usuario
        if (respuesta == -1){
            Toast.makeText(this,"no se elimino",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Dato Eliminado", Toast.LENGTH_LONG).show();
        }
        CargarLista();
        limpiar();
    }
}
