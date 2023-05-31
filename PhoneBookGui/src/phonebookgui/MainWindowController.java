/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebookgui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Rajan
 */
public class MainWindowController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXHamburger hamBurger;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem newMenuItem;
    @FXML
    private MenuItem searchMenuItem;
    @FXML
    private MenuItem editMenuItem;
    @FXML
    private MenuItem listMenuItem;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private JFXDrawer jDrawer;
    public HamburgerNextArrowBasicTransition transition;
    public Status status;
    @FXML
    private MenuItem mergeMenuItem;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY));
        listMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_ANY));
        menuItemClose.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_ANY));
        searchMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY));
        editMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_ANY));
        mergeMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F1, KeyCombination.CONTROL_ANY));
        loadDrawer();
    }

    private void loadWelcomeScreen() {
        FxmlLoader loader = new FxmlLoader("SecondWindow.fxml", "");
        PhoneBookGui.isValid = true;
        AnchorPane pane = loader.getResource();
        anchorPane.getChildren().addAll(pane);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), pane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);
        fadeIn.play();
        fadeIn.setOnFinished((e) -> {
            fadeOut.play();
        });
        fadeOut.setOnFinished((e) -> {
            FxmlLoader loader1 = new FxmlLoader("MainWindow.fxml", "Main Window");
            AnchorPane parent = loader1.getResource();
            anchorPane.getChildren().setAll(parent);
        });

    }

    @FXML
    private void addMenuItemAction(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader("AddAndDeleteWindow.fxml", "Add/Delete");
        loader.loadWindow();
    }

    @FXML
    private void searchMenuItemAction(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader("SearchWindow.fxml", "Search");
        loader.loadWindow();
    }

    @FXML
    private void editMenuItemAction(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader("EditWindow.fxml", "Edit");
        loader.loadWindow();
    }

    @FXML
    private void listMenuItemAction(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader("ListWindow.fxml", "Search");
        loader.loadWindow();
    }

    @FXML
    private void signOutMenuItemAction(ActionEvent event) {
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
    }

    @FXML
    private void exitMenuItemAction(ActionEvent event) {
        System.exit(0);
    }

    private void loadDrawer() {
        FxmlLoader loader = new FxmlLoader("DrawerContents.fxml", "");
        VBox vBox = loader.getResource();
        jDrawer.setSidePane(vBox);
        transition = new HamburgerNextArrowBasicTransition(hamBurger);
        transition.setRate(-1);
    }

    @FXML
    private void hamBurgerMouseAction(MouseEvent event) {
        transition.setRate(transition.getRate() * -1);
        transition.play();

        if (jDrawer.isShown()) {
            jDrawer.close();
        } else {
            jDrawer.open();
        }
    }
}
