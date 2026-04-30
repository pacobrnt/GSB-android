package com.example.gsb.ui.frais;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb.data.model.FicheFrais;
import com.example.gsb.databinding.ItemFraisBinding;

public class FicheFraisAdapter extends ListAdapter<FicheFrais, FicheFraisAdapter.FraisViewHolder> {

    public FicheFraisAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<FicheFrais> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<FicheFrais>() {
                @Override
                public boolean areItemsTheSame(@NonNull FicheFrais a, @NonNull FicheFrais b) {
                    return a.getMois().equals(b.getMois())
                            && a.getVisiteurId().equals(b.getVisiteurId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull FicheFrais a, @NonNull FicheFrais b) {
                    return a.getMontantValide() == b.getMontantValide()
                            && a.getEtat().equals(b.getEtat());
                }
            };

    @NonNull
    @Override
    public FraisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFraisBinding binding = ItemFraisBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new FraisViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FraisViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class FraisViewHolder extends RecyclerView.ViewHolder {
        private final ItemFraisBinding binding;

        FraisViewHolder(@NonNull ItemFraisBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(FicheFrais fiche) {
            binding.tvMois.setText(fiche.getMois());
            binding.tvMontant.setText(String.format("%.2f €", fiche.getMontantValide()));
            binding.tvEtat.setText(fiche.getEtat());
        }
    }
}
