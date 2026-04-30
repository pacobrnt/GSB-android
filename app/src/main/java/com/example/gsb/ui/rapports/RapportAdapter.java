package com.example.gsb.ui.rapports;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb.data.model.RapportVisite;
import com.example.gsb.databinding.ItemRapportBinding;

public class RapportAdapter extends ListAdapter<RapportVisite, RapportAdapter.RapportViewHolder> {

    public RapportAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<RapportVisite> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<RapportVisite>() {
                @Override
                public boolean areItemsTheSame(@NonNull RapportVisite a, @NonNull RapportVisite b) {
                    return a.getId() != null && a.getId().equals(b.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull RapportVisite a, @NonNull RapportVisite b) {
                    return a.getDateVisite().equals(b.getDateVisite())
                            && a.getMedecinNom().equals(b.getMedecinNom());
                }
            };

    @NonNull
    @Override
    public RapportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRapportBinding binding = ItemRapportBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new RapportViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RapportViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class RapportViewHolder extends RecyclerView.ViewHolder {
        private final ItemRapportBinding binding;

        RapportViewHolder(@NonNull ItemRapportBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(RapportVisite rapport) {
            binding.tvDate.setText(rapport.getDateVisite());
            binding.tvMedecin.setText(rapport.getMedecinNom());
            binding.tvMotif.setText(rapport.getMotif());
        }
    }
}
