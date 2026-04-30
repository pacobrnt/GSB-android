package com.example.gsb.data.model;

import com.google.gson.annotations.SerializedName;

public class PortefeuilleItem {

    @SerializedName("praticien")
    private Praticien praticien;

    public Praticien getPraticien() { return praticien; }
}
