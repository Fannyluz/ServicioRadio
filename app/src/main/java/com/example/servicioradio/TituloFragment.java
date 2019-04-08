package com.example.servicioradio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TituloFragment extends ListFragment {
    OnTituloSelectedListener mCallBack;

    public interface OnTituloSelectedListener
    {
        void onTituloSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter <String> (getActivity(),R.layout.support_simple_spinner_dropdown_item,Contenido.Titulos));
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if(getFragmentManager().findFragmentById(R.id.fgm_parrafo) != null)
        {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }


    @Override
    public void onAttach(Context context) //comprobar que la actividad ha implementado la interfaz que se ha establecido
    {
        super.onAttach(context);

        try{
            Activity activity = (Activity) context;
            mCallBack = (OnTituloSelectedListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(getActivity().toString()+" debe implementar el metodo OnTituloSelectedListener");
        }

    }


    @Override
    public void onListItemClick (ListView listView,View view, int position, long id)
    {
        mCallBack.onTituloSelected(position);
        getListView().setItemChecked(position,true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        return inflater.inflate(R.layout.fragment_titulo,container,false);
    }
}
