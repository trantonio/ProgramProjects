/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutions.UF6.NF2.Menus;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author gines
 */
public class Usuaris_Dades {
    int id;                     //Clau primaria. Es crea automàticament pel sistema i és intocable.
    String nom;
    String cognoms;
    String direccio;
    boolean actiu;              //TRUE si és un usuari que té permís per accedir al server.
    Calendar dataCreacio;
    Calendar dataAnulacio;      //Quan actiu passi a valdre FALSE.
    Calendar dataUltimAcces;
    ArrayList<Integer> contactes;   //Conté una llista de a tots els usuaris (ID's) als quals els ha enviat alguna cosa.
    
    
    
    public Usuaris_Dades(int id, String nom, String cognoms, String direccio, boolean actiu, Calendar dataCreacio, Calendar dataAnulacio, Calendar dataUltimAcces, ArrayList<Integer> contactes) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.direccio = direccio;
        this.actiu = actiu;
        this.dataCreacio = dataCreacio;
        this.dataAnulacio = dataAnulacio;
        this.dataUltimAcces = dataUltimAcces;
        this.contactes = contactes;
    }

    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public String getDireccio() {
        return direccio;
    }

    public boolean isActiu() {
        return actiu;
    }

    public Calendar getDataCreacio() {
        return dataCreacio;
    }

    public Calendar getDataAnulacio() {
        return dataAnulacio;
    }

    public Calendar getDataUltimAcces() {
        return dataUltimAcces;
    }

    public ArrayList<Integer> getContactes() {
        return contactes;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }

    public void setDataCreacio(Calendar dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public void setDataAnulacio(Calendar dataAnulacio) {
        this.dataAnulacio = dataAnulacio;
    }

    public void setDataUltimAcces(Calendar dataUltimAcces) {
        this.dataUltimAcces = dataUltimAcces;
    }

    public void setContactes(ArrayList<Integer> contactes) {
        this.contactes = contactes;
    }

    
    @Override
    public String toString() {
        return  "\n       {id=" + id + 
                "\n        nom=" + nom + 
                "\n        cognoms=" + cognoms + 
                "\n        direccio=" + direccio + 
                "\n        actiu=" + actiu + 
                "\n        dataCreacio=" + dataCreacio.get(Calendar.DAY_OF_MONTH) + "-" + (dataCreacio.get(Calendar.MONTH) + 1)  + "-" + dataCreacio.get(Calendar.YEAR) + 
                "\n        dataAnulacio=" + dataAnulacio.get(Calendar.DAY_OF_MONTH) + "-" + (dataAnulacio.get(Calendar.MONTH) + 1)  + "-" + dataAnulacio.get(Calendar.YEAR) + 
                "\n        dataUltimAcces=" + dataUltimAcces.get(Calendar.DAY_OF_MONTH) + "-" + (dataUltimAcces.get(Calendar.MONTH) + 1)  + "-" + dataUltimAcces.get(Calendar.YEAR) +
                "\n        contactes=" + contactes + '}';
    }
    
    
}
