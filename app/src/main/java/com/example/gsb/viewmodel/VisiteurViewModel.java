package com.example.gsb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gsb.data.model.PortefeuilleItem;
import com.example.gsb.data.model.Praticien;
import com.example.gsb.data.model.Visiteur;
import com.example.gsb.data.repository.VisiteurRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class VisiteurViewModel extends ViewModel {

    private final VisiteurRepository repository;

    private final MutableLiveData<Visiteur> visiteurConnecte = new MutableLiveData<>();
    private final MutableLiveData<List<PortefeuilleItem>> portefeuille = new MutableLiveData<>();
    private final MutableLiveData<Praticien> praticienSelectionne = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> erreur = new MutableLiveData<>();
    private final MutableLiveData<Boolean> chargement = new MutableLiveData<>(false);

    @Inject
    public VisiteurViewModel(VisiteurRepository repository) {
        this.repository = repository;
    }

    public LiveData<Visiteur> getVisiteurConnecte() { return visiteurConnecte; }
    public LiveData<List<PortefeuilleItem>> getPortefeuille() { return portefeuille; }
    public LiveData<Praticien> getPraticienSelectionne() { return praticienSelectionne; }
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

    public void loadPraticien(String praticienId) {
        repository.getPraticien(praticienId, new VisiteurRepository.PraticienCallback() {
            @Override
            public void onSuccess(Praticien praticien) {
                praticienSelectionne.postValue(praticien);
            }
            @Override
            public void onError(String message) {
                erreur.postValue(message);
            }
        });
    }

    public void loadPortefeuille(String visiteurId) {
        repository.getPortefeuille(visiteurId, new VisiteurRepository.PortefeuilleCallback() {
            @Override
            public void onSuccess(List<PortefeuilleItem> items) {
                portefeuille.postValue(items);
            }
            @Override
            public void onError(String message) {
                erreur.postValue(message);
            }
        });
    }
}
