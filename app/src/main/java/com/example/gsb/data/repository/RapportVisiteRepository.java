package com.example.gsb.data.repository;

import com.example.gsb.data.model.Motif;
import com.example.gsb.data.model.RapportVisite;
import com.example.gsb.data.network.ApiService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class RapportVisiteRepository {

    private final ApiService apiService;

    @Inject
    public RapportVisiteRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public interface RapportsCallback {
        void onSuccess(List<RapportVisite> rapports);
        void onError(String message);
    }

    public interface RapportCallback {
        void onSuccess(RapportVisite rapport);
        void onError(String message);
    }

    public interface MotifsCallback {
        void onSuccess(List<Motif> motifs);
        void onError(String message);
    }

    public void getRapports(String visiteurId, RapportsCallback callback) {
        apiService.getRapports(visiteurId).enqueue(new Callback<List<RapportVisite>>() {
            @Override
            public void onResponse(Call<List<RapportVisite>> call, Response<List<RapportVisite>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Impossible de charger les rapports");
                }
            }

            @Override
            public void onFailure(Call<List<RapportVisite>> call, Throwable t) {
                callback.onError("Erreur réseau : " + t.getMessage());
            }
        });
    }

    public void createRapport(RapportVisite rapport, RapportCallback callback) {
        apiService.createRapport(rapport).enqueue(new Callback<RapportVisite>() {
            @Override
            public void onResponse(Call<RapportVisite> call, Response<RapportVisite> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Impossible de créer le rapport");
                }
            }

            @Override
            public void onFailure(Call<RapportVisite> call, Throwable t) {
                callback.onError("Erreur réseau : " + t.getMessage());
            }
        });
    }

    public void getMotifs(MotifsCallback callback) {
        apiService.getMotifs().enqueue(new Callback<List<Motif>>() {
            @Override
            public void onResponse(Call<List<Motif>> call, Response<List<Motif>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Impossible de charger les motifs");
                }
            }

            @Override
            public void onFailure(Call<List<Motif>> call, Throwable t) {
                callback.onError("Erreur réseau : " + t.getMessage());
            }
        });
    }
}
