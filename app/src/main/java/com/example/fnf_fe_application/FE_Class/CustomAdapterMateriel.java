package com.example.fnf_fe_application.FE_Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fnf_fe_application.R;

import java.util.ArrayList;

public class CustomAdapterMateriel extends RecyclerView.Adapter{

    ArrayList datesList;
    ArrayList designationList;
    ArrayList quantityList;
    ArrayList obsList;
    Context context;

    public CustomAdapterMateriel(Context context, ArrayList datesList, ArrayList designationList, ArrayList quantityList, ArrayList obsList){
        this.context = context;
        this.datesList = datesList;
        this.designationList = designationList;
        this.quantityList = quantityList;
        this.obsList = obsList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView dateMateriel;
        TextView designationMateriel;
        TextView quantityMateriel;
        TextView obsMateriel;

        public MyViewHolder(View itemView) {
            super(itemView);
            dateMateriel = (TextView) itemView.findViewById(R.id.dateMaterielTextView);
            designationMateriel = (TextView) itemView.findViewById(R.id.designationMaterielTextView);
            quantityMateriel = (TextView) itemView.findViewById(R.id.quantityMaterielTextView);
            obsMateriel = (TextView) itemView.findViewById(R.id.obsMaterielTextView);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.consulter_materiel_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CustomAdapterMateriel.MyViewHolder vh = new CustomAdapterMateriel.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TextView dateMateriel;
        TextView designationMateriel;
        TextView quantityMateriel;
        TextView obsMateriel;

        dateMateriel = (TextView) holder.itemView.findViewById(R.id.dateMaterielTextView);
        designationMateriel = (TextView) holder.itemView.findViewById(R.id.designationMaterielTextView);
        quantityMateriel = (TextView) holder.itemView.findViewById(R.id.quantityMaterielTextView);
        obsMateriel = (TextView) holder.itemView.findViewById(R.id.obsMaterielTextView);

        dateMateriel.setText((CharSequence) datesList.get(position));
        designationMateriel.setText((CharSequence) designationList.get(position));
        quantityMateriel.setText((CharSequence) quantityList.get(position));
        obsMateriel.setText((CharSequence) obsList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return designationList.size();
    }
}
