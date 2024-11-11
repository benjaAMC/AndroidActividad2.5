package com.bmdi.taskmaster2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//Importo librerias para Video View
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;
import android.media.MediaPlayer;
import android.view.View;


public class Web extends  AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);



    //Configuracion del WebView para cargar video de Yotube
    WebView webView = findViewById(R.id.webView);
    //Obtengo la configuración del WebView
    WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    //Agrego la URL del Video en formato embed
    String videoUrl = "https://www.youtube.com/embed/qWiMJUau7o8";
    //Cargo el webView en la app
        webView.setWebViewClient(new WebViewClient());
    //Cargar la URL en el video del webView
        webView.loadUrl(videoUrl);



        //Configuración de reproducción del sonido MP3
        findViewById(R.id.reproducir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creo un MediaPlayer para reproducir el sonido
                MediaPlayer mediaPlayer = MediaPlayer.create(Web.this, R.raw.sonido);
                //Inicio la reproducción
                mediaPlayer.start();
                //Listener para liberar recursos cuando sonido termine
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release(); //Libero recursos cuando el sonido termine
                    }
                });
            }
        });
}}
