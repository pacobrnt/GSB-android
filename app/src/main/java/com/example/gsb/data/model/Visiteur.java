package com.example.gsb.data.model;

import com.google.gson.annotations.SerializedName;

public class Visiteur {

    @SerializedName("id")
    private String id;
    @SerializedName("nom")
    private String nom;
    @SerializedName("prenom")
    private String prenom;
    @SerializedName("email")
    private String email;
    @SerializedName("tel")
    private String tel;

    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTel() { return tel; }
    public String getNomComplet() { return prenom + " " + nom; }
}
