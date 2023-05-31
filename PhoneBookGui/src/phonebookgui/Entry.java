/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebookgui;

public class Entry {

    public String name;
    public String number;
    public String notes;

    public Entry(String name, String number, String notes) {

        this.name = name;
        this.number = number;
        this.notes = notes;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;

    }

    public String getNumber() {
        return number;
    }

    public void setNotes(String notes) {
        this.notes = notes;

    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return this.name + ":" + this.number + ":" + this.notes;
    }

}
