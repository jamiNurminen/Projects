package fxHarjoitustyo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import Harjoitustyo.Jasen;
import Harjoitustyo.Patteri;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * @author Jami Nurminen, Olli Peltomaa
 * @version 10 Feb 2021
 * Päävalikko
 */
public class PaavalikkoController implements ModalControllerInterface<String> {
    
    @FXML
    private TextField nimihakuKentta;

    @FXML
    private Button nimihakuNappi;

    @FXML
    private ListView<String> nimihakuLista;
    
    @FXML
    private MenuItem pvTallenna;

    @FXML
    private MenuItem pvAvaa;
    
    @FXML
    private MenuItem pvRyhmat;

    @FXML
    private MenuItem pvLopeta;

    @FXML
    private MenuItem pvLisaa;

    @FXML
    private MenuItem pvPoista;

    @FXML
    private MenuItem pvMuokkaa;

    @FXML
    private MenuItem pvOhje;

    @FXML
    private MenuItem pvLisatiedot;
    
    @FXML
    private ComboBox<String> jaoshaku;
    
    @FXML
    private TextArea lisatietoTehtavat;

    @FXML
    private Tab ryhma1;

    @FXML
    private ListView<String> ryhmaLista1;

    @FXML
    private Tab ryhma2;

    @FXML
    private ListView<String> ryhmaLista2;

    @FXML
    private Tab ryhma3;

    @FXML
    private ListView<String> ryhmaLista3;

    @FXML
    private Tab ryhma4;

    @FXML
    private ListView<String> ryhmaLista4;

    @FXML
    private Tab ryhma5;

    @FXML
    private ListView<String> ryhmaLista5;

    @FXML
    private TextArea lisatietoNaytto;
    
    private String nimi;
    
    static final String lineSeparator = System.getProperty ( "line.separator" );

    private boolean jaokset = true;
    
    private static Patteri patteri = new Patteri();
    
    private boolean avaus = false;
    
    /*
     * Aliohjelma siirtyy ikkunaan jossa lisätään uusi sotilas
     */
    @FXML
        void mscreenAdd(@SuppressWarnings("unused") ActionEvent event) {
        String sotilas = UusisotilasController.kysyNimi(null, "oletus");
        
        try {
            if (jaokset) listaaJaokset();
            jaokset = false;
            patteri.lisaaSotilas(sotilas);
          }
          catch(Exception e) {
              Dialogs.showMessageDialog("Sotilasta ei lisätty!");
          }

    }
    
    /**
     * Avataan ryhmän muokkausikkuna
     * @param event painallus
     */
    @FXML
    void mscreenRyhmat(@SuppressWarnings("unused") ActionEvent event) {
        ModalController.showModal(TietojarjestelmaGUIController.class.getResource("Muokkaaryhmaa.fxml"), "Ryhmän muokkaaminen", null, "");
    }
    
    /*
     * Aliohjelma siirtyy ikkunaan jossa poistetaan sotilas.
     */
    @FXML
    void mscreenDel(@SuppressWarnings("unused") ActionEvent event) {
        String id = Poista1Controller.kysyId(null, "oletus");
        Jasen sotilas = patteri.getJasen(Integer.parseInt(id));
        if (sotilas.getEtuNimi() == "") Dialogs.showMessageDialog("Sotiasta kyseisellä id tunnuksella ei löydy");
        if (sotilas.getEtuNimi() != "") {
            Poista2Controller.poista(null, sotilas.toString(sotilas), patteri, id);
            poistaListasta(id);
        }

    }

    
    /**
     * Ohjelma sotilaan poistamiseen
     * @param id sotilaan id
     */
    private void poistaListasta(String id) {
        ObservableList<String> lista = ryhmaLista1.getItems();
        for (int i = 0; i < lista.size(); i++) {
            int sid = irrotaId(lista.get(i));
            if (sid == Integer.parseInt(id)) ryhmaLista1.getItems().remove(i);
        }
        
    }

    
    /**
     * Ohjelma, jolla irroitetaan sotilaan id-tunnus
     * @param sotilas sotilas, jonka id otetaan
     * @return id-tunnus
     */
    private int irrotaId(String sotilas) {
        int vali = 0;
        for (int i = 0; i < sotilas.length(); i++) {
            if (sotilas.charAt(i) == ' ') {
                vali = i;
                break;
            }
        }
        int sid = Integer.parseInt(sotilas.substring(0,vali));
        return sid;
    }

