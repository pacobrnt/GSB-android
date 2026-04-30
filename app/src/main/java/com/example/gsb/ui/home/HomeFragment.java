package com.example.gsb.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gsb.R;
import com.example.gsb.databinding.FragmentHomeBinding;
import com.example.gsb.viewmodel.VisiteurViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private PraticienAdapter adapter;

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

        adapter = new PraticienAdapter(new ArrayList<>(), praticienId -> {
            vm.loadPraticien(praticienId);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_home_to_detail_praticien);
        });
        binding.rvPraticiens.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvPraticiens.setAdapter(adapter);

        vm.getVisiteurConnecte().observe(getViewLifecycleOwner(), visiteur -> {
            if (visiteur == null) return;
            binding.tvBienvenue.setText("Bonjour, " + visiteur.getNomComplet());
            binding.tvEmail.setText(visiteur.getEmail());
            binding.tvTel.setText(visiteur.getTel() != null ? visiteur.getTel() : "");
            vm.loadPortefeuille(visiteur.getId());
        });

        vm.getPortefeuille().observe(getViewLifecycleOwner(), items -> {
            if (items != null) adapter.setItems(items);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
