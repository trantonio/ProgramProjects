/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutions.UF6.NF2.Menus;

import java.util.Calendar;

/**
 *
 * @author gines
 */
public class TipusMissatges_Dades {
    int id;                     //Clau primaria. Es crea automàticament pel sistema i és intocable.
    String nom;                 //EMAIL, CHAT, FTP,...
    boolean actiu;              //TRUE si és un tipus actiu (que es pot fer servir)
    Calendar dataCreacio;
    Calendar dataAnulacio;      //Quan actiu passi a valdre FALSE.

    
    
    public TipusMissatges_Dades(int id, String nom, boolean actiu, Calendar dataCreacio, Calendar dataAnulacio) {
        this.id = id;
        this.nom = nom;
        this.actiu = actiu;
        this.dataCreacio = dataCreacio;
        this.dataAnulacio = dataAnulacio;
    }

    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
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

    
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    
    @Override
    public String toString() {
        return "TipusMissatges_Dades{" + "id=" + id + ", nom=" + nom + ", actiu=" + actiu + ", dataCreacio=" + dataCreacio.get(Calendar.DAY_OF_MONTH) + "-" + (dataCreacio.get(Calendar.MONTH) + 1)  + "-" + dataCreacio.get(Calendar.YEAR) + ", dataAnulacio=" + dataAnulacio.get(Calendar.DAY_OF_MONTH) + "-" + (dataAnulacio.get(Calendar.MONTH) + 1)  + "-" + dataAnulacio.get(Calendar.YEAR) + '}';
    }
    
    
    
}
