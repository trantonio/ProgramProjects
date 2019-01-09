/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutions.UF6.NF2.OODB;


import java.util.Date;
/**
 *
 * @author gmartinez
 */
public class Encarrec {
    private String nomProducte;
    private int quantitat;
    private Date data;
    
    public Encarrec(String n, int q) {
        nomProducte = n;
        quantitat = q;
        data = new Date();
    }
    public String getNom() {
        return nomProducte;
    }
    public int getQuantitat() {
        return quantitat;
    }
    public Date getData() {
        return data;
    }
    
    @Override
    public String toString() {
        return getData()+ " − " + getNom() + " (" + getQuantitat() + ")";
    }
}
