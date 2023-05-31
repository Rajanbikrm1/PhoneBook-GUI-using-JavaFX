/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebookgui;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Rajan
 * @param <E>
 */
public class HighlightingTableCell<E> extends TableCell<E, String> {

    private final ObservableValue<String> highlightText;
    private final TextFlow textFlow;

    public HighlightingTableCell(ObservableValue<String> highlightText) {
        this.highlightText = highlightText;
        this.textFlow = new TextFlow();
        textFlow.setPrefHeight(12);
        highlightText.addListener((obs, oldText, newText) -> {
            updateTextFlow(newText);
        });
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        int count = 0;
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            int j =updateTextFlow(highlightText.getValue());
            setGraphic(textFlow);
            count = count + j;
        }
    }

    private int updateTextFlow(String highlight) {
        if (isEmpty()) {
            return 0;
        }
        String item = getItem();
        int index = item.toLowerCase().indexOf(highlight.toLowerCase());
        if (highlight.isEmpty() || index < 0) {
            Text text = new Text(item);
            text.setFont(Font.font(null, FontWeight.BOLD, 15));
            textFlow.getChildren().setAll(text);
            return 0;
        }
        String newCellElement = item.toLowerCase().replaceAll(highlight.toLowerCase(), "1");
        int count = 0;
        final List<Text> list = new ArrayList<>();
        for (char c : newCellElement.toCharArray()) {
            if (Character.toString(c).equals("1")) {
                Text text1 = new Text(highlight.toLowerCase());
                text1.setFont(Font.font(null, FontWeight.BOLD, 15));
                text1.setFill(Color.RED);
                list.add(text1);
                count = count + 1;
            } else {
                Text text2 = new Text(Character.toString(c));
                text2.setFont(Font.font(null, FontWeight.BOLD, 15));
                list.add(text2);
            }
        }
        textFlow.getChildren().setAll(list);
        return count;
       
    }

}
