package com.example.fnf_fe_application.FNF_Class;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fnf_fe_application.R;

import java.util.ArrayList;

public class CustomAdapterPersonnes extends RecyclerView.Adapter{
    ArrayList<String> personnesNoms;
    ArrayList<String> organisationNoms;
    Context context;

    public CustomAdapterPersonnes(Context context, ArrayList personnesNom, ArrayList organisationNoms) {
        this.context = context;
        this.personnesNoms = personnesNom;
        this.organisationNoms = organisationNoms;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomPersonne;
        TextView nomOrganisation;

        public MyViewHolder(View itemView) {
            super(itemView);
            nomPersonne = (TextView) itemView.findViewById(R.id.nomPersonneLayout);
            nomOrganisation = (TextView) itemView.findViewById(R.id.nomOrganisationLayout);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.consulter_personne_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        TextView nomPersonne;
        TextView nomOrganisation;
        nomPersonne = (TextView) holder.itemView.findViewById(R.id.nomPersonneLayout);
        nomOrganisation = (TextView) holder.itemView.findViewById(R.id.nomOrganisationLayout);

        nomPersonne.setText((CharSequence) personnesNoms.get(position));
        nomOrganisation.setText((CharSequence) organisationNoms.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return personnesNoms.size();
    }


}
