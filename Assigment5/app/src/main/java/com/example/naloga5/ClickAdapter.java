package com.example.naloga5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClickAdapter extends RecyclerView.Adapter<ClickAdapter.ExampleClickViewHolder> {

    private ArrayList<OsebaObj> mCustomObjects;
    private OnEntryClickListener mOnEntryClickListener;

    public ClickAdapter(ArrayList<OsebaObj> arrayList) {
        mCustomObjects = arrayList;
    }

    @Override
    public int getItemCount() {
        return mCustomObjects.size();
    }

    @Override
    public ExampleClickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.osebe_list, parent, false);
        return new ExampleClickViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExampleClickViewHolder holder, int position) {
        OsebaObj object = mCustomObjects.get(position);

        String ime = object.getIme();
        String priimek = object.getPriimek();
        String datumRoj = object.getDatumRoj();
        String spol = object.getSpol();

        holder.ime.setText(ime);
        holder.priimek.setText(priimek);
//        holder.datumRoj.setText(datumRoj);
//        holder.spol.setText(spol);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }

    // convenience method for getting data at click position
    OsebaObj getItem(int id) {
        return mCustomObjects.get(id);
    }

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public class ExampleClickViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView ime, priimek, datumRoj, spol;

        ExampleClickViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ime = (TextView) itemView.findViewById(R.id.imeList);
            priimek = (TextView) itemView.findViewById(R.id.priimekList);
//            datumRoj = (TextView) itemView.findViewById(R.id.datumRojList);
//            spol = (TextView) itemView.findViewById(R.id.spolList);
        }

        @Override
        public void onClick(View v) {
            // The user may not set a click listener for list items, in which case our listener
            // will be null, so we need to check for this
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
                System.out.println("test");
            }
        }
    }

}