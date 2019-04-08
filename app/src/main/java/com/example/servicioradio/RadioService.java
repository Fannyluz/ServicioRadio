package com.example.servicioradio;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class RadioService extends Service {



    MediaPlayer reproductor;



    String Stream ="http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1_mf_p";
    Boolean preparar = false;
    boolean started = false;


    @Override
    public void onCreate(){
        Toast.makeText(getApplicationContext(),"Servicio Creado",Toast.LENGTH_LONG).show();


           reproductor = new MediaPlayer();
           reproductor.setAudioStreamType(AudioManager.STREAM_MUSIC);
        new PlayerTask().execute(Stream);

        }

    class PlayerTask extends AsyncTask<String,Void,Boolean>{
        @Override
        protected Boolean doInBackground(String... strings){

            try
            {
                reproductor.setDataSource(strings[0]);
                reproductor.prepare();
                reproductor.start();


            }catch (IOException e){
                e.printStackTrace();
            }

            return preparar;
        }
        @Override
        protected void onPostExecute(Boolean aboolean){
            super.onPostExecute(aboolean);
        }
    }



    @Override
    public int onStartCommand(Intent intent,int flags, int startId){
        Toast.makeText(this,"Servicico Arrancando"+startId,Toast.LENGTH_LONG).show();
        reproductor.start();
        return START_STICKY;
    }


    @Override
    public void onDestroy()
    {
        Toast.makeText(this,"Servicio Detenido",Toast.LENGTH_SHORT).show();
        reproductor.stop();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    }
