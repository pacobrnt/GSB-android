package com.example.gsb.data.network;

import com.example.gsb.data.model.FicheFrais;
import com.example.gsb.data.model.Medecin;
import com.example.gsb.data.model.Motif;
import com.example.gsb.data.model.RapportVisite;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    // Auth — /api/auth/login et /api/auth/signup
    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    // Praticiens (médecins) — /api/praticiens
    @GET("praticiens")
    Call<List<Medecin>> getMedecins();

    @GET("praticiens/{id}")
    Call<Medecin> getMedecin(@Path("id") String id);

    // Visites (rapports) — /api/visites
    @GET("visites/visiteur/{visiteurId}")
    Call<List<RapportVisite>> getRapports(@Path("visiteurId") String visiteurId);

    @POST("visites")
    Call<RapportVisite> createRapport(@Body RapportVisite rapport);

    // Motifs — /api/motifs
    @GET("motifs")
    Call<List<Motif>> getMotifs();

    // Fiches frais (non disponible dans l'API actuelle)
    @GET("fiches-frais")
    Call<List<FicheFrais>> getFichesFrais(@Query("visiteurId") String visiteurId);
}
