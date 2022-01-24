package Harjoitustyo;

/**
 * @author Jami Nurminen, Olli peltomaa
 * @version 4 Mar 2021
 *
 */
public class Ryhmat {
    
    private final static int MAKSIMIKOKO = 25;
    private Ryhma ryhma[] = new Ryhma[MAKSIMIKOKO];
    private int ryhmia;
    
    /**
     * Alustetaan ryhmät luokka
     */
    public Ryhmat() { 
        for (int i = 0; i < MAKSIMIKOKO; i++) {
            lisaaRyhma(i+1);
        }
        ryhma[0].lisaaNimi("Jopa");
        ryhma[1].lisaaNimi("Taisteluryhmä");
        ryhma[2].lisaaNimi("Lepiryhmä");
        ryhma[3].lisaaNimi("Tukiryhmä");
        ryhma[4].lisaaNimi("Pulla 5");
        ryhma[5].lisaaNimi("Kokkitaisteluryhmä");
        ryhma[6].lisaaNimi("Tukiryhmä");
        ryhma[7].lisaaNimi("Kunnossapito");
        ryhma[8].lisaaNimi("Rekkaryhmä");
        ryhma[9].lisaaNimi("Klabiryhmä");
        ryhma[10].lisaaNimi("1 Ohjr");
        ryhma[11].lisaaNimi("2 Ohjr");
        ryhma[12].lisaaNimi("3 Ohjr");
        ryhma[13].lisaaNimi("4 Ohjr");
        ryhma[14].lisaaNimi("5 Ohjr");
        ryhma[15].lisaaNimi("1 Ohjr");
        ryhma[16].lisaaNimi("2 Ohjr");
        ryhma[17].lisaaNimi("3 Ohjr");
        ryhma[18].lisaaNimi("4 Ohjr");
        ryhma[19].lisaaNimi("5 Ohjr");
        ryhma[20].lisaaNimi("1 Ohjr");
        ryhma[21].lisaaNimi("2 Ohjr");
        ryhma[22].lisaaNimi("3 Ohjr");
        ryhma[23].lisaaNimi("4 Ohjr");
        ryhma[24].lisaaNimi("5 Ohjr");
        
        ryhma[0].lisaaTehtava("Patterin johtopaikka");
        ryhma[1].lisaaTehtava("Puolustaa jaokseen kohdistuvat hyökkäykset");
        ryhma[2].lisaaTehtava("Pitää huolta lepäämisestä");
        ryhma[3].lisaaTehtava("Tukee jaoksen muita ryhmiä");
        ryhma[4].lisaaTehtava("Jaoksen yleisryhmä");
        ryhma[5].lisaaTehtava("Taistelee sekä kokkaa");
        ryhma[6].lisaaTehtava("Tukee jaosta");
        ryhma[7].lisaaTehtava("Korjaa patterin kalustoa");
        ryhma[8].lisaaTehtava("Keskittyy patterin rekkoihin");
        ryhma[9].lisaaTehtava("Pitää patterin klabitilanteen hyvänä");
        ryhma[10].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[11].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[12].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[13].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[14].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[15].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[16].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[17].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[18].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[19].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[20].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[21].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[22].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[23].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
        ryhma[24].lisaaTehtava("Toteuttaa patterin määräämät tehtävät");
    }
    
    /**
     * Haetaan ryhmän sotilaiden lukumäärä
     * @param rid ryhmän id-tunnus
     * @return ryhmän sotilaiden määrä
     */
    public int getLkm(int rid) {
        return ryhma[rid-1].getSotilaatlkm();
    }
    
    /** Luodaan uusi ryhmä
     * @param ryhmaid Ryhmän id tunnus
     */
    public void lisaaRyhma(int ryhmaid) {
        ryhma[ryhmia] = new Ryhma();
        ryhma[ryhmia].lisaaRid(ryhmaid);
        ryhmia++;
    }
    
    /** Poistetaan ryhmä ja sen jäsenten tiedot
     * @param ryhmaid Ryhmän id tunnus
     */
    public void poistaRyhma(int ryhmaid) {
        for (int i = 0; i < ryhma.length; i++) {
            if (ryhma[i].getRid() == ryhmaid) ryhma[i].Alusta();
        }
        
        ryhmia--;
    }
    
