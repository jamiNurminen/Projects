package fxHarjoitustyo;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Jami Nurminen & Olli Peltomaa
 * @version 18 Feb 2021
 * Lisätietoikkuna
 */
public class LisatiedotController implements ModalControllerInterface<String>{

    @FXML
    private Button okButton;
    
    /**
     * Poistuu lisätietoikkunasta, kun
     * ok-painiketta painetaan.
     * @param event painallus
     */
    @FXML
    void okPress(@SuppressWarnings("unused") ActionEvent event) {
        ModalController.closeStage(okButton);
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
        //
    }

}
