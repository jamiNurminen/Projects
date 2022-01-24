package Harjoitustyo;

import java.util.ArrayList;

import fi.jyu.mit.fxgui.Dialogs;

/**
 * @author Jami Nurminen & Olli Peltomaa
 * @version 4 Mar 2021
 *
 */
public class Ryhma {
    
    private final static int MAKSIMIKOKO = 10;
    ArrayList<Integer> sid = new ArrayList<Integer>();
    private int rid;
    private int jasenia = 0;
    private String tehtava = "";
    private String nimi = "";
    
    /**
     *  Alustaa Ryhma-olion
     */
    public Ryhma() {
        tehtava = "Ryhmälle ei ole määritelty tehtävää!";
        nimi = "Ryhmälle ei ole määritelty nimeä!";
    }
    
    /** 
     *  Alustaa ryhmän ja poistaa sille kirjatut tiedot.
     */
    public void Alusta() {
        rid = 0;
        sid = new ArrayList<Integer>();
        tehtava = "Ryhmälle ei ole määritelty tehtävää!";
        nimi = "Ryhmälle ei ole määritelty nimeä!";
    }
    
    /** Lisätään jäsen seuraavaan paikkaan jossa sid on 0;
     * @param sotilaanid sotilaan id tunnus.
     * @return Onnistuiko lisääminen
     */
    public boolean lisaaJasen(int sotilaanid) {
        boolean full = false;
        if ( jasenia == MAKSIMIKOKO ) {
            Dialogs.showMessageDialog("Ryhmä on täynnä!");
            full = true;
        }
        else {
            sid.add(sotilaanid);
            jasenia++;
        }
        return full;
    }
    
    /** poistaa sid[] taulukon kohdasta jäsenen joka vastaa sotilaan id tunnusta. 
     * @param sotilaanid sotilaan id
     */
    public void poistaJasen(int sotilaanid) {
        for (int i = 0; i < sid.size(); i++) {
            if (sid.get(i) == sotilaanid) {
                sid.set(i, 0);
                jasenia = jasenia - 1;
            }
        }
    }
    
    /**
     * Aliohjelma sotilaiden määrän saamiseksi
     * @return palauttaa sotilaiden määrän
     */
    public int getSotilaatlkm () {
        return jasenia;
    }
    
    /**
     * Ryhmän id-tunnuksen lisääminen
     * @param ryhmaid Ryhman id tunnus
     */
    public void lisaaRid(int ryhmaid) {
        rid = ryhmaid;
    }
    
    /**
     * Palauttaa ryhmän sotilaiden sid tunnukset
     * @return Palauttaa ryhmään kirjattujen sotilaiden sid tunnukset
     */
    public int[] getSotilaat() {
        int[] sotilaat = new int[sid.size()];
        for (int i = 0; i < sid.size(); i++) {
            sotilaat[i] = sid.get(i);
        }
        return sotilaat;
    }
    
    /**
     * Palauttaa ryhmän id tunnuksen
     * @return palauttaa ryhmän id tunnuksen
     */
    public int getRid() {
        return rid;
    }
    
    /**
     * Ryhmän tehtävän hakeminen
     * @return tehtävä
     */
    public String getTehtava() {
        return tehtava;
    }
    
    /** Lisää ryhmälle tehtävän
     * @param Ryhmantehtava Ryhmalle kirjattu tehtava
     */
    public void lisaaTehtava(String Ryhmantehtava) {
        tehtava = Ryhmantehtava;
    }
    
    /**
     * Palauttaa ryhmän nimen
     * @return Palauttaa ryhmälle kirjatun nimen
     */
    public String getNimi() {
        return nimi;
    }
    
    /** Lisää ryhmälle nimen
     * @param Ryhmannimi Ryhman nimi
     */
    public void lisaaNimi(String Ryhmannimi) {
        nimi = Ryhmannimi;
    }
    
    /**
     * Ryhmäluokan testipääohjelma
     * @param args ei käytössä
     * @example
     * <pre name="test">
     * #import Harjoitustyo.Ryhma;
     * Ryhma r = new Ryhma();
     * r.getNimi() === "Ryhmälle ei ole määritelty nimeä!";
     * r.getTehtava() === "Ryhmälle ei ole määritelty tehtävää!";
     * r.lisaaNimi("Rekkaryhmä");
     * r.getNimi() === "Rekkaryhmä";
     * r.lisaaTehtava("Tavaroiden ja ihmisten kuljettaminen");
     * r.getTehtava() === "Tavaroiden ja ihmisten kuljettaminen";
     * r.getRid() === 0;
     * r.getSotilaatlkm() === 0;
     * r.lisaaJasen(1) === false;
     * r.getSotilaatlkm() === 1;
     * r.poistaJasen(1);
     * r.getSotilaatlkm() === 0;
     * </pre>
     */
    public static void main(String[] args) {
        //
    }

    /**
     * Palauttaa ryhmän tiedot crc-kortilla
     * @return palauttaa ryhmän crc kortin
     */
    public String getCrc() {
        StringBuilder ryhma = new StringBuilder();
        ryhma.append(rid + "|");
        ryhma.append(nimi + "|");
        ryhma.append(tehtava + "|");
        ryhma.append("|");
        return ryhma.toString();
    }
    
    

}
