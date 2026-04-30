package com.example.gsb.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb.R;
import com.example.gsb.data.model.PortefeuilleItem;
import com.example.gsb.data.model.Praticien;

import java.util.List;

public class PraticienAdapter extends RecyclerView.Adapter<PraticienAdapter.ViewHolder> {

    public interface OnClickListener {
        void onClick(String praticienId);
    }

    private List<PortefeuilleItem> items;
    private final OnClickListener listener;

    public PraticienAdapter(List<PortefeuilleItem> items, OnClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public void setItems(List<PortefeuilleItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_praticien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Praticien p = items.get(position).getPraticien();
        if (p == null) return;
        holder.tvNom.setText(p.getNomComplet());
        holder.tvVille.setText(p.getVille() != null ? p.getVille() : "");
        holder.itemView.setOnClickListener(v -> listener.onClick(p.getId()));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNom, tvVille;

        ViewHolder(View itemView) {
            super(itemView);
            tvNom = itemView.findViewById(R.id.tvNom);
            tvVille = itemView.findViewById(R.id.tvVille);
        }
    }
}
