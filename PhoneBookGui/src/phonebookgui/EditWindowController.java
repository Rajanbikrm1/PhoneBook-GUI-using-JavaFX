
package phonebookgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rajan
 */
public class EditWindowController implements Initializable {

    @FXML
    private JFXTextField nameTf;
    @FXML
    private JFXTextField phNumberTf;
    @FXML
    private JFXTextField notesTf;
    @FXML
    private RequiredFieldValidator nameValidator;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private RequiredFieldValidator numberValidator;
    @FXML
    private RequiredFieldValidator notesValidator;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label errorLabel;
    @FXML
    private FontAwesomeIconView iconView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iconView.setVisible(false);
        errorLabel.setVisible(false);
        iconView.setIcon(FontAwesomeIcon.WARNING);
        iconView.setSize("1.5em");
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
        FontAwesomeIconView icon1 = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
        FontAwesomeIconView icon2 = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
        icon.setSize("1.5em");
        icon1.setSize("1.5em");
        icon2.setSize("1.5em");
        icon.setFill(Color.RED);
        icon1.setFill(Color.RED);
        icon2.setFill(Color.RED);
        //  nameValidator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class).glyph(FontAwesomeIcon.WARNING).size("1.5em").styleClass("error").build());
        nameValidator.setIcon(icon);
        nameTf.getValidators().add(nameValidator);
        nameTf.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                nameTf.validate();
            }
        });
        // numberValidator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class).glyph(FontAwesomeIcon.WARNING).size("1.5em").styleClass("error").build());
        numberValidator.setIcon(icon1);
        phNumberTf.getValidators().add(numberValidator);
        phNumberTf.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                phNumberTf.validate();
            }
        });
        //notesValidator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class).glyph(FontAwesomeIcon.WARNING).size("1.5em").styleClass("error").build());
        notesValidator.setIcon(icon2);
        notesTf.getValidators().add(notesValidator);
        notesTf.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                notesTf.validate();
            }
        });

        phNumberTf.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Pattern pattern = Pattern.compile("([(]??\\d\\d\\d)??[-)]??\\d\\d\\d-\\d\\d\\d\\d");
            Matcher matcher = pattern.matcher(phNumberTf.getText());
            String name = phNumberTf.getText();
            boolean valid = name.isEmpty() && !matcher.matches();
            if (!newValue) {
                errorLabel.setVisible(false);
                iconView.setVisible(false);
                phNumberTf.getValidators().clear();
                phNumberTf.getValidators().add(numberValidator);
                phNumberTf.validate();
            }
            if (!name.isEmpty() && !matcher.matches()) {
                errorLabel.setVisible(true);
                iconView.setVisible(true);
            }
        });
        phNumberTf.setOnKeyTyped(t -> {
            if (phNumberTf.getText().length() >= 13) {
                int pos = phNumberTf.getCaretPosition();
                phNumberTf.setText(phNumberTf.getText(0, 12));
                phNumberTf.positionCaret(pos);
            }
        });

        phNumberTf.textProperty().addListener((observable, oldValue, newValue) -> {
            String newString = "";
            char[] charArr = newValue.toCharArray();
            for (int i = 0; i < newValue.length(); i++) {
                if (Character.isDigit(charArr[i]) || charArr[i] == '-' || charArr[i] == '(' || charArr[i] == ')') {
                    newString = newString + charArr[i];
                } else {
                    newString = newString + "";
                }
            }
            phNumberTf.setText(newString);
        });

        addButton.disableProperty().bind(nameTf.textProperty().isNotEmpty()
                .and(phNumberTf.textProperty().isNotEmpty()
                        .and(notesTf.textProperty().isNotEmpty()))
                .not());
    }

    @FXML
    private void nameTfMouseAction(MouseEvent event) {
        nameTf.resetValidation();
    }

    @FXML
    private void phNumberTfMouseAction(MouseEvent event) {
        phNumberTf.resetValidation();
        errorLabel.setVisible(false);
        iconView.setVisible(false);
    }

    @FXML
    private void notesTfMouseAction(MouseEvent event) {
        notesTf.resetValidation();
        Pattern pattern = Pattern.compile("([(]??\\d\\d\\d)??[-)]??\\d\\d\\d-\\d\\d\\d\\d");
        Matcher matcher = pattern.matcher(phNumberTf.getText());
        if (!matcher.matches() && !phNumberTf.getText().isEmpty()) {
            errorLabel.setVisible(true);
            iconView.setVisible(true);
        }
    }

    @FXML
    private void nameTfKeyPressed(KeyEvent event) {
        nameTf.resetValidation();
    }

    @FXML
    private void nameTfKeyTyped(KeyEvent event) {
        nameTf.resetValidation();
    }

    @FXML
    private void phNumberTfKeyPressed(KeyEvent event) {
        phNumberTf.resetValidation();
        errorLabel.setVisible(false);
        iconView.setVisible(false);
        Pattern pattern = Pattern.compile("([(]??\\d\\d\\d)??[-)]??\\d\\d\\d-\\d\\d\\d\\d");
        Matcher matcher = pattern.matcher(phNumberTf.getText());
        if (event.getCode().equals(KeyCode.TAB)) {
            if (!matcher.matches() && phNumberTf.getText().isEmpty()) {
                errorLabel.setVisible(true);
                iconView.setVisible(true);
            }
        }
    }

    @FXML
    private void phNumberTfKeyTyped(KeyEvent event) {
        phNumberTf.resetValidation();
        errorLabel.setVisible(false);
        iconView.setVisible(false);
    }

    @FXML
    private void notesTfKeyPressed(KeyEvent event) {
        notesTf.resetValidation();
    }

    @FXML
    private void notesTfKeyTyped(KeyEvent event) {
        notesTf.resetValidation();
    }

    @FXML
    private void addButtonAction(ActionEvent event) {
        String firstName = nameTf.getText();
        String number = phNumberTf.getText();
        String notes = notesTf.getText();
        boolean isValid = false;
        for (Entry entry : PhoneBookGui.entryList) {
            if (entry != null) {
                if (entry.name.equalsIgnoreCase(firstName)) {
                    entry.number = number;
                    entry.notes = notes;
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Entry " + entry.name + " is edited to new values!!!");
                    alert.showAndWait();
                    isValid = true;
                    String tempName = null;
                    String tempNumber = null;
                    String tempNotes = null;
                    for (int x = 0; x < PhoneBookGui.num_entries; x++) {
                        for (int y = x + 1; y < PhoneBookGui.num_entries; y++) {
                            if (PhoneBookGui.entryList[x].name.compareToIgnoreCase(PhoneBookGui.entryList[y].name) > 0) {
                                tempName = PhoneBookGui.entryList[x].name;
                                tempNumber = PhoneBookGui.entryList[x].number;
                                tempNotes = PhoneBookGui.entryList[x].notes;
                                PhoneBookGui.entryList[x].name = PhoneBookGui.entryList[y].name;
                                PhoneBookGui.entryList[x].number = PhoneBookGui.entryList[y].number;
                                PhoneBookGui.entryList[x].notes = PhoneBookGui.entryList[y].notes;
                                PhoneBookGui.entryList[y].name = tempName;
                                PhoneBookGui.entryList[y].number = tempNumber;
                                PhoneBookGui.entryList[y].notes = tempNotes;
                            }
                        }
                    }
                    PhoneBookGui.storePhoneEntry(PhoneBookGui.file);
                    break;
                }
            }
        }
        if (isValid == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Entry " + firstName + " not found!!!");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelBtnAction(ActionEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

}
