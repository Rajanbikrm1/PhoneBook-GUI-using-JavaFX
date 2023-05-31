/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebookgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.GlyphsBuilder;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rajan
 */
public class AddAndDeleteWindowController implements Initializable {

    @FXML
    private JFXTextField nameTf;
    @FXML
    private RequiredFieldValidator nameValidator;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXTextField phNumberTf;
    @FXML
    private JFXTextField notesTf;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private RequiredFieldValidator numberValidator;
    @FXML
    private RequiredFieldValidator notesValidator;

    private FxmlLoader loader;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField lastTf;
    @FXML
    private RequiredFieldValidator lastNameValidator;
    @FXML
    private RequiredFieldValidator fotmatValidator;
    @FXML
    private FontAwesomeIconView iconView;
    @FXML
    private Label errorLabel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
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
        FontAwesomeIconView icon3 = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
        FontAwesomeIconView icon4 = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
        icon.setSize("1.5em");
        icon1.setSize("1.5em");
        icon2.setSize("1.5em");
        icon3.setSize("1.5em");
        icon4.setSize("1.5em");
        icon.setFill(Color.RED);
        icon1.setFill(Color.RED);
        icon2.setFill(Color.RED);
        icon3.setFill(Color.RED);
        icon4.setFill(Color.RED);
        //  nameValidator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class).glyph(FontAwesomeIcon.WARNING).size("1.5em").styleClass("error").build());
        nameValidator.setIcon(icon);
        nameTf.getValidators().add(nameValidator);
        nameTf.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                nameTf.validate();
            }
        });

        lastNameValidator.setIcon(icon1);
        lastTf.getValidators().add(lastNameValidator);
        lastTf.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                lastTf.validate();
            }
        });
        // numberValidator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class).glyph(FontAwesomeIcon.WARNING).size("1.5em").styleClass("error").build());
        numberValidator.setIcon(icon2);
        fotmatValidator.setIcon(icon4);

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
        //notesValidator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class).glyph(FontAwesomeIcon.WARNING).size("1.5em").styleClass("error").build());
        notesValidator.setIcon(icon3);
        notesTf.getValidators().add(notesValidator);
        notesTf.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                notesTf.validate();
            }
        });

        addButton.disableProperty().bind(nameTf.textProperty().isNotEmpty()
                .and(phNumberTf.textProperty().isNotEmpty()
                        .and(notesTf.textProperty().isNotEmpty().and(lastTf.textProperty().isNotEmpty())))
                .not());
        deleteBtn.disableProperty().bind(nameTf.textProperty().isNotEmpty().and(lastTf.textProperty().isNotEmpty()).not());

        phNumberTf.setOnKeyTyped(t -> {
            if (phNumberTf.getText().length() >= 13) {
                int pos = phNumberTf.getCaretPosition();
                phNumberTf.setText(phNumberTf.getText(0, 12));
                phNumberTf.positionCaret(pos);
            }
        });
        nameTf.setOnKeyTyped(t -> {
            if (nameTf.getText().length() >= 8) {
                int pos = nameTf.getCaretPosition();
                nameTf.setText(nameTf.getText(0, 7));
                nameTf.positionCaret(pos);
            }
        });
        lastTf.setOnKeyTyped(t -> {
            if (lastTf.getText().length() >= 8) {
                int pos = lastTf.getCaretPosition();
                lastTf.setText(lastTf.getText(0, 7));
                lastTf.positionCaret(pos);
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

    }

    @FXML
    private void nameTfMouseAction(MouseEvent event) {
        nameTf.resetValidation();
        errorLabel.setVisible(false);
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
        String lastName = lastTf.getText();
        String number = phNumberTf.getText();
        String notes = notesTf.getText();
        String name = firstName + " " + lastName;
        PhoneBookGui.entryList[PhoneBookGui.num_entries] = new Entry(name, number, notes);
        PhoneBookGui.num_entries++;
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Entry Saved");
        alert.show();
    }

    @FXML
    private void deleteBtnAction(ActionEvent event) {
        String name = nameTf.getText() + " " + lastTf.getText();
        boolean isFound = false;
        ArrayList<Entry> list = new ArrayList<>();
        for (Entry entry : PhoneBookGui.entryList) {
            if (entry != null) {
                list.add(entry);
            }
        }
        Entry e = null;
        for (Entry entry : list) {
            if (entry.name.equalsIgnoreCase(name)) {
                e = entry;
                isFound = true;
                break;
            }
        }
        if (isFound == true) {
            list.remove(e);
            PhoneBookGui.num_entries = list.size();
            for (int i = 0; i < 200; i++) {
                if (i < list.size()) {
                    PhoneBookGui.entryList[i] = list.get(i);
                } else {
                    PhoneBookGui.entryList[i] = null;
                }
            }
            PhoneBookGui.storePhoneEntry(PhoneBookGui.file);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Entry " + name + " deleted!!!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Entry " + name + " not found!!!");
            alert.show();
        }

    }

    @FXML
    private void cancelBtnAction(ActionEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void lastTfKeyPressed(KeyEvent event) {
        lastTf.resetValidation();
    }

    @FXML
    private void lastTfMouseAction(MouseEvent event) {
        lastTf.resetValidation();
    }

    @FXML
    private void lastTfKeyTyped(KeyEvent event) {
        lastTf.resetValidation();
    }

}
