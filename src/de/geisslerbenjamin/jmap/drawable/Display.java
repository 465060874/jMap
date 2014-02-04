package de.geisslerbenjamin.jmap.drawable;

import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableInterface;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Draw all elements on the background image and display to it.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Display {
    /**
     * Draw all elements on the background image and display to it.
     *
     * @param pane
     * @param image
     * @param elements
     */
    public static void draw(Pane pane, ScrollPane image, ArrayList<DrawableInterface> elements) {
        Group imageBox = (Group) image.getContent();
        imageBox.getChildren().removeAll();

        for (DrawableInterface drawable : elements) {
            if (imageBox.getChildren().contains(drawable.draw()) == false) {
                imageBox.getChildren().add(drawable.draw());
            }
        }

        image.setContent(imageBox);
        pane.getChildren().remove(image);
        if (pane.getChildren().contains(image) == false) {
            pane.getChildren().add(image);
        }
    }
}
