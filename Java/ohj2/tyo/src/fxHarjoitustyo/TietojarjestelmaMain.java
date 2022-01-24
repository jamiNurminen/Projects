package fxHarjoitustyo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Olli Peltomaa, Jami Nurminen
 * @version 24.3.2021
 * Ohjelman käynnistys
 */
public class TietojarjestelmaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("TietojarjestelmaGUIView.fxml"));
            final Pane root = ldr.load();
            //final TietojarjestelmaGUIController tietojarjestelmaCtrl = (TietojarjestelmaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("tietojarjestelma.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ilmatorjuntapatterin sotilastietojarjestelmä");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pääohjelma
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}