package com.elis.mvalier1.findme.model;

/*
*
 * Created by mvalier1 on 24/01/2018.

*/


public class SalleSample {

    private static final long serialVersionUID = -3009157732242241606L;

    private long id;

    private String nom;
    private String numero;
    private String capacite;
    private String equipement;
    private String telephone;
    private String aile;
    private String niveau;

    public SalleSample() {

    }

    public SalleSample(int id, String nom, String numero, String capacite, String equipement, String telephone, String aile, String niveau) {
        this.id = id;
        this.nom = nom;
        this.numero = numero;
        this.capacite = capacite;
        this.equipement = equipement;
        this.telephone = telephone;
        this.aile = aile;
        this.niveau = niveau;
    }

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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getAile() {
        return aile;
    }

    public void setAile(String aile) {
        this.aile = aile;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }



}
