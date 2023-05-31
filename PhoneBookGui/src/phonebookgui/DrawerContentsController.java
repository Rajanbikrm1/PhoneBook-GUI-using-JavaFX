/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebookgui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Rajan
 */
public class DrawerContentsController implements Initializable {

    @FXML
    private VBox vBoxStack;
    @FXML
    private JFXButton addDelBtn;
    @FXML
    private JFXButton searchBtn;
    @FXML
    private JFXButton editBtn;
    @FXML
    private JFXButton listBtn;
    @FXML
    private JFXButton singnOutBtn;
    @FXML
    private JFXButton exitBtn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addDelBtnAction(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader("AddAndDeleteWindow.fxml", "Add/Del");
        loader.loadWindow();
    }

    @FXML
    private void searchBtnAction(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader("SearchWindow.fxml", "Search");
        loader.loadWindow();
    }

    @FXML
    private void editBtnAction(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader("EditWindow.fxml", "Edit");
        loader.loadWindow();
    }

    @FXML
    private void listBtnAction(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader("ListTable.fxml", "Search");
        loader.loadWindow();
    }

    @FXML
    private void signOutBtnAction(ActionEvent event) {
        String numberBuilder = "";
        String notesBuilder = "";
        int size = PhoneBookGui.num_entries;
        ArrayList<Entry> newList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> noteList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String name = PhoneBookGui.entryList[i].getName();
            for (int j = 0; j < size; j++) {

                if (PhoneBookGui.entryList[j].getName().equalsIgnoreCase(name)) {
                    numberBuilder = numberBuilder + PhoneBookGui.entryList[j].getNumber().trim() + ",";
                    notesBuilder = notesBuilder + PhoneBookGui.entryList[j].getNotes().trim() + ",";
                }
            }
            if (!nameList.contains(name) || nameList.isEmpty()) {
                if (numberBuilder.isEmpty() && notesBuilder.isEmpty()) {
                    newList.add(new Entry(name, PhoneBookGui.entryList[i].getNumber(), PhoneBookGui.entryList[i].getNotes()));
                } else {
                    notesBuilder = notesBuilder.substring(0, notesBuilder.length() - 1);
                    numberBuilder = numberBuilder.substring(0, numberBuilder.length() - 1);
                    newList.add(new Entry(name, numberBuilder, notesBuilder));
                    nameList.add(name);
                }
            }
            numberBuilder = "";
            notesBuilder = "";
        }
        PhoneBookGui.num_entries = newList.size();
        for (int i = 0; i < 200; i++) {
            if (i < newList.size()) {
                PhoneBookGui.entryList[i] = newList.get(i);
            } else {
                PhoneBookGui.entryList[i] = null;
            }
        }
        PhoneBookGui.storePhoneEntry(PhoneBookGui.file);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Contacts merged");
        alert.showAndWait();
        FxmlLoader loader = new FxmlLoader("ListTable.fxml", "Search");
        loader.loadWindow();
        vBoxStack.getScene().getWindow().hide();
    }

    @FXML
    private void exitBtnAction(ActionEvent event) {
        System.exit(0);
    }

}
