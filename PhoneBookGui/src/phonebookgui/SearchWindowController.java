/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebookgui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Rajan
 */
public class SearchWindowController implements Initializable {

    @FXML
    private JFXTextField searchByNameTf;
    @FXML
    private JFXButton searchByNameBtn;
    @FXML
    private JFXTextField searchNyNumberTf;
    @FXML
    private JFXButton searchByNumber;
    @FXML
    private JFXTextField searchByNotesTf;
    @FXML
    private JFXButton searchByNotes;
    @FXML
    private RequiredFieldValidator nameValidator;
    @FXML
    private RequiredFieldValidator numberValidator;
    @FXML
    private RequiredFieldValidator notesValidator;
    private FxmlLoader loader;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
        FontAwesomeIconView icon1 = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
        FontAwesomeIconView icon2 = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
        icon.setSize("1.5em");
        icon1.setSize("1.5em");
        icon2.setSize("1.5em");
        icon.setFill(Color.RED);
        icon1.setFill(Color.RED);
        icon2.setFill(Color.RED);
        nameValidator.setIcon(icon);
        searchByNameTf.getValidators().add(nameValidator);
        searchByNameTf.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                searchByNameTf.validate();
            }
        });
        numberValidator.setIcon(icon1);
        searchNyNumberTf.getValidators().add(numberValidator);
        searchNyNumberTf.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                searchNyNumberTf.validate();
            }
        });
        notesValidator.setIcon(icon2);
        searchByNotesTf.getValidators().add(notesValidator);
        searchByNotesTf.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                searchByNotesTf.validate();
            }
        });
        searchByNameBtn.disableProperty().bind(searchByNameTf.textProperty().isNotEmpty().not());
        searchByNumber.disableProperty().bind(searchNyNumberTf.textProperty().isNotEmpty().not());
        searchByNotes.disableProperty().bind(searchByNotesTf.textProperty().isNotEmpty().not());
    }

    @FXML
    private void searchByNameAction(ActionEvent event) {
        PhoneBookConstant.SEARCH_BY_NAME_KEYWORD = searchByNameTf.getText().trim();
        loader = new FxmlLoader("SearchByNameTable.fxml", "Search By Name Keyword");
        loader.loadWindow();
    }

    @FXML
    private void searchByNumberAction(ActionEvent event) {
        PhoneBookConstant.SEARCH_BY_NUMBER_KERWORD = searchNyNumberTf.getText();
        loader = new FxmlLoader("SearchByNumberTable.fxml", "Search By Number Keyword");
        loader.loadWindow();
    }

    @FXML
    private void searchByNotesAction(ActionEvent event) {
        PhoneBookConstant.SEARCH_BY_NOTES_KEYWORD = searchByNotesTf.getText();
        loader = new FxmlLoader("SearchByNotesTable.fxml", "Search by Notes Keyword");
        loader.loadWindow();
    }

}
