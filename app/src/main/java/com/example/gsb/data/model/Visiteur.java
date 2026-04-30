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
    private String login;
    @SerializedName("adresse")
    private String adresse;
    @SerializedName("cp")
    private String cp;
    @SerializedName("ville")
    private String ville;

    public Visiteur() {}

    public Visiteur(String id, String nom, String prenom, String login,
                    String adresse, String cp, String ville) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getCp() { return cp; }
    public void setCp(String cp) { this.cp = cp; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getNomComplet() { return prenom + " " + nom; }
}
