package phonebookgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class SignUpPageController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField fNameTF;
    @FXML
    private JFXTextField lNameTF;
    @FXML
    private JFXTextField uIdTF;
    @FXML
    private JFXPasswordField passwordTF;
    @FXML
    private JFXPasswordField rePasswordTF;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label uIdLabel;
    @FXML
    private Label pwdLabel;
    @FXML
    private Label rePwdLabel;

    private String fName;
    private String lName;
    private String uId;
    private String pwd;
    private String rePwd;
    boolean areValidInputs = false;
    public boolean isFirstNameValid = false;
    public boolean isLastNameValid = false;
    public boolean isUserIdValid = false;
    public boolean isPwdValid = false;
    public boolean isRePwdValid = false;
    public boolean isPwdMatched = false;
    @FXML
    private Label passwordMatchLabel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allLabelInvisible();
        fName = fNameTF.getText().trim();
        lName = lNameTF.getText().trim();
        uId = uIdTF.getText().trim();
        pwd = passwordTF.getText().trim();
        rePwd = rePasswordTF.getText().trim();
        saveBtn.setDisable(true);
        if (inputFieldValidator() == true && isPwdMatched == true) {
            saveBtn.setVisible(false);
        }

    }

    @FXML
    private void fNameKeyPressed(KeyEvent event) {
        fNameLabel.setVisible(false);
        if (event.getCode().equals(KeyCode.TAB)) {
            System.out.println(fName);
            if (fNameTF.getText().trim().isEmpty()) {
                fNameLabel.setVisible(true);
                isFirstNameValid = false;
            }
        }
    }

    @FXML
    private void fNameMouseClicked(MouseEvent event) {
        fNameLabel.setVisible(false);
    }

    @FXML
    private void fNameKeyTyped(KeyEvent event) {
        fNameLabel.setVisible(false);
    }

    @FXML
    private void lNameKeyPressed(KeyEvent event) {
        if (fNameTF.getText().isEmpty()) {
            fNameLabel.setVisible(true);

        } else {
            fNameLabel.setVisible(false);
            isFirstNameValid = true;
        }
        if (event.getCode().equals(KeyCode.TAB)) {
            if (lNameTF.getText().trim().isEmpty()) {
                lNameLabel.setVisible(true);
                isLastNameValid = false;
            } else {
                lNameLabel.setVisible(false);
                isLastNameValid = true;
            }
        }
    }

    @FXML
    private void lNameMouseClicked(MouseEvent event) {
        if (fNameTF.getText().isEmpty()) {
            fNameLabel.setVisible(true);
            isFirstNameValid = false;
        } else {
            fNameLabel.setVisible(false);
            isFirstNameValid = true;
        }
        lNameLabel.setVisible(false);

    }

    @FXML
    private void lNameKeyTyped(KeyEvent event) {
        lNameLabel.setVisible(false);
    }

    @FXML
    private void uIdKeyPressed(KeyEvent event) {
        if (fNameTF.getText().isEmpty()) {
            fNameLabel.setVisible(true);
            isFirstNameValid = false;
        } else {
            fNameLabel.setVisible(false);
            isFirstNameValid = true;
        }
        if (lNameTF.getText().isEmpty()) {
            lNameLabel.setVisible(true);
            isLastNameValid = false;
        } else {
            lNameLabel.setVisible(false);
            isLastNameValid = true;
        }
        if (event.getCode().equals(KeyCode.TAB)) {
            if (uIdTF.getText().trim().isEmpty()) {
                uIdLabel.setVisible(true);
                isUserIdValid = false;
            } else {
                uIdLabel.setVisible(false);
                isUserIdValid = true;
            }
        }
    }

    @FXML
    private void uIdMouseClicked(MouseEvent event) {
        if (fNameTF.getText().isEmpty()) {
            fNameLabel.setVisible(true);
            isLastNameValid = false;
        } else {
            fNameLabel.setVisible(false);
            isFirstNameValid = true;
        }
        if (lNameTF.getText().isEmpty()) {
            lNameLabel.setVisible(true);
            isLastNameValid = false;
        } else {
            lNameLabel.setVisible(false);
            isLastNameValid = true;
        }
        uIdLabel.setVisible(false);
    }

    @FXML
    private void uIdKeyTyped(KeyEvent event) {
        uIdLabel.setVisible(false);
    }

    @FXML
    private void passwordKeyPressed(KeyEvent event) {
        if (fNameTF.getText().isEmpty()) {
            fNameLabel.setVisible(true);
            isFirstNameValid = false;
        } else {
            fNameLabel.setVisible(false);
            isFirstNameValid = true;
        }
        if (lNameTF.getText().isEmpty()) {
            lNameLabel.setVisible(true);
            isLastNameValid = false;
        } else {
            lNameLabel.setVisible(false);
            isLastNameValid = true;
        }
        if (uIdTF.getText().isEmpty()) {
            uIdLabel.setVisible(true);
            isUserIdValid = false;
        } else {
            uIdLabel.setVisible(false);
            isUserIdValid = true;
        }
        if (event.getCode().equals(KeyCode.TAB)) {
            if (passwordTF.getText().trim().isEmpty()) {
                pwdLabel.setVisible(true);
                isPwdValid = false;
            } else {
                pwdLabel.setVisible(false);
                isPwdValid = true;
            }
        }
    }

    @FXML
    private void passwordMouseClicked(MouseEvent event) {
        if (fNameTF.getText().isEmpty()) {
            fNameLabel.setVisible(true);
            isFirstNameValid = false;

        } else {
            fNameLabel.setVisible(false);
            isFirstNameValid = true;
        }
        if (lNameTF.getText().isEmpty()) {
            lNameLabel.setVisible(true);
            isLastNameValid = false;
        } else {
            lNameLabel.setVisible(false);
            isLastNameValid = true;
        }
        if (uIdTF.getText().isEmpty()) {
            uIdLabel.setVisible(true);
            isUserIdValid = false;
        } else {
            uIdLabel.setVisible(false);
            isUserIdValid = true;
        }
        pwdLabel.setVisible(false);
    }

    @FXML
    private void actionRetypePassword(ActionEvent event) {
        pwdLabel.setVisible(false);
        passwordMatchLabel.setVisible(false);
    }

    @FXML
    private void passwordKeyTyped(KeyEvent event) {
        pwdLabel.setVisible(false);
        passwordMatchLabel.setVisible(false);
    }

    @FXML
    private void rePasswordKeyPressed(KeyEvent event) {
        if (fNameTF.getText().isEmpty()) {
            fNameLabel.setVisible(true);
            isFirstNameValid = false;
        } else {
            fNameLabel.setVisible(false);
            isFirstNameValid = true;
            isFirstNameValid = true;
        }
        if (lNameTF.getText().isEmpty()) {
            lNameLabel.setVisible(true);
            isLastNameValid = true;
        } else {
            lNameLabel.setVisible(false);
            isLastNameValid = true;

        }
        if (uIdTF.getText().isEmpty()) {
            uIdLabel.setVisible(true);
            isUserIdValid = false;
        } else {
            uIdLabel.setVisible(false);
            isUserIdValid = true;
        }
        if (passwordTF.getText().isEmpty()) {
            pwdLabel.setVisible(true);
            isPwdValid = false;
        } else {
            pwdLabel.setVisible(false);
            isPwdValid = true;
        }
        if (event.getCode().equals(KeyCode.TAB)) {
            if (rePasswordTF.getText().trim().isEmpty()) {
                rePwdLabel.setVisible(true);
                isRePwdValid = false;
            } else {
                rePwdLabel.setVisible(false);
                isRePwdValid = true;

                isPwdMatched = passwordTF.getText().trim().equals(rePasswordTF.getText().trim());
                if (inputFieldValidator() == true && isPwdMatched == true) {
                    saveBtn.setDisable(false);
                } else if (inputFieldValidator() == false || isPwdMatched == false) {
                    if (isPwdMatched == false) {
                        passwordMatchLabel.setVisible(true);
                        saveBtn.setDisable(true);
                    } else {
                        passwordMatchLabel.setVisible(false);
                        saveBtn.setDisable(false);
                    }
                } else {  
                    if (isPwdMatched == false) {
                        passwordMatchLabel.setVisible(false);
                        saveBtn.setDisable(true);
                    } else {
                        passwordMatchLabel.setVisible(true);
                    }
                }
            }
        }
    }

    @FXML
    private void rePasswordKeyMouseClicked(MouseEvent event) {
        if (fNameTF.getText().isEmpty()) {
            fNameLabel.setVisible(true);
            isFirstNameValid = false;
        } else {
            fNameLabel.setVisible(false);
            isFirstNameValid = true;
        }
        if (lNameTF.getText().isEmpty()) {
            lNameLabel.setVisible(true);
            isLastNameValid = false;
        } else {
            lNameLabel.setVisible(false);
            isLastNameValid = true;
        }
        if (uIdTF.getText().isEmpty()) {
            uIdLabel.setVisible(true);
            isUserIdValid = false;
        } else {
            uIdLabel.setVisible(false);
            isUserIdValid = true;
        }
        if (passwordTF.getText().isEmpty()) {
            pwdLabel.setVisible(true);
            isPwdValid = false;
        } else {
            pwdLabel.setVisible(false);
            isPwdValid = false;
        }
        rePwdLabel.setVisible(false);
        passwordMatchLabel.setVisible(false);
    }

    @FXML
    private void rePasswordKeyTyped(KeyEvent event) {
        rePwdLabel.setVisible(false);
        passwordMatchLabel.setVisible(false);
    }

    @FXML
    private void saveButtonAction(ActionEvent event) {
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    private void allLabelInvisible() {
        fNameLabel.setVisible(false);
        lNameLabel.setVisible(false);
        uIdLabel.setVisible(false);
        pwdLabel.setVisible(false);
        rePwdLabel.setVisible(false);
        passwordMatchLabel.setVisible(false);
    }

    private void allLabelVisible() {
        fNameLabel.setVisible(true);
        lNameLabel.setVisible(true);
        uIdLabel.setVisible(true);
        pwdLabel.setVisible(true);
        rePwdLabel.setVisible(true);

    }

    public boolean inputFieldValidator() {
        return isFirstNameValid == true && isLastNameValid == true
                && isUserIdValid == true && isPwdValid == true && isRePwdValid;
    }

    @FXML
    private void vBoxMouseClicked(MouseEvent event) {
        if (fNameTF.getText().isEmpty()) {
            fNameLabel.setVisible(true);
            isFirstNameValid = false;
        } else {
            fNameLabel.setVisible(false);
            isFirstNameValid = true;
            isFirstNameValid = true;
        }
        if (lNameTF.getText().isEmpty()) {
            lNameLabel.setVisible(true);
            isLastNameValid = true;
        } else {
            lNameLabel.setVisible(false);
            isLastNameValid = true;

        }
        if (uIdTF.getText().isEmpty()) {
            uIdLabel.setVisible(true);
            isUserIdValid = false;
        } else {
            uIdLabel.setVisible(false);
            isUserIdValid = true;
        }
        if (passwordTF.getText().isEmpty()) {
            pwdLabel.setVisible(true);
            isPwdValid = false;
        } else {
            pwdLabel.setVisible(false);
            isPwdValid = true;
        }
        if (rePasswordTF.getText().trim().isEmpty()) {
            rePwdLabel.setVisible(true);
            isRePwdValid = false;
        } else {
            rePwdLabel.setVisible(false);
            isRePwdValid = true;
        }
        isPwdMatched = passwordTF.getText().trim().equals(rePasswordTF.getText().trim());
        if (inputFieldValidator() == true && isPwdMatched == true) {
            saveBtn.setDisable(false);
        } else if (inputFieldValidator() == false || isPwdMatched == false) {
            saveBtn.setDisable(true);
            if (isPwdMatched == false) {
                passwordMatchLabel.setVisible(true);
            } else {
                passwordMatchLabel.setVisible(false);
            }
        } else {
            saveBtn.setDisable(true);
            if (isPwdMatched == false) {
                passwordMatchLabel.setVisible(true);
            } else {
                passwordMatchLabel.setVisible(false);
            }
        }

    }

    @FXML
    private void anchorPaneMouseClicked(MouseEvent event) {
        if (fNameTF.getText().isEmpty()) {
            fNameLabel.setVisible(true);
            isFirstNameValid = false;
        } else {
            fNameLabel.setVisible(false);
            isFirstNameValid = true;
            isFirstNameValid = true;
        }
        if (lNameTF.getText().isEmpty()) {
            lNameLabel.setVisible(true);
            isLastNameValid = true;
        } else {
            lNameLabel.setVisible(false);
            isLastNameValid = true;

        }
        if (uIdTF.getText().isEmpty()) {
            uIdLabel.setVisible(true);
            isUserIdValid = false;
        } else {
            uIdLabel.setVisible(false);
            isUserIdValid = true;
        }
        if (passwordTF.getText().isEmpty()) {
            pwdLabel.setVisible(true);
            isPwdValid = false;
        } else {
            pwdLabel.setVisible(false);
            isPwdValid = true;
        }
        if (rePasswordTF.getText().trim().isEmpty()) {
            rePwdLabel.setVisible(true);
            isRePwdValid = false;
        } else {
            rePwdLabel.setVisible(false);
            isRePwdValid = true;
        }
        isPwdMatched = passwordTF.getText().trim().equals(rePasswordTF.getText().trim());
        if (inputFieldValidator() == true && isPwdMatched == true) {
            saveBtn.setDisable(false);
        } else if (inputFieldValidator() == false || isPwdMatched == false) {
            saveBtn.setDisable(true);
            if (isPwdMatched == false) {
                passwordMatchLabel.setVisible(true);
            }
        } else {
            saveBtn.setDisable(true);
            if (isPwdMatched == false) {
                passwordMatchLabel.setVisible(true);
            }
        }
    }
}
