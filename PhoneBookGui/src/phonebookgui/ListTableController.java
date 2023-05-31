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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rajan
 */
public class ListTableController implements Initializable {

    ObservableList<TableEntry> list = FXCollections.observableArrayList();
    @FXML
    private TableView<TableEntry> tableView;
    @FXML
    private TableColumn<TableEntry, String> nameCoulmn;
    @FXML
    private TableColumn<TableEntry, String> numberColumn;
    @FXML
    private TableColumn<TableEntry, String> notesColumn;
    @FXML
    private JFXButton mergeButton;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCoulmn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        StringBuilder numberBuilder = new StringBuilder();
        StringBuilder notesBuilder = new StringBuilder();
        for (Entry entry : PhoneBookGui.entryList) {
            if (entry != null) {
                String name = entry.name;
                String number = entry.number;
                // String[] allNumber = number.split(",");
                String notes = entry.notes;
                String[] allNotes = notes.split(",");
                for (int i = 0; i < allNotes.length; i++) {
                    //numberBuilder.append(allNumber[i].trim()).append("\n");
                    notesBuilder.append(allNotes[i].trim()).append("\n");
                }
                list.add(new TableEntry(entry.name, number, notesBuilder.toString()));
                numberBuilder = new StringBuilder();
                notesBuilder = new StringBuilder();
            }
        }
        tableView.setItems(list);
    }

    @FXML
    private void mergeButtonAction(ActionEvent event) {
        String numberBuilder = "";
        String notesBuilder = "";
        int size = PhoneBookGui.num_entries;
        ArrayList<Entry> newList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        //ArrayList<String> newNameList = new ArrayList<>();
        boolean isValid = false;
        //ArrayList<String> noteList = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < size; i++) {
            String name = PhoneBookGui.entryList[i].getName();
            for (int j = 0; j < size; j++) {

                if (PhoneBookGui.entryList[j].getName().equalsIgnoreCase(name) && count < 2) {

                    notesBuilder = notesBuilder + PhoneBookGui.entryList[j].getNotes().trim() + ",";
                    if (count == 1) {
                        notesBuilder = notesBuilder + PhoneBookGui.entryList[j].getNumber().trim();
                    }
                    count = count + 1;
                } else {
                }
            }
            if (!nameList.contains(name) || nameList.isEmpty()) {
                if (notesBuilder.isEmpty()) {
                    newList.add(new Entry(name, PhoneBookGui.entryList[i].getNumber(), PhoneBookGui.entryList[i].getNotes()));
                } else {
                    //notesBuilder = notesBuilder.substring(0, notesBuilder.length() - 1);
                    //numberBuilder = numberBuilder.substring(0, numberBuilder.length() - 1);
                    newList.add(new Entry(name, PhoneBookGui.entryList[i].getNumber(), notesBuilder));
                    nameList.add(name);
                }
            }
            count = 0;
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
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

}
