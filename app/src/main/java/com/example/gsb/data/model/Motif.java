package com.example.gsb.data.model;

import com.google.gson.annotations.SerializedName;

public class Motif {

    @SerializedName("id")
    private String id;
    @SerializedName("libelle")
    private String libelle;

    public Motif() {}

    public Motif(String id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    @Override
    public String toString() { return libelle; }
}
