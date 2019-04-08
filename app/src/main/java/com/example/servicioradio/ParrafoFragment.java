package com.example.servicioradio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ParrafoFragment extends Fragment {

    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            updateParrafoView(args.getInt(ARG_POSITION));
            updateTituloView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            updateParrafoView(mCurrentPosition);
            updateTituloView(mCurrentPosition);
        }
    }

    public void updateTituloView(int position) {
        TextView titulo = (TextView) getActivity().findViewById(R.id.txt_titulo);
        titulo.setText(Contenido.Titulos[position]);
        mCurrentPosition = position;
    }


    public void updateParrafoView(int position) {
        TextView parrafo = (TextView) getActivity().findViewById(R.id.txt_fragmento);
        parrafo.setText(Contenido.Parrafos[position]);
        mCurrentPosition = position;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        return inflater.inflate(R.layout.fragment_parrafo, container, false);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}



