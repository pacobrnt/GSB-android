package com.example.gsb.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gsb.databinding.FragmentHomeBinding;
import com.example.gsb.viewmodel.VisiteurViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        VisiteurViewModel vm = new ViewModelProvider(requireActivity()).get(VisiteurViewModel.class);

        vm.getVisiteurConnecte().observe(getViewLifecycleOwner(), visiteur -> {
            if (visiteur == null) return;
            binding.tvBienvenue.setText("Bonjour, " + visiteur.getNomComplet());
            binding.tvEmail.setText(visiteur.getEmail());
            binding.tvTel.setText(visiteur.getTel() != null ? visiteur.getTel() : "");
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
