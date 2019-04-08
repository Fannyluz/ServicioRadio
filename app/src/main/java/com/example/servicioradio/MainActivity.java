package com.example.servicioradio;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements TituloFragment.OnTituloSelectedListener {
    Button play;
    Button detener;
    MediaPlayer mediaPlayer;


    String Stream = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1_mf_p";
    Boolean preparar = false;
    boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(R.id.fragment_container) != null) {

            //Provenga la colision de fragments
            if (savedInstanceState != null) {
                return;
            }
            //Crear el nuevo Fragmento
            TituloFragment tituloFragment = new TituloFragment();

            //Agregar extras si existen
            tituloFragment.setArguments(getIntent().getExtras());

            //Lanzar la vista del fragmento
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, tituloFragment).commit();



        }
        play = (Button) findViewById(R.id.btn_play);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, RadioService.class));
            }
        });


        detener = (Button) findViewById(R.id.btn_detener);

        detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, RadioService.class));
            }
        });


    }


    @Override
    public void onTituloSelected(int position) {
        ParrafoFragment parrafoFragment = (ParrafoFragment) getSupportFragmentManager().findFragmentById(R.id.fgm_parrafo);

        if(parrafoFragment != null)
        {
            parrafoFragment.updateParrafoView(position);


        }
        else
        {
            ParrafoFragment fragmentoNuevo = new ParrafoFragment();
            Bundle args = new Bundle();
            args.putInt(ParrafoFragment.ARG_POSITION,position);
            fragmentoNuevo.setArguments(args);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fragment_container,fragmentoNuevo);

            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.commit();


        }
    }
}
