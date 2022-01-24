package fxHarjoitustyo;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * @author Olli Peltomaa & Jami Nurminen
 * @version 24.3.2021
 * Järjestelmän alkuvalikko
 */
public class TietojarjestelmaGUIController {

    @FXML
    private Button uusiClick;
    
    @FXML
    private TextField textKentta;

    @FXML
    private Button cancelClick;

    @FXML
    private Button onClick;
    
    /**
     *  Rekisterinnimi
     */
    public static String Rekisterinimi = "";

    /*
     * Enter painikkeesta avataan haluttu tietojärjestelmä.
     */
    @FXML
    void enter(@SuppressWarnings("unused") MouseEvent event) {
        Boolean valilyonteja = false;
        for (int i = 0; i < Rekisterinimi.length(); i++) {
            if(Rekisterinimi.charAt(i) == ' ') valilyonteja = true;
        }
        if (!valilyonteja) ModalController.showModal(TietojarjestelmaGUIController.class.getResource("PaavalikkoGUIView.fxml"), Rekisterinimi, null, Rekisterinimi);
        else Dialogs.showMessageDialog("Patterin nimessä ei saa olla välilyöntejä!");
    }

    /**
     * Poistutaan ikkunasta
     * @param event klikkaus
     */
    @FXML
    void poistu(@SuppressWarnings("unused") MouseEvent event) {
        ModalController.closeStage(cancelClick);
    }

    
    /*
     * Aliohjelma siirtää tekstikentän syötteen.
     */
    @FXML
    void keyReleased(@SuppressWarnings("unused") KeyEvent event) {
        Rekisterinimi = textKentta.getText();
    }
    
    

}