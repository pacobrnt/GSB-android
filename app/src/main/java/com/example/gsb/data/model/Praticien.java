package com.example.gsb.data.model;

import com.google.gson.annotations.SerializedName;

public class Praticien {

    @SerializedName("_id")
    private String id;
    @SerializedName("nom")
    private String nom;
    @SerializedName("prenom")
    private String prenom;
    @SerializedName("email")
    private String email;
    @SerializedName("tel")
    private String tel;
    @SerializedName("rue")
    private String rue;
    @SerializedName("code_postal")
    private String codePostal;
    @SerializedName("ville")
    private String ville;

    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTel() { return tel; }
    public String getRue() { return rue; }
    public String getCodePostal() { return codePostal; }
    public String getVille() { return ville; }
    public String getNomComplet() { return prenom + " " + nom; }
}