    /*
     * Aliohjelma siirtyy ikkunaan jossa muokataan sotilaan tietoja.
     */
    @FXML
    void mscreenEdit(@SuppressWarnings("unused") ActionEvent event) {
        Dialogs.showMessageDialog("En osaa muokata sotilasta!");

    }

    /*
     *  Aliohjelma poistuu tietojärjestelmästä,
     */
    @FXML
    void mscreenExit(@SuppressWarnings("unused") ActionEvent event) {
        Dialogs.showMessageDialog("Hyvästi! :)");
        ModalController.closeStage(lisatietoNaytto);

    }

    /*
     * Aliohjelma näyttää ohjelman käyttöohjeet.
     */
    @FXML
    void mscreenHelp(@SuppressWarnings("unused") ActionEvent event) {
        ModalController.showModal(TietojarjestelmaGUIController.class.getResource("Ohje.fxml"), "Ohje", null, "");

    }

    /*
     * Aliohjelma näyttää ohjelman tiedot.
     */
    @FXML
    void mscreenInfo(@SuppressWarnings("unused") ActionEvent event) {
        ModalController.showModal(TietojarjestelmaGUIController.class.getResource("Lisatiedot.fxml"), "Lisätiedot", null, "");

    }

    /**
     * Aliohjelma joka lukee ja avaa tiedoston
     * @param event napin painallus
     */
    @FXML
    void mscreenOpen(@SuppressWarnings("unused") ActionEvent event) {
        if (!avaus) {
            if (jaokset) listaaJaokset();
            jaokset = false;
            patteri.alusta();
            try {
                File dat = new File(nimi + "sotilas.dat");
                @SuppressWarnings("resource")
                Scanner skanneri = new Scanner(dat);
                while (skanneri.hasNextLine()) {
                    String s = skanneri.nextLine();
                    patteri.lisaaSotilas(s);
                }
                Dialogs.showMessageDialog("Tiedoston avaus onnistui!");
            } catch (FileNotFoundException f) {
                Dialogs.showMessageDialog("Tiedostoa ei löydy");
            }
        }
        if (avaus) Dialogs.showMessageDialog("Tiedosto on jo avattu, tallenna muutokset ja poistu!");
        avaus = true;
        
    }

    /**
     * Ohjelma tietojen tallentamiseen.
     * @param event painallus
     * @throws IOException asd
     */
    @FXML
    void mscreenSave(@SuppressWarnings("unused") ActionEvent event) throws IOException {
        Jasen[] jasenet = patteri.getAll();
        String[] ryhmat = patteri.getAllRyhmat();
        @SuppressWarnings("hiding")
        String[] jaokset = patteri.getAllJaokset();
        tallennaJasenet(jasenet, nimi + "sotilas.dat");
        tallennaRyhmat(ryhmat, nimi + "ryhma.dat");
        tallennaJaokset(jaokset, nimi + "jaos.dat");
        
        Dialogs.showMessageDialog("Muutokset tallennettu");

    }
    
