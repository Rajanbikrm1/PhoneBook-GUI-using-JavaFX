package phonebookgui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Rajan Sah
 */
public class PhoneBookGui extends Application {

    public static Boolean isValid = false;
    public static File file;
    public static Entry[] entryList = new Entry[200];
    public static int num_entries;
    public static PrintStream writer;

    @Override
    public void start(Stage stage) {
        file = new File("book.txt");

        try {
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(PhoneBookGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        num_entries = readPhoneBook(file);
        FxmlLoader loader = new FxmlLoader("MainWindow.fxml", "");
        Parent root = loader.getResource();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static int readPhoneBook(File file) {
        int count = 0;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] fields = line.split(":");
                Entry entry = new Entry(fields[0].trim(), fields[1].trim(), fields[2].trim());
                entryList[count] = entry;
                count = count + 1;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PhoneBookGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static void storePhoneEntry(File file) {
        try {
            writer = new PrintStream(file);
            String newNumber = "";
            String newNotes = "";
            for (Entry entry : entryList) {
                if (entry != null) {
                    newNumber = entry.number.trim().substring(0, entry.number.length() - 1);
                    newNotes = entry.notes.trim().substring(0, entry.notes.length() - 1);
                    Entry newEntry = new Entry(entry.name, newNumber, newNotes);
                    writer.println(entry.toString());
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PhoneBookGui.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
            writer.flush();
        }

    }
}
