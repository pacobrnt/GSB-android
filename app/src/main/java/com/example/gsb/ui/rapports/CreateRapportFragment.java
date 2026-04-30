package com.example.gsb.ui.rapports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gsb.data.model.Motif;
import com.example.gsb.data.model.RapportVisite;
import com.example.gsb.databinding.FragmentCreateRapportBinding;
import com.example.gsb.viewmodel.RapportVisiteViewModel;
import com.example.gsb.viewmodel.VisiteurViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CreateRapportFragment extends Fragment {

    private FragmentCreateRapportBinding binding;
    private RapportVisiteViewModel vm;
    private VisiteurViewModel visiteurVm;
    private List<Motif> listeMotifs = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateRapportBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(requireActivity()).get(RapportVisiteViewModel.class);
        visiteurVm = new ViewModelProvider(requireActivity()).get(VisiteurViewModel.class);

        vm.getMotifs().observe(getViewLifecycleOwner(), motifs -> {
            if (motifs != null) {
                listeMotifs = motifs;
                ArrayAdapter<Motif> adapter = new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        motifs);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.spinnerMotif.setAdapter(adapter);
            }
        });

        vm.getRapportCree().observe(getViewLifecycleOwner(), success -> {
            if (Boolean.TRUE.equals(success)) {
                Toast.makeText(requireContext(), "Rapport créé avec succès", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(this).popBackStack();
            }
        });

        vm.getErreur().observe(getViewLifecycleOwner(), message -> {
            if (message != null) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
            }
        });

        binding.btnEnregistrer.setOnClickListener(v -> {
            String medecinId = binding.editMedecinId.getText().toString().trim();
            String date = binding.editDate.getText().toString().trim();
            String bilan = binding.editBilan.getText().toString().trim();

            if (medecinId.isEmpty() || date.isEmpty() || bilan.isEmpty()) {
                Toast.makeText(requireContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            Motif motifSelectionne = (Motif) binding.spinnerMotif.getSelectedItem();
            String motif = motifSelectionne != null ? motifSelectionne.getLibelle() : "";

            String visiteurId = visiteurVm.getVisiteurConnecte().getValue() != null
                    ? visiteurVm.getVisiteurConnecte().getValue().getId()
                    : "";

            RapportVisite rapport = new RapportVisite(visiteurId, medecinId, date, motif, bilan);
            vm.creerRapport(rapport);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
