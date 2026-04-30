package com.example.gsb.data.repository;

import com.example.gsb.data.model.Medecin;
import com.example.gsb.data.network.ApiService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class MedecinRepository {

    private final ApiService apiService;

    @Inject
    public MedecinRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public interface MedecinsCallback {
        void onSuccess(List<Medecin> medecins);
        void onError(String message);
    }

    public interface MedecinCallback {
        void onSuccess(Medecin medecin);
        void onError(String message);
    }

    public void getMedecins(MedecinsCallback callback) {
        apiService.getMedecins().enqueue(new Callback<List<Medecin>>() {
            @Override
            public void onResponse(Call<List<Medecin>> call, Response<List<Medecin>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Impossible de charger les médecins");
                }
            }

            @Override
            public void onFailure(Call<List<Medecin>> call, Throwable t) {
                callback.onError("Erreur réseau : " + t.getMessage());
            }
        });
    }

    public void getMedecin(String id, MedecinCallback callback) {
        apiService.getMedecin(id).enqueue(new Callback<Medecin>() {
            @Override
            public void onResponse(Call<Medecin> call, Response<Medecin> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Médecin introuvable");
                }
            }

            @Override
            public void onFailure(Call<Medecin> call, Throwable t) {
                callback.onError("Erreur réseau : " + t.getMessage());
            }
        });
    }
}
