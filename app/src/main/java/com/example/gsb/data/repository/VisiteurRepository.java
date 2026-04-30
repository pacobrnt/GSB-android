package com.example.gsb.data.repository;

import com.example.gsb.data.model.Visiteur;
import com.example.gsb.data.network.ApiService;
import com.example.gsb.data.network.AuthResponse;
import com.example.gsb.data.network.LoginRequest;
import com.example.gsb.data.network.TokenManager;
import com.example.gsb.data.network.VisiteurResponse;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class VisiteurRepository {

    private final ApiService apiService;
    private final TokenManager tokenManager;

    @Inject
    public VisiteurRepository(ApiService apiService, TokenManager tokenManager) {
        this.apiService = apiService;
        this.tokenManager = tokenManager;
    }

    public interface LoginCallback {
        void onSuccess(Visiteur visiteur);
        void onError(String message);
    }

    public void login(String email, String password, LoginCallback callback) {
        apiService.login(new LoginRequest(email, password)).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tokenManager.saveToken(response.body().token);
                    String visiteurId = response.body().visiteur.getId();
                    apiService.getVisiteur(visiteurId).enqueue(new Callback<VisiteurResponse>() {
                        @Override
                        public void onResponse(Call<VisiteurResponse> call, Response<VisiteurResponse> r) {
                            if (r.isSuccessful() && r.body() != null && r.body().data != null) {
                                callback.onSuccess(r.body().data);
                            } else {
                                callback.onSuccess(response.body().visiteur);
                            }
                        }
                        @Override
                        public void onFailure(Call<VisiteurResponse> call, Throwable t) {
                            callback.onSuccess(response.body().visiteur);
                        }
                    });
                } else {
                    String errorMessage = "Identifiants incorrects";
                    try {
                        if (response.errorBody() != null) {
                            JSONObject json = new JSONObject(response.errorBody().string());
                            if (json.has("message")) {
                                errorMessage = json.getString("message");
                            }
                        }
                    } catch (Exception ignored) {}
                    callback.onError(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                callback.onError("Erreur réseau : " + t.getMessage());
            }
        });
    }

}
