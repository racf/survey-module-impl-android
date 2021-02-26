package com.cysout.sousystems.survey.impl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cysout.sousystems.survey.impl.R;
import com.cysout.sousystems.surveymodule.entity.Encuesta;

import java.util.List;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.CustomHolder>{
    private int layout;
    private List<Encuesta> list;
    private CustomHolder.OnItemClickListener itemClickListener;

    public SurveyAdapter (List<Encuesta> list, int layout, CustomHolder.OnItemClickListener listener){
        this.list = list;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        CustomHolder customHolder = new CustomHolder(view);
        return customHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
        holder.bind(list.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CustomHolder extends RecyclerView.ViewHolder{
        private TextView tvCardSurveyTitle;
        private TextView tvCardSurveySubTitle;
        private Button btnStartSurvey;

        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            this.tvCardSurveyTitle = itemView.findViewById(R.id.tvCardSurveyTitle);
            this.tvCardSurveySubTitle = itemView.findViewById(R.id.tvCardSurveySubTitle);
            this.btnStartSurvey = itemView.findViewById(R.id.btnStartSurvey);
        }
        public void bind(final Encuesta encuesta, final CustomHolder.OnItemClickListener listener){
            tvCardSurveyTitle.setText(encuesta.getTitulo());
            String description = encuesta.getDescripcion() == null ? "" : encuesta.getDescripcion();
            tvCardSurveySubTitle.setText(description);
            btnStartSurvey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.btnOnClick(v, encuesta, getAdapterPosition());
                }
            });
        }
        public interface OnItemClickListener{
            void onItemClick(Encuesta encuesta, int position);
            void btnOnClick(View v, Encuesta encuesta, int position);
        }

    }
}
