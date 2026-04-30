package com.example.gsb.data.model;

import com.google.gson.annotations.SerializedName;

public class RapportVisite {

    @SerializedName("id")
    private String id;
    @SerializedName("visiteurId")
    private String visiteurId;
    @SerializedName("medecinId")
    private String medecinId;
    @SerializedName("medecinNom")
    private String medecinNom;
    @SerializedName("dateVisite")
    private String dateVisite;
    @SerializedName("motif")
    private String motif;
    @SerializedName("bilan")
    private String bilan;

    public RapportVisite() {}

    public RapportVisite(String visiteurId, String medecinId,
                         String dateVisite, String motif, String bilan) {
        this.visiteurId = visiteurId;
        this.medecinId = medecinId;
        this.dateVisite = dateVisite;
        this.motif = motif;
        this.bilan = bilan;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getVisiteurId() { return visiteurId; }
    public void setVisiteurId(String visiteurId) { this.visiteurId = visiteurId; }

    public String getMedecinId() { return medecinId; }
    public void setMedecinId(String medecinId) { this.medecinId = medecinId; }

    public String getMedecinNom() { return medecinNom; }
    public void setMedecinNom(String medecinNom) { this.medecinNom = medecinNom; }

    public String getDateVisite() { return dateVisite; }
    public void setDateVisite(String dateVisite) { this.dateVisite = dateVisite; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    public String getBilan() { return bilan; }
    public void setBilan(String bilan) { this.bilan = bilan; }
}
