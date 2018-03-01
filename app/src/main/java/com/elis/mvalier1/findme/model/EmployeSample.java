package com.elis.mvalier1.findme.model;

/**
 * Created by mvalier1 on 08/02/2018.
 */

public class EmployeSample {

    private long id;

    private String nom;

    private String prenom;

    private String etage;

    private String aile;

    private String numero;

    private String telephone;

    private String service;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public String getAile() {
        return aile;
    }

    public void setAile(String aile) {
        this.aile = aile;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return nom + ' ' + prenom + ' ' +
                "\nEtage : " + etage + ' ' +
                "\nAile : " + aile + ' ' +
                "\nBureau n° : " + numero + ' ' +
                "\nTéléphone : " + telephone + ' ' +
                "\nService : " + service ;
    }


}
