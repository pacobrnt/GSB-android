package com.example.gsb.ui.rapports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gsb.R;
import com.example.gsb.databinding.FragmentListeRapportsBinding;
import com.example.gsb.viewmodel.RapportVisiteViewModel;
import com.example.gsb.viewmodel.VisiteurViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListeRapportsFragment extends Fragment {

    private FragmentListeRapportsBinding binding;
    private RapportVisiteViewModel vm;
    private RapportAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListeRapportsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerRapports.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new RapportAdapter();
        binding.recyclerRapports.setAdapter(adapter);

        binding.fabNouveauRapport.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_listeRapports_to_createRapport));

        vm = new ViewModelProvider(requireActivity()).get(RapportVisiteViewModel.class);
        VisiteurViewModel visiteurVm = new ViewModelProvider(requireActivity()).get(VisiteurViewModel.class);

        visiteurVm.getVisiteurConnecte().observe(getViewLifecycleOwner(), visiteur -> {
            if (visiteur != null) {
                vm.chargerRapports(visiteur.getId());
            }
        });

        vm.getRapports().observe(getViewLifecycleOwner(), rapports -> {
            if (rapports != null) {
                adapter.submitList(rapports);
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