    /**
     * Ohjelma nimihaun avulla sotilaan etsimiseen
     * @param event napin painallus
     */
    @FXML
    void nimihakuHae(@SuppressWarnings("unused") ActionEvent event) {
        nimihakuLista.getItems().clear();
        String sotilas = nimihakuKentta.getText();
        if (sotilas == "") return;
        Jasen[] jasenet = patteri.getAll();
        if (!sotilas.contains(" ")) {
            for (int i = 0; i < jasenet.length; i++) {
                boolean onko = true;
                for (int j = 0; j < sotilas.length(); j ++) {
                    if (!jasenet[i].getSukuNimi().equals("") && jasenet[i].getSukuNimi().length() >= sotilas.length()) {
                        if (sotilas.toLowerCase().charAt(j) != jasenet[i].getSukuNimi().toLowerCase().charAt(j)) onko = false;
                    }
                    else onko = false;
                }
                
                if (onko == true) nimihakuLista.getItems().add(jasenet[i].getSid() + " " 
                                  + jasenet[i].getSukuNimi() + " " + jasenet[i].getEtuNimi());
            }
        }
        else Dialogs.showMessageDialog("Kirjoita pelkkä sukunimi");
    }
    
    
    /**
     * Tallennetaan jaokset jaos.dat tiedostoon
     * @param jaokset patterin jaokset
     * @param tiedosto johon tallennetaan
     * @throws FileNotFoundException tiedostoa ei löydy
     * @example
     * <pre name="test">
     * #import Harjoitustyo.*;
     * #import java.io.IOException;
     * String[] jaokset = new String[] {"Ekajaos", "Tokajaos", "Kolmasjaos"};
     * try {
     *   tallennaJaokset(jaokset, "jaosTest.dat");
     * } catch (IOException e) {
     *    e.printStackTrace();
     * }
     * tiedostonLuku("jaosTest.dat") === "Ekajaos Tokajaos Kolmasjaos ";
     * </pre>
     */
    public static void tallennaJaokset(String[] jaokset, String tiedosto) throws FileNotFoundException {
        try {
            @SuppressWarnings("resource")
            PrintStream dat = new PrintStream(new FileOutputStream(tiedosto));
            for (int i = 0; i < jaokset.length; i++) {
                if (jaokset[i] != "") dat.println(jaokset[i]);
            }
        } catch (FileNotFoundException f) {
            @SuppressWarnings("resource")
            PrintStream asd = new PrintStream(new FileOutputStream(tiedosto));
            for (int i = 0; i < jaokset.length; i++) {
                if (jaokset[i] != "") asd.println(jaokset[i]);
            }
        }
        
    }
    
    /**
     * Tallennetaan ryhmat ryhma.dat tiedostoon
     * @param ryhmat jaoksen ryhmat
     * @param tiedosto tiedosto johon tallennetaan
     * @throws FileNotFoundException tiedostoa ei löydy
     * @example
     * <pre name="test">
     * #import Harjoitustyo.*;
     * #import java.io.IOException;
     * String[] ryhmat = new String[]{"Rötinäryhmä", "Munkkiryhmä", "Moveryhmä"};
     * try {
     *   tallennaRyhmat(ryhmat, "ryhmaTest.dat");
     * } catch (IOException e) {
     *    e.printStackTrace();
     * }
     * tiedostonLuku("ryhmaTest.dat") === "Rötinäryhmä Munkkiryhmä Moveryhmä ";
     * </pre>
     */
    public static void tallennaRyhmat(String[] ryhmat, String tiedosto) throws FileNotFoundException {
        try {
            @SuppressWarnings("resource")
            PrintStream dat = new PrintStream(new FileOutputStream(tiedosto));
            
            for (int i = 0; i < ryhmat.length; i++) {
                if (ryhmat[i] != "") dat.println(ryhmat[i]);
            }
        } catch (FileNotFoundException f) {
            @SuppressWarnings("resource")
            PrintStream asd = new PrintStream(new FileOutputStream(tiedosto));
            for (int i = 0; i < ryhmat.length; i++) {
                if (ryhmat[i] != "") asd.println(ryhmat[i]);
            }
        }
        
    }

