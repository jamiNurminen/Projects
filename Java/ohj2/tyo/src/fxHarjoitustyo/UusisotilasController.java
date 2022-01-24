package fxHarjoitustyo;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * @author Olli Peltomaa, Jami Nurminen
 * @version 12.3.2021
 * Uusisotilas ikkuna
 */
public class UusisotilasController implements ModalControllerInterface<String> {
    
    @FXML
    private TextArea lisaysLisatieto;

    @FXML
    private TextField lisaysEtu;

    @FXML
    private TextField lisaysSuku;

    @FXML
    private TextField lisaysKk;

    @FXML
    private TextField lisaysVv;
    
    @FXML
    private TextField lisaysPv;

    @FXML
    private TextField lisaysAse;

    @FXML
    private TextField lisaysTeht;
    
    @FXML
    private Button okBut;

    @FXML
    private Button cancelBut;
    
    @FXML
    private ComboBoxChooser<String> lisaysJaos;

    @FXML
    private ComboBoxChooser<String> lisaysRyhma;

    @FXML
    private ComboBoxChooser<String> lisaysArvo;
    
    @FXML
    private Label sotilasDat;
    
    private String vastaus = null;
    
    /*
     * Aliohjelma siirtää käyttäjän pääikkunaan.
     */
    @FXML
    void Poistu(@SuppressWarnings("unused") MouseEvent event) {
        ModalController.closeStage(cancelBut);
    }
    
    /*
     * Aliohjelma tallentaa syötetyt tiedot oliolle ja poistuu pääikkunaan.
     */
    @FXML
    void Tallenna(@SuppressWarnings("unused") MouseEvent event) {
        
        uusiSotilas();
        vastaus = sotilasDat.getText();
        ModalController.closeStage(sotilasDat);
        
    }
    
    /**
    * Luodaan nimenkysymisdialogi ja palautetaan siihen kirjoitettu nimi tai null
    * @param modalityStage mille ollaan modaalisia, null = sovellukselle
    * @param oletus mitä nimeä näytetään oletuksena
    * @return null jos painetaan Cancel, muuten kirjoitettu nimi
    */
    public static String kysyNimi(Stage modalityStage, String oletus) {
       return ModalController.showModal(
              UusisotilasController.class.getResource("UusiSotilas.fxml"),
              "Uusi Sotilas",
              modalityStage, oletus);
          }

    
    /**
     * palautetaan vastaus
     */
    @Override
    public String getResult() {
        return vastaus;
    }

    @Override
    public void handleShown() {
        //
    }

    @Override
    public void setDefault(String arg0) {
        //
    }
    
    //===============================================================================================0
    
    
    /**
     * Luodaan sotilaasta merkkijono
     * @return sotilas merkkijonona
     */
    public String uusiSotilas() {
        StringBuilder sotilas = new StringBuilder();
        sotilas.append(lisaysEtu.getText() + "|");
        sotilas.append(lisaysSuku.getText() + "|");
        sotilas.append(lisaysPv.getText() + "|");
        sotilas.append(lisaysKk.getText() + "|");
        sotilas.append(lisaysVv.getText() + "|");
        sotilas.append(lisaysAse.getText() + "|");
        sotilas.append(lisaysArvo.getSelectedText() + "|");
        sotilas.append(lisaysTeht.getText() + "|");
        sotilas.append(rid(lisaysRyhma.getSelectedText(), lisaysJaos.getSelectedText()) + "|");
        sotilas.append(lisaysLisatieto.getText() + "|");
        
        sotilasDat.setText(sotilas.toString());
        return sotilas.toString();
    }

    
    /**
     * Antaa ryhmä-id:n merkkijonona
     * @param rid ryhmä-id
     * @param jid jaos-id
     * @return ryhmä-id
     */
    private String rid(String rid, String jid) {
        int ryhma = Integer.parseInt(rid.substring(0,1));
        int jaos = Integer.parseInt(jid.substring(0,1));
        
        switch (jaos) {
        case 1: 
            switch (ryhma) {
            case 1: 
                return "1";
            case 2: 
                return "2";
            case 3: 
                return "3";
            case 4: 
                return "4";
            default:
                return "5";
            }
        case 2: 
            switch (ryhma) {
            case 1: 
                return "6";
            case 2: 
                return "7";
            case 3: 
                return "8";
            case 4: 
                return "9";
            default:
                return "10";
            }
        case 3: 
            switch (ryhma) {
            case 1: 
                return "11";
            case 2: 
                return "12";
            case 3: 
                return "13";
            case 4: 
                return "14";
            default:
                return "15";
            }
        case 4: 
            switch (ryhma) {
            case 1: 
                return "16";
            case 2: 
                return "17";
            case 3: 
                return "18";
            case 4: 
                return "19";
            default:
                return "20";
            }
        default:
            switch (ryhma) {
            case 1: 
                return "21";
            case 2: 
                return "22";
            case 3: 
                return "23";
            case 4: 
                return "24";
            default:
                return "25";
            }
        }
    }
    

}
