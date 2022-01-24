package Harjoitustyo;

/**
 * @author Jami Nurminen, Olli Peltomaa
 * @version 4 Mar 2021
 *
 */
public class Patteri {
    
    private Ryhmat ryhmat;
    private Jasenet jasenet;
    private Jaokset jaokset;
    @SuppressWarnings("unused")
    private String nimi;
    
    /**
     * Alustaa Patteri luokan
     */
    public Patteri() {
        nimi = "";
        ryhmat = new Ryhmat();
        jaokset = new Jaokset();
        jasenet = new Jasenet();
    }
    
    /**
     *  Alustaa patterin jäsenet
     */
    public void alusta() {
        jasenet.alusta();
    }
    
    
    /**
     * Palauttaa jaosten lukumäärän
     * @return jaosten määrä
     */
    public int jaoksetLkm() {
        return jaokset.getLkm();
    }
    
    /**
     * Palautta kaikki patterin jäsenet
     * @return palauttaa kaikki patterin jasenet
     */
    public Jasen[] getAll() {
         return jasenet.getJasenet();
    }
    
    /**
     * Palauttaa kaikki patterin ryhmät
     * @return palauttaa kaikki patterin ryhmat
     */
    public String[] getAllRyhmat() {
         return ryhmat.getRyhmat();
    }
    /**
     * Palauttaa kaikki patterin jaokset
     * @return palauttaa kaikki patterin jaokset
     */
    public String[] getAllJaokset() {
         return jaokset.getJaokset();
    }
    
    /**
     * Palauttaa ryhmien määrän
     * @return ryhmien määrä
     */
    public int maksimilkmRyhmat() {
        return ryhmat.getMaxlkm();
    }
    
    /**
     * Palauttaa halutun ryhmän nimen
     * @param rid Ryhmän id tunnut
     * @return Palauttaa ryhmän nimen
     */
    public String getRyhmanimi(int rid) {
        return ryhmat.getRyhmanimi(rid);
    }
    
    
    /**
     * Asettaa patterille nimen
     * @param patterinNimi Nimi joka patterille asetetaan.
     */
    public void setNimi(String patterinNimi) {
        nimi = patterinNimi;
    }
    
    
    /**
     * Hakee sid tunnusta vastaavan jäsenen
     * @param sid sotilasid
     * @return haluttu jäsen
     */
    public Jasen getJasen(int sid) {
        return jasenet.haeJasen(sid);
    }
    
    
    /**
     * Hakee sotilaalle kirjatun ryhmän tunnuksen
     * @param sid sotilas id
     * @return jäsenen ryhmätunnus
     */
    public int getRid(int sid) {
        return jasenet.getRid(sid);
    }
    
    
    /**
     * Palauttaa ryhmän jäsenten lukumäärän
     * @param rid ryhmä id
     * @return ryhmän jäsenten määrä
     */
    public int getRyhmanlkm(int rid) {
        return ryhmat.getLkm(rid);
    }
        
    
    /**
<<<<<<< HEAD
     * Lisätään sotilas järjestelmään
=======
     * 
>>>>>>> 574ed6531aa7732519060a22e28ab7db40bb2200
     * @param sotilas Sotilas
     * @return palauttaa sotilaan id tunnuksen
     */
    public int lisaaSotilas(String sotilas) {
       
       int erottimet[] = new int[10];
       int erottimia = 0;
       for(int i = 0; i < sotilas.length(); i++) {
           if(sotilas.charAt(i) == '|' ) {
               erottimet[erottimia] = i;
               erottimia++;
           }           
       }
       String etunimi = sotilas.substring(0, erottimet[0]);
       String sukunimi = sotilas.substring(erottimet[0]+1, erottimet[1]);
       int pv = Integer.parseInt(sotilas.substring(erottimet[1]+1, erottimet[2]));
       int kk = Integer.parseInt(sotilas.substring(erottimet[2]+1, erottimet[3]));
       int vv = Integer.parseInt(sotilas.substring(erottimet[3]+1, erottimet[4]));
       int asenumero = Integer.parseInt(sotilas.substring(erottimet[4]+1, erottimet[5]));
       String arvo = sotilas.substring(erottimet[5]+1, erottimet[6]);
       String tehtava = sotilas.substring(erottimet[6]+1, erottimet[7]);
       int rid = Integer.parseInt(sotilas.substring(erottimet[7]+1, erottimet[8]));
       String lisatieto = sotilas.substring(erottimet[8]+1, erottimet[9]);
      
       int Sotilasid = jasenet.lisaaJasen(rid, asenumero, arvo, etunimi, sukunimi, pv, kk, vv, tehtava, lisatieto);
       boolean full = ryhmat.lisaaJasen(rid, Sotilasid);
       if (full) {
           getJasen(Sotilasid).Alusta();
       }
       return Sotilasid;
    }
    
    
    /**
     * Palauttaa patterin kaikkien jäsenten lukumäärä
     * @return Palautta jäsenten lukumäärän
     */
    public int getJasentenlkm() {
        return jasenet.getJasentenlkm();
    }

    
    /**
     * Palauttaa jaoksen nimen
     * @param jid Jaos id
     * @return Palautta Jaoksen nimen
     */
    public String getJaosnimi(int jid) {
        return jaokset.getNimi(jid);
    }
    

    /**
     * Palauttaa jaokseen kirjatut ryhmät
     * @param jid jaos id
     * @return ryhmät
     */
    public int[] getRyhmat(int jid) {
        return jaokset.getRyhmat(jid);
    }
    

    /**
     * Palauttaa ryhmään kirjatut jäsenet
     * @param rid ryhmä id
     * @return jäsenet ryhmästä
     */
    public int[] getJasenet(int rid) {
        return ryhmat.getRyhmanSotilaat(rid);
    }


    /**
     * Palauttaa ryhmälle kirjatun tehtävän
     * @param rid ryhmän id
     * @return palauttaa ryhmän tehtävän
     */
    public String getRyhmantehtava(int rid) {
        return ryhmat.getRyhmantehtava(rid);
    }

    /** Poistaa tietorakenteesta jäsenen
     * @param sid Sotilaan id tunnus
     */
    public void poistaJasen(int sid) {
        Jasen jasen = getJasen(sid);
        int rid = jasen.getRid();
        Ryhma ryhma = ryhmat.getRyhma(rid);
        ryhma.poistaJasen(sid);
        jasen.Alusta();
    }
    
    
    

}
