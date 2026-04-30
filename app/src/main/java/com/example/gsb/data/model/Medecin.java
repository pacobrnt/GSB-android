package com.example.gsb.data.model;

import com.google.gson.annotations.SerializedName;

public class Medecin {

    @SerializedName("id")
    private String id;
    @SerializedName("nom")
    private String nom;
    @SerializedName("prenom")
    private String prenom;
    @SerializedName("adresse")
    private String adresse;
    @SerializedName("cp")
    private String cp;
    @SerializedName("ville")
    private String ville;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("specialite")
    private String specialite;

    public Medecin() {}

    public Medecin(String id, String nom, String prenom, String adresse,
                   String cp, String ville, String telephone, String specialite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.telephone = telephone;
        this.specialite = specialite;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getCp() { return cp; }
    public void setCp(String cp) { this.cp = cp; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    public String getNomComplet() { return "Dr " + prenom + " " + nom; }
}
