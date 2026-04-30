package com.example.gsb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gsb.data.model.Motif;
import com.example.gsb.data.model.RapportVisite;
import com.example.gsb.data.repository.RapportVisiteRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RapportVisiteViewModel extends ViewModel {

    private final RapportVisiteRepository repository;

    private final MutableLiveData<List<RapportVisite>> rapports = new MutableLiveData<>();
    private final MutableLiveData<List<Motif>> motifs = new MutableLiveData<>();
    private final MutableLiveData<Boolean> rapportCree = new MutableLiveData<>();
    private final MutableLiveData<String> erreur = new MutableLiveData<>();

    @Inject
    public RapportVisiteViewModel(RapportVisiteRepository repository) {
        this.repository = repository;
        chargerMotifs();
    }

    public LiveData<List<RapportVisite>> getRapports() { return rapports; }
    public LiveData<List<Motif>> getMotifs() { return motifs; }
    public LiveData<Boolean> getRapportCree() { return rapportCree; }
    public LiveData<String> getErreur() { return erreur; }

    public void chargerRapports(String visiteurId) {
        repository.getRapports(visiteurId, new RapportVisiteRepository.RapportsCallback() {
            @Override
            public void onSuccess(List<RapportVisite> liste) {
                rapports.postValue(liste);
            }

            @Override
            public void onError(String message) {
                erreur.postValue(message);
            }
        });
    }

    public void creerRapport(RapportVisite rapport) {
        repository.createRapport(rapport, new RapportVisiteRepository.RapportCallback() {
            @Override
            public void onSuccess(RapportVisite r) {
                rapportCree.postValue(true);
            }

            @Override
            public void onError(String message) {
                erreur.postValue(message);
            }
        });
    }

    private void chargerMotifs() {
        repository.getMotifs(new RapportVisiteRepository.MotifsCallback() {
            @Override
            public void onSuccess(List<Motif> liste) {
                motifs.postValue(liste);
            }

            @Override
            public void onError(String message) {
                erreur.postValue(message);
            }
        });
    }
}
