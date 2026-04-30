package com.example.gsb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gsb.data.model.Visiteur;
import com.example.gsb.data.repository.VisiteurRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class VisiteurViewModel extends ViewModel {

    private final VisiteurRepository repository;

    private final MutableLiveData<Visiteur> visiteurConnecte = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> erreur = new MutableLiveData<>();
    private final MutableLiveData<Boolean> chargement = new MutableLiveData<>(false);

    @Inject
    public VisiteurViewModel(VisiteurRepository repository) {
        this.repository = repository;
    }

    public LiveData<Visiteur> getVisiteurConnecte() { return visiteurConnecte; }
    public LiveData<Boolean> getLoginSuccess() { return loginSuccess; }
    public LiveData<String> getErreur() { return erreur; }
    public LiveData<Boolean> getChargement() { return chargement; }
    public void resetErreur() { erreur.setValue(null); }

    public void login(String login, String mdp) {
        chargement.setValue(true);
        repository.login(login, mdp, new VisiteurRepository.LoginCallback() {
            @Override
            public void onSuccess(Visiteur visiteur) {
                chargement.postValue(false);
                visiteurConnecte.postValue(visiteur);
                loginSuccess.postValue(true);
            }

            @Override
            public void onError(String message) {
                chargement.postValue(false);
                erreur.postValue(message);
            }
        });
    }

}
