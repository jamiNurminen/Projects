package fxHarjoitustyo;

import Harjoitustyo.Patteri;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author Jami Nurminen & Olli Peltomaa
 * @version 19 Apr 2021
 * Poistamisen vahvistusikkuna
 */
public class Poista2Controller implements ModalControllerInterface<String>{


    @FXML
    private Button okBut;

    @FXML
    private Button cancelBut;

    @FXML
    private Label sotilaanNimi;
    
    private String vastaus = null;
    
    @FXML
    private Label nappi;
    
    
    /**
     * Palautetaan vastaus
     */
    @Override
    public String getResult() {
        return vastaus;
    }

    @Override
    public void handleShown() {
        //
        
    }
    
    
    /**
     * Peruutetaan toiminto painamalla cancel-nappia
     * @param event napin painallus
     */
    @FXML
    void cancel(@SuppressWarnings("unused") ActionEvent event) {
        ModalController.closeStage(cancelBut);
    }

    
    /**
     * Poistamisen vahvistus
     * @param event napin painallus
     */
    @FXML
    void delete(@SuppressWarnings("unused") ActionEvent event) {
        vastaus = "ok";
        nappi.setText(vastaus);
        ModalController.closeStage(nappi);
    }
    
    /**
     * Ohjelma sotilaan poistamiseen
     * @param modalityStage .
     * @param oletus Jasen joka edellisestä controllista tuli
     * @param patteri Patteri josta henkilo poistetaan
     * @param sid Sotilaan id tunnus
     */
    public static void poista(Stage modalityStage, String oletus, Patteri patteri, String sid) {
                String vastaus = ModalController.showModal(
                UusisotilasController.class.getResource("Poista2.fxml"),
                "Poista Sotilas",
                modalityStage, oletus);
                if (vastaus == "ok") {
                    patteri.poistaJasen(Integer.parseInt(sid));
                    
                }
            
    }

    
    /**
     * Ohjelma jolla poistamisen vahvistus-ikkunaan
     * laitetaan sotilaan nimi, jota ollaan poistamassa.
     */
    @Override
    public void setDefault(String sotilas) {
        
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
        String arvo = sotilas.substring(erottimet[5]+1, erottimet[6]);
        
        sotilaanNimi.setText(arvo + " " + sukunimi + " " + etunimi);
        
    }
    
    

}
