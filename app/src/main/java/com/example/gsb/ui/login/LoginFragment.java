package com.example.gsb.ui.login;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gsb.R;
import com.example.gsb.databinding.FragmentLoginBinding;
import com.example.gsb.viewmodel.VisiteurViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private VisiteurViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(requireActivity()).get(VisiteurViewModel.class);

        vm.getLoginSuccess().observe(getViewLifecycleOwner(), success -> {
            if (Boolean.TRUE.equals(success)) {
                showMessage("Connexion réussie !", true);
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_login_to_home);
            }
        });

        vm.getErreur().observe(getViewLifecycleOwner(), message -> {
            if (message != null) {
                showMessage(message, false);
                vm.resetErreur();
            }
        });

        vm.getChargement().observe(getViewLifecycleOwner(), chargement -> {
            binding.progressBar.setVisibility(Boolean.TRUE.equals(chargement) ? View.VISIBLE : View.GONE);
            binding.btnConnexion.setEnabled(!Boolean.TRUE.equals(chargement));
        });

        binding.btnConnexion.setOnClickListener(v -> {
            String email = binding.editLogin.getText().toString().trim();
            String mdp = binding.editMdp.getText().toString().trim();
            if (email.isEmpty() || mdp.isEmpty()) {
                showMessage("Veuillez remplir tous les champs", false);
                return;
            }
            binding.tvMessage.setVisibility(View.GONE);
            vm.login(email, mdp);
        });
    }

    private void showMessage(String message, boolean success) {
        binding.tvMessage.setText(message);
        binding.tvMessage.setTextColor(success ? Color.parseColor("#2E7D32") : Color.parseColor("#C62828"));
        binding.tvMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
