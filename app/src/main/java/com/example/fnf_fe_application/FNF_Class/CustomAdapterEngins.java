package com.example.fnf_fe_application.FNF_Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fnf_fe_application.R;

import java.util.ArrayList;

public class CustomAdapterEngins extends RecyclerView.Adapter{
    ArrayList enginsImpliqués;
    ArrayList typeEngins;
    ArrayList immatriculationEngins;
    Context context;

    public CustomAdapterEngins(Context context, ArrayList enginsImpliqués, ArrayList typeEngins, ArrayList immatriculationEngins) {
        this.context = context;
        this.enginsImpliqués = enginsImpliqués;
        this.typeEngins = typeEngins;
        this.immatriculationEngins = immatriculationEngins;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView enginImpliqué;
        TextView typeEngin;
        TextView immatriculationEngin;

        public MyViewHolder(View itemView) {
            super(itemView);
            enginImpliqué = (TextView) itemView.findViewById(R.id.enginImpliqueLayout);
            typeEngin = (TextView) itemView.findViewById(R.id.typeEnginLayoutAdd);
            immatriculationEngin = (TextView) itemView.findViewById(R.id.immatriculationLayout);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.consulter_engins_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new CustomAdapterEngins.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TextView enginImpliqué;
        TextView typeEngin;
        TextView immatriculationEngin;

        enginImpliqué = (TextView) holder.itemView.findViewById(R.id.enginImpliqueLayout);
        typeEngin = (TextView) holder.itemView.findViewById(R.id.typeEnginLayoutAdd);
        immatriculationEngin = (TextView) holder.itemView.findViewById(R.id.immatriculationLayout);

        enginImpliqué.setText((CharSequence) enginsImpliqués.get(position));
        typeEngin.setText((CharSequence) typeEngins.get(position));
        immatriculationEngin.setText((CharSequence) immatriculationEngins.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return enginsImpliqués.size();
    }
}
