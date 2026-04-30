package com.example.gsb.ui.praticien;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gsb.databinding.FragmentDetailPraticienBinding;
import com.example.gsb.viewmodel.VisiteurViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailPraticienFragment extends Fragment {

    private FragmentDetailPraticienBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailPraticienBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnRetour.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigateUp());

        VisiteurViewModel vm = new ViewModelProvider(requireActivity()).get(VisiteurViewModel.class);

        vm.getPraticienSelectionne().observe(getViewLifecycleOwner(), praticien -> {
            if (praticien == null) return;
            binding.tvNomPraticien.setText(praticien.getNomComplet());
            binding.tvTel.setText(praticien.getTel() != null ? praticien.getTel() : "-");
            binding.tvEmail.setText(praticien.getEmail() != null ? praticien.getEmail() : "-");
            binding.tvAdresse.setText(praticien.getRue() != null ? praticien.getRue() : "-");
            binding.tvVille.setText(
                (praticien.getCodePostal() != null ? praticien.getCodePostal() + " " : "") +
                (praticien.getVille() != null ? praticien.getVille() : "-")
            );
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
