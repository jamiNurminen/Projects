package Harjoitustyo;

/**
 * @author Jami Nurminen & Olli Peltomaa
 * @version 4 Mar 2021
 *  Jasen luokka tallentaa ja käsittelee Jasen-olioiden tietoja.
 */
public class Jasen {
    
    private int rid;
    private int sid;
    private int asenumero;
    private String arvo;
    private String etunimi;
    private String sukunimi;
    private int syntymaaikapv = 0;
    private int syntymaaikakk = 0;
    private int syntymaaikavv = 0;
    private String tehtava = "";
    private String lisatieto = "";
    
    
    
    /**
     *  Jasen-olion alustus.
     */
    public Jasen() {
        rid = 0;
        asenumero = 0;
        arvo = "";
        etunimi = "";
        sukunimi = "";
        syntymaaikapv = 0;
        syntymaaikakk = 0;
        syntymaaikavv = 0;
        tehtava = "Sotilaan tehtävä puuttuu!";
        lisatieto = "Sotilaalle ei ole kirjattu lisätietoa.";
        
    }
    
    /**
     * Jäsenluokan testipääohjelma
     * @param args ei käytössä
     * @example
     * <pre name="test">
     * #import Harjoitustyo.Jasen;
     * Jasen lohi = new Jasen();
     * lohi.getRid() === 0;
     * lohi.getSid() === 0;
     * lohi.getEtuNimi() === "";
     * lohi.getSukuNimi() === "";
     * lohi.getSyntymaAikaPv() === 0;
     * lohi.getSyntymaAikaKk() === 0;
     * lohi.getSyntymaAikaVv() === 0;
     * lohi.lisaaNimi("Eetu", "Lohi");
     * lohi.getEtuNimi() === "Eetu";
     * lohi.getSukuNimi() === "Lohi";
     * lohi.lisaaTehtava("Runnutus");
     * lohi.getTehtava() === "Runnutus";
     * lohi.lisaaSyntymaAika(1,7,1600);
     * lohi.getSyntymaAikaPv() === 1;
     * lohi.getSyntymaAikaKk() === 7;
     * lohi.getSyntymaAikaVv() === 1600;
     * lohi.lisaaAseNro(69420666);
     * lohi.getAseenNumero() === 69420666;
     * lohi.lisaaLisatieto("Gonaukko");
     * lohi.getLisatieto() === "Gonaukko";
     * lohi.lisaaArvo("Mosa");
     * lohi.getArvo() === "Mosa"; 
     * </pre>
     */
    public static void main(String[] args) {
        //
    }
    
    /**
     * antaa sotilaalle id-tunnuksen
     * @param sotilasid seuraava id numero
     */
    public void rekisteroi(int sotilasid) {
        sid = sotilasid;
    }
    
    
    /**
     * Lisätään sotilaalle ryhmän id-tunnus
     * @param ryhmaId ryhmän id-tunnus
     */
    public void lisaaRid(int ryhmaId) {
        rid = ryhmaId;
    }
    
    /**
     * Lisätään sotilaan syntymäaika
     * @param pv päivä
     * @param kk kuukausi
     * @param v vuosi
     */
    public void lisaaSyntymaAika(int pv, int kk, int v) {
        syntymaaikapv = pv;
        syntymaaikakk = kk;
        syntymaaikavv = v;
    }
    
    /**
     * Lisätään sotilaan sotilasarvo
     * @param a arvo
     */
    public void lisaaArvo(String a) {
        arvo = a;
    }
    
    /**
     * Lisätään sotilaan aseen numero
     * @param n numero
     */
    public void lisaaAseNro(int n) {
        asenumero = n;
    }
    
    /**
     * Lisätään sotilaan tehtävä
     * @param t tehtävä
     */
    public void lisaaTehtava(String t) {
        tehtava = t;
    }
    