    /**
     * Luetaan tiedosto ja lisätään siihen uusi rivi,
     * jos tiedostoa ei ole, luodaan se
     * @param jasenet Patterin jasenet
     * @param tiedosto sotilas.dat
     * @throws IOException asd
     * @example
     * <pre name="test">
     * #import Harjoitustyo.*;
     * #import java.io.IOException;
     * Jasen[] jasenet = new Jasen[]{new Jasen(), new Jasen(), new Jasen()};
     * jasenet[0].lisaaNimi("Hatti","Vattinen");
     * jasenet[1].lisaaNimi("Rölli","Peikko");
     * jasenet[2].lisaaNimi("Uno","Turhapuro");
     * try {
     *   tallennaJasenet(jasenet, "sotilasTest.dat");
     * } catch (IOException e) {
     *    e.printStackTrace();
     * }
     * tiedostonLuku("sotilasTest.dat") === "Hatti|Vattinen|0|0|0|0|Sotilasarvo puuttuu!|Sotilaan tehtävä puuttuu!|0|Sotilaalle ei ole kirjattu lisätietoa.| Rölli|Peikko|0|0|0|0|Sotilasarvo puuttuu!|Sotilaan tehtävä puuttuu!|0|Sotilaalle ei ole kirjattu lisätietoa.| Uno|Turhapuro|0|0|0|0|Sotilasarvo puuttuu!|Sotilaan tehtävä puuttuu!|0|Sotilaalle ei ole kirjattu lisätietoa.| ";
     * </pre>
     */
    @SuppressWarnings("resource")
    public static void tallennaJasenet(Jasen[] jasenet, String tiedosto) throws IOException {
        try {
            PrintStream dat = new PrintStream(new FileOutputStream(tiedosto));
            
            for (int i = 0; i < jasenet.length; i++) {
                if (jasenet[i].getEtuNimi() != "") dat.println(sotilasDatiksi(jasenet[i]));
            }
        } catch (FileNotFoundException f) {
            PrintStream asd = new PrintStream(new FileOutputStream(tiedosto));
            for (int i = 0; i < jasenet.length; i++) {
                if (jasenet[i].getEtuNimi() != "") asd.println(sotilasDatiksi(jasenet[i]));
            }
        }
        
    }
    
