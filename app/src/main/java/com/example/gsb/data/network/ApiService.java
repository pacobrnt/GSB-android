package com.example.gsb.data.network;

import com.example.gsb.data.model.Visiteur;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    @GET("visiteurs/{id}")
    Call<VisiteurResponse> getVisiteur(@Path("id") String id);

    @GET("portefeuille/visiteur/{visiteurId}")
    Call<PortefeuilleResponse> getPortefeuille(@Path("visiteurId") String visiteurId);

    @GET("praticiens/{id}")
    Call<PraticienResponse> getPraticien(@Path("id") String id);
}
