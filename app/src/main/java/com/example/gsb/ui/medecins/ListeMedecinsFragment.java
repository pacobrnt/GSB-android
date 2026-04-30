package com.example.gsb.ui.medecins;

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
import com.example.gsb.databinding.FragmentListeMedecinsBinding;
import com.example.gsb.viewmodel.MedecinViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListeMedecinsFragment extends Fragment {

    private FragmentListeMedecinsBinding binding;
    private MedecinViewModel vm;
    private MedecinAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListeMedecinsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerMedecins.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new MedecinAdapter(medecin -> {
            vm.selectionnerMedecin(medecin);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_listeMedecins_to_detailMedecin);
        });
        binding.recyclerMedecins.setAdapter(adapter);

        vm = new ViewModelProvider(requireActivity()).get(MedecinViewModel.class);

        vm.getMedecins().observe(getViewLifecycleOwner(), medecins -> {
            if (medecins != null) {
                adapter.submitList(medecins);
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
