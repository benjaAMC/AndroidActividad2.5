package com.bmdi.taskmaster2;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

public class Thread extends AppCompatActivity {
// Declarar variables para manipular los componentes del thread.
    private TextView textView;
    private ImageView imageView;
    public  MainActivity main;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        // Enlazar variables con los ids
        textView = findViewById(R.id.texto1);
        imageView = findViewById(R.id.imagenprueba);
        //Creo y ejecuto el thread a utilizar.
        java.lang.Thread thread = new java.lang.Thread(new Runnable() {
            @Override
            public void run() {
                //Simulo una operación que toma 5 segundos
                try{
                    java.lang.Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                //Actualizo la interfaz del usuario (UI) desde el thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Actualizo el Texto
                        textView.setText("¡Encuesta cargada con exito!");
                        //Hago visible a la imagen
                        imageView.setVisibility(View.VISIBLE);
                            //Redirijo la aplicacion  a la encuesta

                        new CountDownTimer(4000, 1000) {
                            public void onFinish() {
                                Intent intent = new Intent(Thread.this,Encuesta.class);
                                startActivity(intent);
                            }

                            public void onTick(long millisUntilFinished) {
                                // millisUntilFinished    The amount of time until finished.
                            }
                        }.start();



                    }
                });

            }
        });
        //Iniciamos el thread
        thread.start();

    }


}
