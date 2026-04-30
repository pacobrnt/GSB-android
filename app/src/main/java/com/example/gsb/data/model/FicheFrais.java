package com.example.gsb.data.model;

import com.google.gson.annotations.SerializedName;

public class FicheFrais {

    @SerializedName("visiteurId")
    private String visiteurId;
    @SerializedName("mois")
    private String mois;
    @SerializedName("nbJustificatifs")
    private int nbJustificatifs;
    @SerializedName("montantValide")
    private double montantValide;
    @SerializedName("dateModif")
    private String dateModif;
    @SerializedName("etat")
    private String etat;

    public FicheFrais() {}

    public FicheFrais(String visiteurId, String mois, int nbJustificatifs,
                      double montantValide, String dateModif, String etat) {
        this.visiteurId = visiteurId;
        this.mois = mois;
        this.nbJustificatifs = nbJustificatifs;
        this.montantValide = montantValide;
        this.dateModif = dateModif;
        this.etat = etat;
    }

    public String getVisiteurId() { return visiteurId; }
    public void setVisiteurId(String visiteurId) { this.visiteurId = visiteurId; }

    public String getMois() { return mois; }
    public void setMois(String mois) { this.mois = mois; }

    public int getNbJustificatifs() { return nbJustificatifs; }
    public void setNbJustificatifs(int nbJustificatifs) { this.nbJustificatifs = nbJustificatifs; }

    public double getMontantValide() { return montantValide; }
    public void setMontantValide(double montantValide) { this.montantValide = montantValide; }

    public String getDateModif() { return dateModif; }
    public void setDateModif(String dateModif) { this.dateModif = dateModif; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }
}