    /**
     * Ohjelma tiedoston tallennuksen testaamiseen
     * @param tiedosto joka luetaan
     * @return tiedoston sisältö
     */
    public static String tiedostonLuku(String tiedosto) {
        StringBuilder sb = new StringBuilder();
        try {
            File dat = new File(tiedosto);
            @SuppressWarnings("resource")
            Scanner skanneri = new Scanner(dat);
            while (skanneri.hasNextLine()) {
                sb.append(skanneri.nextLine() + " ");
            }
            return sb.toString();
        } catch (FileNotFoundException f){
            return "virhe";
        }
    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public void handleShown() {
        //
    }

    @Override
    public void setDefault(String oletus) {
        nimi = oletus;
    }
    
    
    /**
     * Aliohjelma joka päivittää sotilaslistat
     * @param event napin painallus
     */
    @FXML
    void paivitaLista(@SuppressWarnings("unused") MouseEvent event) {
        try {
            ryhmaLista1.getItems().clear();
            ryhmaLista2.getItems().clear();
            ryhmaLista3.getItems().clear();
            ryhmaLista4.getItems().clear();
            ryhmaLista5.getItems().clear();
            paivitaTehtavat();
        
            int jaos = Integer.parseInt(jaoshaku.getValue().substring(0,1));
            int ryhmat[] = patteri.getRyhmat(jaos);
            
        
            for (int i = 0; i < 5; i++) {
                int[] jasenet = patteri.getJasenet(ryhmat[i]);
                for (int y = 0; y < jasenet.length; y++) {
                    listaaJasen(jasenet[y], ryhmat[i]);
                }
            }
        } catch (Exception e) {
            Dialogs.showMessageDialog("Valitse jaos tai lisää sotilas!");
        }
        
        
    }

    
    /**
     * Ohjelma joka tekee listan jaoksista.
     */
    private void listaaJaokset() {
        for (int i = 0; i < patteri.jaoksetLkm(); i++) {
            jaoshaku.getItems().add(patteri.getJaosnimi(i+1));
        }
    }
    
    
    /**
     * Ohjelma joka luo dat-tiedostoon sopivan muodon sotilaasta
     * @param jasen jasen josta tehdään dat-merkkijono
     * @return jäsen dat-muodossa
     */
    private static String sotilasDatiksi(Jasen jasen) {
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
    
    
    /**
     * Ohjelma, joka päivittää ryhmien tehtävät näyttöön
     */
    private void paivitaTehtavat() {
        StringBuilder tehtavat = new StringBuilder();
        int jaos = Integer.parseInt(jaoshaku.getValue().substring(0,1));
        int ryhmat[] = patteri.getRyhmat(jaos);
        for (int i = 0; i < ryhmat.length; i++) {
            tehtavat.append(patteri.getRyhmanimi(ryhmat[i]-1) + ": ");
            tehtavat.append(patteri.getRyhmantehtava(ryhmat[i]-1) + "\n");
        }
        lisatietoTehtavat.setText(tehtavat.toString());
    }
    
    
    /**
     * Ohjelma sotilaiden listaamiseen ryhmälistoihin
     * @param sid sotilas-id
     * @param rid ryhmä-id
     */
    private void listaaJasen(int sid, int rid) {
        ListView<String> lista = new ListView<String>();
        if (rid >= 0) lista = getLista1(rid);
        if (rid >= 5) lista = getLista2(rid);
        if (rid >= 10) lista = getLista3(rid);
        if (rid >= 15)  lista = getLista4(rid);
        if (rid >= 20)  lista = getLista5(rid);
        
        try {
            if (sid != 0) lista.getItems().add(lisaaListaan(sid));       
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    
    /**
     * Lisätään sotilaan tiedot listaan, jotka näkyvät päävalikossa
     * @param sid sotilas-id
     * @return merkkijono, jossa sid, arvo ja sukunimi
     */
    private String lisaaListaan(int sid) {
        if (sid == 0) return "";
        StringBuilder henkilo = new StringBuilder();
        henkilo.append(patteri.getJasen(sid).getSid() + " ");
        henkilo.append(patteri.getJasen(sid).getArvo() + " ");
        henkilo.append(patteri.getJasen(sid).getSukuNimi());
        return henkilo.toString();
    }
    
    
    /**
     * Tulostetaan tiedot näyttöön
     * @param event hiiren klikkaus
     */
    @FXML
    private void tietojenTulostaminen(MouseEvent event) {
        String tiedot;
        int sid = 0;
        switch(event.getSource().toString())
        {
           case "ListView[id=ryhmaLista1, styleClass=list-view]":
               tiedot = ryhmaLista1.getSelectionModel().getSelectedItem();
               break;
           case "ListView[id=ryhmaLista2, styleClass=list-view]":
               tiedot = ryhmaLista2.getSelectionModel().getSelectedItem();
               break;
           case "ListView[id=ryhmaLista3, styleClass=list-view]":
               tiedot = ryhmaLista3.getSelectionModel().getSelectedItem();
               break;
           case "ListView[id=ryhmaLista4, styleClass=list-view]":
               tiedot = ryhmaLista4.getSelectionModel().getSelectedItem();
               break;
           case "ListView[id=ryhmaLista5, styleClass=list-view]":
               tiedot = ryhmaLista5.getSelectionModel().getSelectedItem();
               break;
           case "ListView[id=nimihakuLista, styleClass=list-view]":
               tiedot = nimihakuLista.getSelectionModel().getSelectedItem();
               break;
           default:
               tiedot = "Tänne asti ei ole mahdollista päästä!";
               break;
         }
        
        try {
            sid = Integer.parseInt(tiedot.substring(0,1));
        } catch (Exception e) {
            //
        }
        try {
            sid = Integer.parseInt(tiedot.substring(0,2));
        } catch (Exception e) {
            //
        }
        try {
            sid = Integer.parseInt(tiedot.substring(0,3));
        } catch (Exception e) {
            //
        }
        
        if (sid == 0) lisatietoNaytto.setText("Valitse sotilas!");
        else lisatietoNaytto.setText(tiedot(sid));
        
    }
    
    
    /**
     * Ohjelma joka luo sotilaasta lisätietonäyttöön
     * sotilaan tietojen esitysmuodon
     * @param sid sotilas-id
     * @return sotilaan tiedot
     */
    private String tiedot(int sid) {
        StringBuilder tiedot = new StringBuilder();
        int[] sijoitus = sotilaanSijoitus(sid);
        tiedot.append("Sotilas id:  " + patteri.getJasen(sid).getSid() + "\n");
        tiedot.append("Jaos:        " + sijoitus[0] + "\n");
        tiedot.append("Ryhmä:       " + sijoitus[1] + "\n");
        tiedot.append("Asenumero:   " + patteri.getJasen(sid).getAseenNumero() + "\n");
        tiedot.append("Tehtävä:     " + patteri.getJasen(sid).getTehtava() + "\n");
        tiedot.append("Sotilas:     " + patteri.getJasen(sid).getArvo() + " " + patteri.getJasen(sid).getSukuNimi() + "\n");
        tiedot.append("Lisätietoa:  " + patteri.getJasen(sid).getLisatieto());
        return tiedot.toString();
    }
    
    
    /**
     * Palauttaa sotilaan jaoksen ja ryhmän
     * @param sid sotilas-id
     * @return sotilaan jaos ja ryhmä
     */
    private int[] sotilaanSijoitus(int sid) {
        Jasen sotilas = patteri.getJasen(sid);
        int rid = sotilas.getRid();
        int[] sijoitus = new int[2];
    
        if (rid >= 0) sijoitus[0] = 1;
        if (rid >= 5) sijoitus[0] = 2;
        if (rid >= 10) sijoitus[0] = 3;
        if (rid >= 15) sijoitus[0] = 4;
        if (rid >= 20) sijoitus[0] = 5;
        
        if (rid == 1 || rid == 6 || rid == 11 || rid == 16 || rid == 21 ) sijoitus[1] = 1;
        if (rid == 2 || rid == 7 || rid == 12 || rid == 17 || rid == 22 ) sijoitus[1] = 2;
        if (rid == 3 || rid == 8 || rid == 13 || rid == 18 || rid == 23 ) sijoitus[1] = 3;
        if (rid == 4 || rid == 9 || rid == 14 || rid == 19 || rid == 24 ) sijoitus[1] = 4;
        if (rid == 5 || rid == 10 || rid == 15 || rid == 20 || rid == 25 ) sijoitus[1] = 5;
        
        return sijoitus;
        
    }

    /**
     * Haetaan ensimmäisen jaoksen lista
     * @param rid ryhmä-id
     * @return ensimmäisen jaoksen lista
     */
    private ListView<String> getLista1(int rid) {
        switch (rid)
        {
        case 1:
            return ryhmaLista1;
        case 2:
            return ryhmaLista2;
        case 3:
            return ryhmaLista3;
        case 4:
            return ryhmaLista4;
        case 5:
            return ryhmaLista5;
        default:
            return new ListView<String>();
        }     
    }
    
    
    /**
     * Haetaan toisen jaoksen lista
     * @param rid ryhmä-id
     * @return toisen jaoksen lista
     */
    private ListView<String> getLista2(int rid) {
        switch (rid)
        {
        case 6:
            return ryhmaLista1;
        case 7:
            return ryhmaLista2;
        case 8:
            return ryhmaLista3;
        case 9:
            return ryhmaLista4;
        case 10:
            return ryhmaLista5;
        default:
            return new ListView<String>();
        }     
    }
    
    
    /**
     * Haetaan kolmannen jaoksen lista
     * @param rid ryhmä-id
     * @return kolmannen jaoksen lista
     */
    private ListView<String> getLista3(int rid) {
        switch (rid)
        {
        case 11:
            return ryhmaLista1;
        case 12:
            return ryhmaLista2;
        case 13:
            return ryhmaLista3;
        case 14:
            return ryhmaLista4;
        case 15:
            return ryhmaLista5;
        default:
            return new ListView<String>();
        }     
    }
    
    
    /**
     * Haetaan neljännen jaoksen lista
     * @param rid ryhmä-id
     * @return neljännen jaoksen lista
     */
    private ListView<String> getLista4(int rid) {
        switch (rid)
        {
        case 16:
            return ryhmaLista1;
        case 17:
            return ryhmaLista2;
        case 18:
            return ryhmaLista3;
        case 19:
            return ryhmaLista4;
        case 20:
            return ryhmaLista5;
        default:
            return new ListView<String>();
        }     
    }
    
    
    /**
     * Haetaan viidennen jaoksen lista
     * @param rid ryhmä-id
     * @return viidennen jaoksen lista
     */
    private ListView<String> getLista5(int rid) {
        switch (rid)
        {
        case 21:
            return ryhmaLista1;
        case 22:
            return ryhmaLista2;
        case 23:
            return ryhmaLista3;
        case 24:
            return ryhmaLista4;
        case 25:
            return ryhmaLista5;
        default:
            return new ListView<String>();
        }     
    }
    
    

}
