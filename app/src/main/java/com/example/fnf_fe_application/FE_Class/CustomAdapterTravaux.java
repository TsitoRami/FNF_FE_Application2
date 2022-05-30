package com.example.fnf_fe_application.FE_Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fnf_fe_application.R;

import java.util.ArrayList;

public class CustomAdapterTravaux extends RecyclerView.Adapter{

    ArrayList datesListTravaux;
    ArrayList designationListTravaux;
    ArrayList intervenantListTravaux;
    ArrayList tempsPassedListTravaux;
    Context context;

    public CustomAdapterTravaux(Context context, ArrayList datesList, ArrayList designationList, ArrayList intervenantList, ArrayList tempsPassedList){
        this.context = context;
        this.datesListTravaux = datesList;
        this.designationListTravaux = designationList;
        this.intervenantListTravaux = intervenantList;
        this.tempsPassedListTravaux = tempsPassedList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dateTravaux;
        TextView designationTravaux;
        TextView intervenantTravaux;
        TextView tempsPassedTravaux;

        public MyViewHolder(View itemView) {
            super(itemView);
            dateTravaux = (TextView) itemView.findViewById(R.id.dateTravauxTextView);
            designationTravaux = (TextView) itemView.findViewById(R.id.designationTravauxTextView);
            intervenantTravaux = (TextView) itemView.findViewById(R.id.intervenantTravauxTextView);
            tempsPassedTravaux = (TextView) itemView.findViewById(R.id.timeTravauxTextView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.consulter_travaux_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CustomAdapterTravaux.MyViewHolder vh = new CustomAdapterTravaux.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView dateTravaux;
        TextView designationTravaux;
        TextView intervenantTravaux;
        TextView tempsPassedTravaux;

        dateTravaux = (TextView) holder.itemView.findViewById(R.id.dateTravauxTextView);
        designationTravaux = (TextView) holder.itemView.findViewById(R.id.designationTravauxTextView);
        intervenantTravaux = (TextView) holder.itemView.findViewById(R.id.intervenantTravauxTextView);
        tempsPassedTravaux = (TextView) holder.itemView.findViewById(R.id.timeTravauxTextView);

        dateTravaux.setText((CharSequence) datesListTravaux.get(position));
        designationTravaux.setText((CharSequence) designationListTravaux.get(position));
        intervenantTravaux.setText((CharSequence) intervenantListTravaux.get(position));
        tempsPassedTravaux.setText((CharSequence) tempsPassedListTravaux.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return designationListTravaux.size();
    }
}
