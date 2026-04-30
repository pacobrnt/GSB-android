package com.example.gsb.ui.frais;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gsb.databinding.FragmentListeFraisBinding;
import com.example.gsb.viewmodel.FicheFraisViewModel;
import com.example.gsb.viewmodel.VisiteurViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListeFraisFragment extends Fragment {

    private FragmentListeFraisBinding binding;
    private FicheFraisViewModel vm;
    private FicheFraisAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListeFraisBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerFrais.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new FicheFraisAdapter();
        binding.recyclerFrais.setAdapter(adapter);

        vm = new ViewModelProvider(requireActivity()).get(FicheFraisViewModel.class);
        VisiteurViewModel visiteurVm = new ViewModelProvider(requireActivity()).get(VisiteurViewModel.class);

        visiteurVm.getVisiteurConnecte().observe(getViewLifecycleOwner(), visiteur -> {
            if (visiteur != null) {
                vm.chargerFiches(visiteur.getId());
            }
        });

        vm.getFiches().observe(getViewLifecycleOwner(), fiches -> {
            if (fiches != null) {
                adapter.submitList(fiches);
            }
        });

        vm.getErreur().observe(getViewLifecycleOwner(), message -> {
            if (message != null) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
