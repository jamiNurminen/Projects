package Harjoitustyo;

/**
 * @author Jami Nurminen & Olli Peltomaa
 * @version 4 Mar 2021
 *
 */
public class Jaos {
    
    private int jid;
    private final static int MAKSIMIKOKO = 5;
    private int rid[] = new int[MAKSIMIKOKO];
    private int ryhmia = 0;
    private String tehtava;
    private String nimi;
    
    /**
     *  Alustaa jaos-olion
     */
    public Jaos() {
        jid = 0;
        for (int i = 0; i < MAKSIMIKOKO; i ++) {
            rid[i] = 0;
        }
        tehtava = "Jaokselle ei ole määritelty tehtävää!";
        nimi = "Jaokselle ei ole määritelty nimeä!";
    }
    
    /**
     *  Alustaa jaoksen ja poistaa sille kirjatut tiedot.
     */
    public void Alusta() {
        jid = 0;
        for (int i = 0; i < MAKSIMIKOKO; i ++) {
            rid[i] = 0;
        }
        tehtava = "Jaokselle ei ole määritelty tehtävää!";
        nimi = "Jaokselle ei ole määritelty nimeä!";
    }
    
    /** Lisää jaokseen ryhmän
     * @param ryhmanid Ryhmän id tunnus
     */
    public void lisaaRyhma(int ryhmanid) {
        rid[ryhmia] = ryhmanid;
        ryhmia++;
    }
    
    /** Poistaa ryhmän jaoksesta
     * @param ryhmanid Ryhmän id tunnus
     */
    public void poistaRyhma(int ryhmanid) {
        for (int i = 0; i < rid.length; i++) {
            if (rid[i] == ryhmanid) {
                rid[i] = 0;
                ryhmia = ryhmia - 1;
            }
        }
    }
    
    /**
     * Lisätään jaokselle id tunnus
     * @param jaoksenid Jaoksen id tunnus
     */
    public void lisaajid(int jaoksenid) {
        jid = jaoksenid;
    }
    
    /**
     * Haetaan jaokselle kirjattujen ryhmien id tunnukset
     * @return Palauttaa jaoksen ryhmien Ryhmä id tunnukset
     */
    public int[] getRyhmat() {
        return rid;
    }
    
    /**
     * palautetaan jaoksen id tunnus
     * @return palautta Jaoksen id tunnuksen
     */
    public int getJid() {
        return jid;
    }
    
    /** 
     * Palautetaan jaokselle kirjattu tehtävä
     * @return Jaoksen tehtävä
     */
    public String getTehtava() {
        return tehtava;
    }
    
    /** Lisää jaokselle tehtävän
     * @param Jaoksentehtava Jaoksen tehtävä
     */
    public void lisaaTehtava(String Jaoksentehtava) {
        tehtava = Jaoksentehtava;
    }
    
    /**
     * Palautetaan jaokselle kirjattu nimi
     * @return Palauttaa jaoksen nimen
     */
    public String getNimi() {
        return nimi;
    }
    
    /** Lisää jaokselle nimen
     * @param Jaoksennimi Jaoksen nimi
     */
    public void lisaaNimi(String Jaoksennimi) {
        nimi = Jaoksennimi;
    }
    
    /**
     * Metodi, jolla nähdään jaosten ryhmien määrä
     * @return ryhmien määrä
     */
    public int getRyhmaLkm() {
        return ryhmia;
    }
    
    /**
     * Jaosluokan testipääohjelma
     * @param args ei käytössä
     * @example
     * <pre name="test">
     * #import Harjoitustyo.Jaos;
     * Jaos j = new Jaos();
     * j.getNimi() === "Jaokselle ei ole määritelty nimeä!";
     * j.getTehtava() === "Jaokselle ei ole määritelty tehtävää!";
     * j.lisaaNimi("Pulla5");
     * j.getNimi() === "Pulla5";
     * j.lisaaTehtava("Rötvääminen");
     * j.getTehtava() === "Rötvääminen";
     * j.getJid() === 0;
     * j.lisaajid(5);
     * j.getJid() === 5;
     * j.lisaaRyhma(1);
     * j.getRyhmaLkm() === 1;
     * j.poistaRyhma(1);
     * j.getRyhmaLkm() === 0;
     * </pre>
     */
    public static void main(String[] args) {
        Jaos perkele = new Jaos();
        perkele.lisaaRyhma(50);
        perkele.lisaaRyhma(51);
        perkele.lisaaRyhma(52);
        perkele.lisaaRyhma(53);
        perkele.lisaaNimi("Hyväjaos");
        perkele.lisaaTehtava("Voittaa kaikki");
        perkele.lisaajid(12);
        System.out.println(perkele.getCrc());
    }

    /**
     * Luodaan ja palautetaan jaoksen crc-kortti
     * @return Palauttaa jaoksen tiedot crc-kortin muodossa
     */
    public String getCrc() {
        StringBuilder jaos = new StringBuilder();
        jaos.append(jid + "|");
        jaos.append(nimi + "|");
        jaos.append(tehtava + "|");
        for (int i = 0; i < rid.length; i++) {
            jaos.append(rid[i] + " ");
        }
        jaos.append("|");
        return jaos.toString();
    }
    
  
}