    /**
     * Lisätään sotilaalle lisätiedot
     * @param l lisätiedot
     */
    public void lisaaLisatieto(String l) {
        lisatieto = l;
    }
    
    /**
     * Lisätään sotilaalle nimi
     * @param etu etunimet
     * @param suku sukunimet
     */
    public void lisaaNimi(String etu, String suku) {
        etunimi = etu;
        sukunimi = suku;
    }
    
    /**
     * aliohjelma sotilaan rid tunnuksen johon se kuuluu
     * @return palauttaa ryhmä id
     */
    public int getRid() {
        return rid;
    }
    
    /**
     * palauttaa sotilaan id tunnuksen
     * @return palauttaa sotilaan id
     */
    public int getSid() {
        return sid;
    }
    
    /**
     * palauttaa sotilaan aseen numeron
     * @return palautta sotilaan asenumeron
     */
    public int getAseenNumero() {
        return asenumero;
    }
    
    /**
     * Palauttaa sotilaan arvon
     * @return palauttaa sotilaan arvon
     */
    public String getArvo() {
        return arvo;
    }
    
    /**
     * palauttaa sotilaan etunimen
     * @return palauttaa sotilaan etunimen
     */
    public String getEtuNimi() {
        return etunimi;
    }
    
    /**
     * Palauttaa sotilaan sukunimen
     * @return palauttaa sotilaan sukunimen
     */
    public String getSukuNimi() {
        return sukunimi;
    }
    
    /**
     * Palauttaa sotilaan syntymäpäivän
     * @return palauttaa sotilaan syntmäpäivän
     */
    public int getSyntymaAikaPv() {
        return syntymaaikapv;
    }
    
    /**
     * Palauttaa sotilaan syntymäkuukauden
     * @return palauttaa sotilaan syntmäkuukauden
     */
    public int getSyntymaAikaKk() {
        return syntymaaikakk;
    }
    /**
     * palauttaa sotilaan syntymävuoden
     * @return palauttaa sotilaan syntmävuoden
     */
    public int getSyntymaAikaVv() {
        return syntymaaikavv;
    }
    
    /**
     * Palauttaa sotilaalle kirjatun tehtävän
     * @return palauttaa sotilaan tehtävän
     */
    public String getTehtava() {
        return tehtava;
    }
    
    /**
     * Palauttaa sotilaalle kirjatun lisätiedon
     * @return palautta sotilaalle kirjatun lisätiedon
     */
    public String getLisatieto() {
        return lisatieto;
    }
    
    /**
     * Alustetaan sotilas
     */
    public void Alusta() {
        rid = 0;
        sid = 0;
        asenumero = 0;
        arvo = "Sotilas arvo puuttuu!";
        etunimi = "";
        sukunimi = "";
        syntymaaikapv = 0;
        syntymaaikakk = 0;
        syntymaaikavv = 0;
        tehtava = "Sotilaan tehtävä puuttuu!";
        lisatieto = "Sotilaalle ei ole kirjattu lisätietoa.";
        
        
    }
    
    /**
     * Palauttaa sotilaan tiedot crc kortin muodossa
     * @param jasen Jäsen jota käsitellään
     * @return Palauttaa Jäsenen crc-kortin
     */
    public String toString(Jasen jasen) {
        StringBuilder sotilas = new StringBuilder();
        sotilas.append(jasen.getEtuNimi() + "|");
        sotilas.append(jasen.getSukuNimi() + "|");
        sotilas.append(jasen.getSyntymaAikaPv() + "|");
        sotilas.append(jasen.getSyntymaAikaKk() + "|");
        sotilas.append(jasen.getSyntymaAikaVv() + "|");
        sotilas.append(jasen.getAseenNumero() + "|");
        sotilas.append(jasen.getArvo() + "|");
        sotilas.append(jasen.getTehtava() + "|");
        sotilas.append(jasen.getRid() + "|");
        sotilas.append(jasen.getLisatieto() + "|");
        
        return sotilas.toString();
        
    }

}
