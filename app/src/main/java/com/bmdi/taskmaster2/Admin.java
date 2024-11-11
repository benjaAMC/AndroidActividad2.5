package com.bmdi.taskmaster2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//Importo librerias de video
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;




public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        //Configuramos el video para la reproduccion local
        VideoView videoView = findViewById(R.id.videoView);
        //Construimos una URI del video
        String videoPath = "android.resource://"+ getPackageName() + "/" + R.raw.video;
        //Convierto la cadena en un URI
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        //Agrego controles de reproducción del video
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        //Vinculamos el controlador de video para mostrar en la app
        mediaController.setAnchorView(videoView);
        videoView.start();



    }

}
