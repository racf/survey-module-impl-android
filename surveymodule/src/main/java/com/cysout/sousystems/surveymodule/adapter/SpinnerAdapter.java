package com.cysout.sousystems.surveymodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.entity.Respuesta;

public class SpinnerAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<Respuesta> items;
    private final int mResource;

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return createItemView(position, convertView, parent);
    }
    
    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);
        TextView tvTitleSpinner = view.findViewById(R.id.tvTitleSpinner);
        Respuesta respuesta = items.get(position);
        tvTitleSpinner.setText(respuesta.getTexto());
        tvTitleSpinner.setTag(respuesta);
        return view;
    }
}
