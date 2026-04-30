package com.example.gsb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gsb.data.model.Medecin;
import com.example.gsb.data.repository.MedecinRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MedecinViewModel extends ViewModel {

    private final MedecinRepository repository;

    private final MutableLiveData<List<Medecin>> medecins = new MutableLiveData<>();
    private final MutableLiveData<Medecin> medecinSelectionne = new MutableLiveData<>();
    private final MutableLiveData<String> erreur = new MutableLiveData<>();

    @Inject
    public MedecinViewModel(MedecinRepository repository) {
        this.repository = repository;
        chargerMedecins();
    }

    public LiveData<List<Medecin>> getMedecins() { return medecins; }
    public LiveData<Medecin> getMedecinSelectionne() { return medecinSelectionne; }
    public LiveData<String> getErreur() { return erreur; }

    public void chargerMedecins() {
        repository.getMedecins(new MedecinRepository.MedecinsCallback() {
            @Override
            public void onSuccess(List<Medecin> liste) {
                medecins.postValue(liste);
            }

            @Override
            public void onError(String message) {
                erreur.postValue(message);
            }
        });
    }

    public void chargerMedecin(String id) {
        repository.getMedecin(id, new MedecinRepository.MedecinCallback() {
            @Override
            public void onSuccess(Medecin medecin) {
                medecinSelectionne.postValue(medecin);
            }

            @Override
            public void onError(String message) {
                erreur.postValue(message);
            }
        });
    }

    public void selectionnerMedecin(Medecin medecin) {
        medecinSelectionne.setValue(medecin);
    }
}
