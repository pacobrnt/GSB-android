package com.example.gsb.ui.medecins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gsb.databinding.FragmentDetailMedecinBinding;
import com.example.gsb.viewmodel.MedecinViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailMedecinFragment extends Fragment {

    private FragmentDetailMedecinBinding binding;
    private MedecinViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailMedecinBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(requireActivity()).get(MedecinViewModel.class);

        vm.getMedecinSelectionne().observe(getViewLifecycleOwner(), medecin -> {
            if (medecin != null) {
                binding.tvNom.setText(medecin.getNomComplet());
                binding.tvSpecialite.setText(medecin.getSpecialite());
                binding.tvAdresse.setText(medecin.getAdresse());
                binding.tvVille.setText(medecin.getCp() + " " + medecin.getVille());
                binding.tvTelephone.setText(medecin.getTelephone());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
