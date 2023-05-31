/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebookgui;

import com.jfoenix.controls.JFXProgressBar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rajan Sah
 */
public class SplashWindowController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private ProgressIndicator indicator;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Splash screen = new Splash();
        screen.start();
    }

    public class Splash extends Thread {

        @Override
        public void run() {

            try {
                Thread.sleep(1);
                Platform.runLater(() -> {
                    loadWindow("Login", "LoginWindow.fxml");
                    anchorPane.getScene().getWindow().hide();
                });

            } catch (InterruptedException ex) {
                Logger.getLogger(SplashWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loadWindow(String title, String fxmlName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SplashWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
