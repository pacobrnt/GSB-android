package com.example.gsb.ui.medecins;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb.data.model.Medecin;
import com.example.gsb.databinding.ItemMedecinBinding;

public class MedecinAdapter extends ListAdapter<Medecin, MedecinAdapter.MedecinViewHolder> {

    public interface OnMedecinClickListener {
        void onClick(Medecin medecin);
    }

    private final OnMedecinClickListener listener;

    public MedecinAdapter(OnMedecinClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    private static final DiffUtil.ItemCallback<Medecin> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Medecin>() {
                @Override
                public boolean areItemsTheSame(@NonNull Medecin a, @NonNull Medecin b) {
                    return a.getId().equals(b.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Medecin a, @NonNull Medecin b) {
                    return a.getNom().equals(b.getNom()) && a.getPrenom().equals(b.getPrenom());
                }
            };

    @NonNull
    @Override
    public MedecinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMedecinBinding binding = ItemMedecinBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new MedecinViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MedecinViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    static class MedecinViewHolder extends RecyclerView.ViewHolder {
        private final ItemMedecinBinding binding;

        MedecinViewHolder(@NonNull ItemMedecinBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Medecin medecin, OnMedecinClickListener listener) {
            binding.tvNom.setText(medecin.getNomComplet());
            binding.tvVille.setText(medecin.getCp() + " " + medecin.getVille());
            binding.tvSpecialite.setText(medecin.getSpecialite());
            binding.getRoot().setOnClickListener(v -> listener.onClick(medecin));
        }
    }
}
