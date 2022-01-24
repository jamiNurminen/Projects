package Harjoitustyo;

/**
 * @author Jami Nurminen & Olli Peltomaa
 * @version 4.3.2021
 *
 */
public class Jasenet {
    private final static int MAKSIMIKOKO = 250;
    private Jasen jasen[] = new Jasen[MAKSIMIKOKO];
    private int jasenia; 
    
    
    /**
     * Alustetaan jäsenet-luokka
     */
    public Jasenet() {
        jasenia = 0;
        for (int i = 0; i < MAKSIMIKOKO; i++) {
            jasen[i] = new Jasen();
        }
    }
    
    /**
     * Haetaan kaikkien sotilaiden määrä
     * @return palauttaa jasenten lukumäärä
     */
    public int getJasentenlkm() {
        return jasenia;
    }
    
    /**
     * Lisätään sotilaan ryhmän ryhmä-id
     * @param sid Sotilaan id
     * @param rid Ryhmän id
     */
    public void lisaaRid(int sid, int rid) {
        haeJasen(sid).lisaaRid(rid);
    }
    
    /**
     * Lisätään sotilaan sotilasarvo
     * @param sid Sotilaan id
     * @param arvo Arvo
     */
    public void lisaaArvo (int sid, String arvo) {
        haeJasen(sid).lisaaArvo(arvo);
    }
    
    /**
     * Lisätään sotilaan aseen numero
     * @param sid Sotilaan id
     * @param asenro Aseen numero
     */
    public void lisaaAseNro(int sid, int asenro) {
        haeJasen(sid).lisaaAseNro(asenro);
       
    }
    
    /**
     * Lisätään sotilaan tehtävä
     * @param sid Sotilaan id
     * @param tehtava Tehtävä
     */
    public void lisaaTehtava (int sid, String tehtava) {
        haeJasen(sid).lisaaTehtava(tehtava);
    }
    
    /**
     * Lisätään sotilaan syntymäaika
     * @param sid Sotilaan id
     * @param pv Päivä
     * @param kk Kuukausi
     * @param vv Vuosi
     */
    public void lisaaSyntyma(int sid, int pv, int kk, int vv) {
        haeJasen(sid).lisaaSyntymaAika(pv, kk, vv);
    }
    
    /**
     * Lisätään sotilaan nimi
     * @param sid Sotilaan id
     * @param etunimi Etunimi
     * @param sukunimi Sukunimi
     */
    public void lisaaNimi(int sid, String etunimi, String sukunimi) {
        haeJasen(sid).lisaaNimi(etunimi, sukunimi);
    }
    
    /**
     * Lisätään sotilaalle lisätietoa
     * @param sid Sotilaan id
     * @param lisatietoa Lisätieto
     */
    public void lisaaLisatietoa (int sid, String lisatietoa) {
        haeJasen(sid).lisaaLisatieto(lisatietoa);
    }
    
    /**
     * Etsitään onko samaa sotilasta, kuin parametrinä annettu sid
     * @param sid sotilaan id
     * @return sotilas
     */
    public Jasen haeJasen(int sid) {
        for (int i = 0; i < jasenia; i++) {
            if (jasen[i].getSid() == sid) return jasen[i];
        }
        return new Jasen();
    }
    
    /**
     * Ryhmä id:n hakeminen
     * @param sid sotilaan id
     * @return sen ryhmän ryhmä-id, johon sotilas kuuluu
     */
    public int getRid(int sid) {
        @SuppressWarnings("hiding")
        Jasen jasen = haeJasen(sid);
        return jasen.getRid();
    }
    
    
    /**
     * Luodaan sotilas
     * @param rid Ryhmän id
     * @param asenumero Aseen numero
     * @param arvo Arvo
     * @param etunimi Etunimi
     * @param sukunimi Sukunimi
     * @param pv Päivämäärä
     * @param kk Kuukausi
     * @param vv Vuosi
     * @param tehtava Tehtävä
     * @param lisatieto Lisätietoa
     * @return Palauttaa sotilasid
     */
    public int lisaaJasen(int rid, int asenumero, String arvo, String etunimi, String sukunimi, int pv, int kk, int vv, String tehtava, String lisatieto) {
        jasen[jasenia] = new Jasen();
        int sotilasid = rekisteroi(jasen[jasenia]);
        jasen[jasenia].lisaaRid(rid);
        jasen[jasenia].lisaaArvo(arvo);
        jasen[jasenia].lisaaNimi(etunimi, sukunimi);
        jasen[jasenia].lisaaAseNro(asenumero);
        jasen[jasenia].lisaaSyntymaAika(pv, kk, vv);
        jasen[jasenia].lisaaTehtava(tehtava);
        jasen[jasenia].lisaaLisatieto(lisatieto);
        jasenia++;
        return sotilasid;
    }
    
    /**
     * Sotilaan rekisteröinti
     * @param jasen jäsen joka rekisteröidään
     * @return sotilaan id tunnus
     */
    @SuppressWarnings("hiding")
    public int rekisteroi(Jasen jasen) {
        int sotilasid = Randomsid();
        boolean kaytossa = tarkista(sotilasid);
        while(kaytossa) {
            sotilasid = Randomsid();
        }
        jasen.rekisteroi(sotilasid);
        return sotilasid;
    }
    
    /**
     * Tarkistaa onko samaa sotilas-id:tä jo olemassa
     * @param id
     * @return onko olemassa vai ei
     */
    private boolean tarkista(int id) {
        for (int i = 0; i < jasen.length; i++) {
            if (jasen[i].getSid() == id) return true;
        }
        return false;
    }
    
    /**
     * Luodaan sotilaalle sattumanvarainen id
     * @return satunnaisen sotilas id:n
     */
    private int Randomsid() {
        return 1 + (int)(Math.random() * ((999 - 1) +1));
    }
    
    /**
     * Poistetaan sotilas
     * @param sotilasId sotilaan id
     */
    public void poistaJasen(int sotilasId) {
        for (int i = 0; i < jasen.length; i++) {
            if (jasen[i].getSid() == sotilasId) jasen[i].Alusta();
        }
        jasenia--;
    }
    
    /**
     * aliohjelma jäsenten hakemiseen
     * @return kaikki jäsenet
     */
    public Jasen[] getJasenet() {
        return jasen;
    }
    
    /**
     * Jäsenetluokan testipääohjelma
     * @param args ei käytössä
     * @example
     * <pre name="test">
     * #import Harjoitustyo.Jasenet;
     * Jasenet j = new Jasenet();
     * j.getJasentenlkm() === 0;
     * </pre>
     */
    public static void main(String[] args) {
      //
    }

    /**
     * Alustaa kaikki jäsenet
     */
    public void alusta() {
        for(int i = 0; i < MAKSIMIKOKO; i++) {
            jasen[i].Alusta();
        }
        
    }

}
