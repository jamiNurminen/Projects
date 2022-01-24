package fxHarjoitustyo;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Olli Peltomaa & Jami Nurminen
 * @version 18.2.2021
 * Sulkee ohjesivun poistu-painikkeesta
 */
public class OhjeController implements ModalControllerInterface<String>{

    
    @FXML private Button suljeOhje;
    
    @FXML
    void exitHelp(@SuppressWarnings("unused") ActionEvent event) {
        ModalController.closeStage(suljeOhje);
    }

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }


}
