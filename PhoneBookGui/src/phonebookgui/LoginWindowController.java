package phonebookgui;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rajan Sah
 */
public class LoginWindowController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField userNameTF;
    @FXML
    private JFXPasswordField passwordTF;
    @FXML
    private Label userLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton signUpButton;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allLabelInvisible();

    }

    @FXML
    private void loginButtonAction(ActionEvent event) {
        loadWindow("Phone Book", "MainWindow.fxml");
    }

    @FXML
    private void signButtonAction(ActionEvent event) {
        loadWindow("Sign Up Page", "SignUpPage.fxml");
    }

    @FXML
    private void passwordKeyPressed(KeyEvent event) {
        if (userNameTF.getText().isEmpty()) {
            userLabelVisible();
        }
        if (event.getCode().equals(KeyCode.TAB)) {

            if ((userNameTF.getText().trim().isEmpty() && !passwordTF.getText().trim().isEmpty())) {
                userLabelVisible();
                passwordLabelInvisible();
            }
            if ((!userNameTF.getText().trim().isEmpty() && passwordTF.getText().trim().isEmpty())) {
                userLabelInvisible();
                passwordLabelVisible();

            }
            if ((userNameTF.getText().trim().isEmpty() && passwordTF.getText().trim().isEmpty())) {
                allLabelVisible();
            }
            if (!userNameTF.getText().trim().isEmpty() && !passwordTF.getText().trim().isEmpty()) {
                allLabelInvisible();
            }
        }
    }

    @FXML
    private void userKeyPressed(KeyEvent event) {
        userLabelInvisible();
        if (event.getCode().equals(KeyCode.TAB)) {
            if (userNameTF.getText().trim().isEmpty()) {
                userLabelVisible();
            }
        }
    }

    @FXML
    private void userMouseClicked(MouseEvent event) {
        userLabelInvisible();
    }

    @FXML
    private void passwordMouseClicked(MouseEvent event) {
        passwordLabelInvisible();
        if (userNameTF.getText().isEmpty()) {
            userLabelVisible();
        } else {
            allLabelInvisible();
        }
    }

    @FXML
    private void passKeyTyped(KeyEvent event) {
        passwordLabel.setVisible(false);
    }

    @FXML
    private void userKeyTyped(KeyEvent event) {
        userLabel.setVisible(false);
    }

    private void userLabelVisible() {
        userLabel.setVisible(true);
    }

    private void userLabelInvisible() {
        userLabel.setVisible(false);
    }

    private void passwordLabelVisible() {
        passwordLabel.setVisible(true);
    }

    private void passwordLabelInvisible() {
        passwordLabel.setVisible(false);
    }

    private void allLabelVisible() {
        userLabel.setVisible(true);
        passwordLabel.setVisible(true);
    }

    private void allLabelInvisible() {
        userLabel.setVisible(false);
        passwordLabel.setVisible(false);
    }

    private void loadWindow(String title, String fxmlName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
