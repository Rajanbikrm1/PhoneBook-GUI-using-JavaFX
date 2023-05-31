/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebookgui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Rajan
 */
public class FxmlLoader {

    private final String fxmlName;
    private final String title;

    public FxmlLoader(String fxmlName, String title) {
        this.fxmlName = fxmlName;
        this.title = title;
    }

    public void loadWindow() {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource(this.fxmlName));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle(this.title);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FxmlLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public <T> T getResource() {
        try {
            return FXMLLoader.load(getClass().getResource(this.fxmlName));
        } catch (IOException ex) {
            Logger.getLogger(FxmlLoader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
