/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebookgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Rajan
 */
public class ListWindowController implements Initializable {

    @FXML
    private TableColumn<TableEntry, String> nameColumn;
    @FXML
    private TableColumn<TableEntry, String> numberColumn;
    @FXML
    private TableColumn<TableEntry, String> notesColumn;

    ObservableList<TableEntry> list = FXCollections.observableArrayList();
    @FXML
    private TextField filterSearchTf;
    @FXML
    private TableView<TableEntry> tableView;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().NameProperty());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().NumberProperty());
        notesColumn.setCellValueFactory(cellData -> cellData.getValue().NotesProperty());
        nameColumn.setCellFactory(tc -> new HighlightingTableCell<>(filterSearchTf.textProperty()));
        numberColumn.setCellFactory(tc -> new HighlightingTableCell<>(filterSearchTf.textProperty()));
        notesColumn.setCellFactory(tc -> new HighlightingTableCell<>(filterSearchTf.textProperty()));
        list.add(new TableEntry("JacJaob", "Smith", "null"));
        list.add(new TableEntry("Issabella", "Smith", "null"));
        list.add(new TableEntry("Ethan", "Smith", "null"));
        list.add(new TableEntry("Emma", "Smith", "null"));
        list.add(new TableEntry("Micheal", "Brown", "null"));
        FilteredList<TableEntry> filteredList = new FilteredList<>(list);
        filteredList.predicateProperty().bind(Bindings.createObjectBinding(()-> person ->
                person.getName().toLowerCase().contains(filterSearchTf.getText().toLowerCase())
                || person.getNumber().toLowerCase().contains(filterSearchTf.getText().toLowerCase())
                || person.getNotes().toLowerCase().contains(filterSearchTf.getText().toLowerCase())
                || person.getName().toUpperCase().contains(filterSearchTf.getText().toUpperCase())
                || person.getNumber().toUpperCase().contains(filterSearchTf.getText().toUpperCase())
                || person.getNotes().toUpperCase().contains(filterSearchTf.getText().toUpperCase())
                ,filterSearchTf.textProperty()));

        tableView.setItems(filteredList);

    }

}
