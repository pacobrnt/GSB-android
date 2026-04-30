package com.example.gsb.data.repository;

import com.example.gsb.data.model.FicheFrais;
import com.example.gsb.data.network.ApiService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class FicheFraisRepository {

    private final ApiService apiService;

    @Inject
    public FicheFraisRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public interface FichesCallback {
        void onSuccess(List<FicheFrais> fiches);
        void onError(String message);
    }

    public void getFichesFrais(String visiteurId, FichesCallback callback) {
        apiService.getFichesFrais(visiteurId).enqueue(new Callback<List<FicheFrais>>() {
            @Override
            public void onResponse(Call<List<FicheFrais>> call, Response<List<FicheFrais>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Impossible de charger les fiches de frais");
                }
            }

            @Override
            public void onFailure(Call<List<FicheFrais>> call, Throwable t) {
                callback.onError("Erreur réseau : " + t.getMessage());
            }
        });
    }
}