    /** Haetaan ryhmän sotilaiden id tunnukset
     * @param ryhmaid Ryhmän id
     * @return Palauttaa ryhmän sotilaiden id tunnukset
     */
    public int[] getRyhmanSotilaat(int ryhmaid) {
        for (int i = 0; i < ryhma.length; i++) {
            if(ryhma[i].getRid() == ryhmaid) return ryhma[i].getSotilaat();
        }
        return new int[]{0,0};
    }   
    
    /** Haetaan ryhmän tehtävä
     * @param ryhmaid Ryhmän id tunnus
     * @return Palauttaa ryhmän tehtävän
     */
    public String getTehtava(int ryhmaid) {
        for (int i = 0; i < ryhma.length; i++) {
            if (ryhma[i].getRid() == ryhmaid) return ryhma[i].getTehtava();
        }
        return "";
    }
    
    /** Haetaan ryhmän nimi
     * @param ryhmaid Ryhmän id tunnus
     * @return Palauttaa ryhmän nimen
     */
    public String getNimi(int ryhmaid) {
        for (int i = 0; i < ryhma.length; i++) {
            if (ryhma[i].getRid() == ryhmaid) return ryhma[i].getNimi();
        }
        return "";
    }
    
    /** Lisätään Ryhmään jäsen
     * @param ryhmaid Ryhmän id tunnus
     * @param sid Ryhmään lisättävän sotilaan id tunnus
     * @return Onnistuiko lisääminen
     */
    public boolean lisaaJasen(int ryhmaid, int sid) {
        boolean full = false;
        for (int i = 0; i < ryhma.length; i++) {
            if (ryhma[i].getRid() == ryhmaid) {
                full = ryhma[i].lisaaJasen(sid);
                
            }
        }
        return full;
    }
    
    /** Lisätään ryhmälle tehtävä
     * @param ryhmaid Ryhmän id tunnus
     * @param tehtava Ryhmän tehtävä
     */
    public void lisaaTehtava(int ryhmaid, String tehtava) {
        for (int i = 0; i < ryhma.length; i++) {
            if (ryhma[i].getRid() == ryhmaid) ryhma[i].lisaaTehtava(tehtava);
        }
    }
    
    /** Lisätään Ryhmälle nimi
     * @param ryhmaid Ryhman id tunnus
     * @param nimi Nimi joka lisätään
     */
    public void lisaaNimi(int ryhmaid, String nimi) {
        for (int i = 0; i < ryhma.length; i++) {
            if (ryhma[i].getRid() == ryhmaid) ryhma[i].lisaaNimi(nimi);
        }
        ryhma[ryhmaid].lisaaNimi(nimi);
    }

    /**
     * Palauttaa maksimilukumäärän mitä patteriin voi kirjata ryhmiä
     * @return palauttaa maksimi lukumäärän ryhmistä
     */
    public int getMaxlkm() {
        return MAKSIMIKOKO;
        
    }

    /**
     * Palauttaa halutun ryhmän nimen
     * @param rid Ryhmän id tunnut
     * @return Palauttaa ryhmän nimen
     */
    public String getRyhmanimi(int rid) {
        return ryhma[rid].getNimi();
    }

    /**
     * Palauttaa halutun ryhmän tehtävän
     * @param rid Ryhmän id tunnnus
     * @return Palauttaa ryhmän tehtävän
     */
    public String getRyhmantehtava(int rid) {
        return ryhma[rid].getTehtava();
        
    }

    /**
     * Palauttaa kaikkien ryhmien crc kortit
     * @return Palauttaa ryhmien crc kortit
     */
    public String[] getRyhmat() {
        String[] ryhmat = new String[MAKSIMIKOKO];
        for (int i = 0; i < ryhmat.length; i++) {
            ryhmat[i] = ryhma[i].getCrc();
        }
        return ryhmat;
    }

    /**
     * Palauttaa rid tunnusta vastaavan ryhmän
     * @param rid Ryhmän id tunnus
     * @return Palauttaa Ryhmän
     */
    public Ryhma getRyhma(int rid) {
        for (int i = 0; i < ryhma.length; i++) {
            if (ryhma[i].getRid() == rid) return ryhma[i];
        }
        return null;
    }
    
    
}
