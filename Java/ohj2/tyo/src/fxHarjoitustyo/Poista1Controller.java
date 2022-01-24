package fxHarjoitustyo;


import fi.jyu.mit.fxgui.ModalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import fi.jyu.mit.fxgui.ModalControllerInterface;

/**
 * @author Olli Peltomaa & Jami Nurminen
 * @version 24.3.2021
 * Poistamisikkuna
 */
public class Poista1Controller implements ModalControllerInterface<String>{
    @FXML
    private Button poistaOK;
    
    @FXML
    private TextField syote;

    @FXML
    private Button peruutaPoisto;
    
    private String vastaus = null;
    
    @FXML
    private Label teksti;

    /*
     * Aliohjelma siirtyy pääikkunaan
     */
    @FXML
    void cancelDel(@SuppressWarnings("unused") ActionEvent event) {
        ModalController.closeStage(peruutaPoisto);
    }

    /*
     * Aliohjelma poistaa sotilaan järjestelmästä (Ei toimi vielä)
     */
    @FXML
    void deleteOK(@SuppressWarnings("unused") ActionEvent event) {        
        sotilasId();
        vastaus = teksti.getText();
        ModalController.closeStage(teksti);

    }
    
    /**
     * @return Palautta sotilaan id tunnuksen String muodossa
     */
    public String sotilasId() {       
        teksti.setText(syote.getText());
        return syote.getText();
    }

    
    /**
     * Palautetaan vastaus
     */
    @Override
    public String getResult() {
        return vastaus;
    }
    
    /**
     * Luodaan nimenkysymisdialogi ja palautetaan siihen kirjoitettu nimi tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä nimeä näytetään oletuksena
     * @return null jos painetaan Cancel, muuten kirjoitettu nimi
     */
     public static String kysyId(Stage modalityStage, String oletus) {
        return ModalController.showModal(
               UusisotilasController.class.getResource("Poista1.fxml"),
               "Poista Sotilas",
               modalityStage, oletus);
           }

    @Override
    public void handleShown() {
        //
        
    }

    @Override
    public void setDefault(String oletus) {
        //
        
    }

}
