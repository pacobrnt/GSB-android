package com.example.gsb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gsb.data.model.FicheFrais;
import com.example.gsb.data.repository.FicheFraisRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FicheFraisViewModel extends ViewModel {

    private final FicheFraisRepository repository;

    private final MutableLiveData<List<FicheFrais>> fiches = new MutableLiveData<>();
    private final MutableLiveData<String> erreur = new MutableLiveData<>();

    @Inject
    public FicheFraisViewModel(FicheFraisRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<FicheFrais>> getFiches() { return fiches; }
    public LiveData<String> getErreur() { return erreur; }

    public void chargerFiches(String visiteurId) {
        repository.getFichesFrais(visiteurId, new FicheFraisRepository.FichesCallback() {
            @Override
            public void onSuccess(List<FicheFrais> liste) {
                fiches.postValue(liste);
            }

            @Override
            public void onError(String message) {
                erreur.postValue(message);
            }
        });
    }
}
