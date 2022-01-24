package Harjoitustyo;

/**
 * @author Jami Nurminen, Olli Peltomaa
 * @version 4 Mar 2021
 */
public class Jaokset {
    private final static int MAKSIMIKOKO = 5;
    private Jaos jaos[] = new Jaos[MAKSIMIKOKO];
    private static int jaoksia = 0;
    
    /**
     * Alustetaan jaokset luokka
     */
    public Jaokset() { 
        
        int rid = 1;
        for (int i = 0; i < MAKSIMIKOKO; i++) {
            lisaaJaos(i+1);
            for (int y = 0; y < MAKSIMIKOKO; y++) {
                jaos[i].lisaaRyhma(rid);
                rid++;
            }
        }
        
        jaos[0].lisaaNimi("1. Johtopaikka");
        jaos[1].lisaaNimi("2. Huoltojaos");
        jaos[2].lisaaNimi("3. Ensimmäinen ohjusjaos");
        jaos[3].lisaaNimi("4. Toinen ohjusjaos");
        jaos[4].lisaaNimi("5. Kolmas ohjusjaos");
        
        jaos[0].lisaaTehtava("Johtaa patterin toimintaa");
        jaos[1].lisaaTehtava("Pitää huolta patterin toimintakunnosta");
        jaos[2].lisaaTehtava("Patterin tehtävien toteuttaminen, päätehtävänä turvata ilmatila");
        jaos[3].lisaaTehtava("Patterin tehtävien toteuttaminen, päätehtävänä turvata ilmatila");
        jaos[4].lisaaTehtava("Patterin tehtävien toteuttaminen, päätehtävänä turvata ilmatila");
    }
    
    /**
     * pääohjelma, jolla testataan luokan toimivuutta
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        //
    }
    
    /** Luodaan uusi jaos
     * @param jaosid Jaoksen id tunnus
     */
    public void lisaaJaos(int jaosid) {
        jaos[jaoksia] = new Jaos();
        jaos[jaoksia].lisaajid(jaosid);
        jaoksia++;
    }
    
    /** Poistetaan jaos ja sen tiedot
     * @param jaosid Jaoksen id tunnus
     */
    public void poistaJaos(int jaosid) {
        for (int i = 0; i < jaos.length; i++) {
            if (jaos[i].getJid() == jaosid) jaos[i].Alusta();
        }
        jaoksia--;
    }
    
    /** Haetaan jaoksen ryhmmien id tunnukset
     * @param jaosid Jaoksen id tunnus
     * @return Palauttaa jaoksen ryhmien id tunnukset
     */
    public int[] getRyhmat(int jaosid) {
        for (int i = 0; i < jaos.length; i++) {
            if(jaos[i].getJid() == jaosid) return jaos[i].getRyhmat();
        }
        return new int[]{0,0};
    }   
    
    /** Haetaan jaoksen tehtävä
     * @param jaosid Jaoksen id tunnus
     * @return Palauttaa jaoksen tehtävän
     */
    public String getTehtava(int jaosid) {
        for (int i = 0; i < jaos.length; i++) {
            if (jaos[i].getJid() == jaosid) return jaos[i].getTehtava();
        }
        return "";
    }
    
    /** Haetaan Jaoksen nimi
     * @param jaosid Jaoksen id tunnus
     * @return Palauttaa jaoksen nimen
     */
    public String getNimi(int jaosid) {
        for (int i = 0; i < jaos.length; i++) {
            if (jaos[i].getJid() == jaosid) return jaos[i].getNimi();
        }
        return "";
    }
    
    /** Lisätään Jaokseen ryhmääääää
     * @param jaosid Jaoksen id tunnus
     * @param rid Jaokseen lisättävän ryhmän id tunnus
     */
    public void lisaaRyhma(@SuppressWarnings("unused") int jaosid, int rid) {
        for (int i = 0; i < jaos.length; i++) {
            if (jaos[i].getJid() == 0) jaos[i].lisaaRyhma(rid);
        }
    }
    
    /** Lisätään jaokselle tehtävä
     * @param jaosid Jaoksen id tunnus
     * @param tehtava Jaoksen tehtävä
     */
    public void lisaaTehtava(int jaosid, String tehtava) {
        for (int i = 0; i < jaos.length; i++) {
            if (jaos[i].getJid() == jaosid) jaos[i].lisaaTehtava(tehtava);
        }
    }
    
    /** Lisätään Jaokselle nimi
     * @param jaosid Jaoksen id tunnus
     * @param nimi Nimi joka lisätään
     */
    public void lisaaNimi(int jaosid, String nimi) {
        for (int i = 0; i < jaos.length; i++) {
            if (jaos[i].getJid() == jaosid) jaos[i].lisaaNimi(nimi);
        }
        jaos[jaosid].lisaaNimi(nimi);
    }

    /**
     * Haetaan jaosten lukumäärä
     * @return palauttaa jaosten maksimikoon
     */
    public int getLkm() {
        return MAKSIMIKOKO;
    }

    /**
     * Luodaan jaosten crc-kortit
     * @return Palauttaa jaosten tiedot crc-kortin muodossa
     */
    public String[] getJaokset() {
        String[] jaokset = new String[MAKSIMIKOKO];
        for (int i = 0; i < jaokset.length; i++) {
            jaokset[i] = jaos[i].getCrc();
        }
        return jaokset;
    }
    

}
