
package phonebookgui;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableEntry {

    private final SimpleStringProperty name;
    private final SimpleStringProperty number;
    private final SimpleStringProperty notes;

    public TableEntry(String name, String number, String notes) {
        this.name = new SimpleStringProperty(name);
        this.number = new SimpleStringProperty(number);
        this.notes = new SimpleStringProperty(notes);
    }

    public final String getName() {
        return name.get();
    }

    public final String getNumber() {
        return number.get();
    }

    public final String getNotes() {
        return notes.get();
    }

    public final StringProperty NameProperty() {
        return this.name;
    }

    public final StringProperty NumberProperty() {
        return this.number;
    }

    public final StringProperty NotesProperty() {
        return this.notes;
    }
}
