package de.geisslerbenjamin.jkmap.rectangle;

import de.geisslerbenjamin.jkmap.configuration.interfaces.TranslationInterface;
import de.geisslerbenjamin.jkmap.dialog.AbstractDialog;
import de.geisslerbenjamin.jkmap.dialog.ValidateField;
import javafx.geometry.Insets;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Displays a dialog to change the id, width, height and rotation of a given or new rectangle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
abstract public class AbstractRectangleDialog extends AbstractDialog {
    /**
     * Constructor.
     *
     * @param stage
     * @param translation
     */
    public AbstractRectangleDialog(Stage stage, TranslationInterface translation) {
        super(stage, translation);
    }

    /**
     * Displays a dialog to change the id, width, height and rotation of a given or new rectangle.
     *
     * @param type
     * @return
     */
    protected boolean displayDialog(String type) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // width
        final TextField width = new TextField();
        width.setPromptText(this.getTranslation().translate("element.rectangle.width"));
        width.setText(Double.toString(((InputParameter) this.input).width));
        grid.add(new Label(this.getTranslation().translate("element.rectangle.width") + ":"), 0, 1);
        grid.add(width, 1, 1);

        // height
        final TextField height = new TextField();
        height.setPromptText(this.getTranslation().translate("element.rectangle.height"));
        height.setText(Double.toString(((InputParameter) this.input).height));
        grid.add(new Label(this.getTranslation().translate("element.rectangle.height") + ":"), 0, 2);
        grid.add(height, 1, 2);

        // rotation
        final TextField rotation = new TextField();
        rotation.setPromptText(this.getTranslation().translate("element.rectangle.rotation"));
        rotation.setText(Double.toString(((InputParameter) this.input).rotation));
        grid.add(new Label(this.getTranslation().translate("element.rectangle.rotation") + ":"), 0, 3);
        grid.add(rotation, 1, 3);

        final InputParameter localInput = new InputParameter();
        localInput.valid = true;
        Callback<Void, Void> myCallback = new Callback<Void, Void>() {
            @Override
            public Void call(Void param) {
                localInput.id = ((InputParameter) input).id;

                // validate user input
                ValidateField validation = new ValidateField();
                if (validation.isDouble(width.getText())) {
                    localInput.width = Double.parseDouble(width.getText().replace(",", "."));
                } else {
                    localInput.valid = false;
                    localInput.message.add("error.rectangle.width");
                }

                if (validation.isDouble(height.getText())) {
                    localInput.height = Double.parseDouble(height.getText().replace(",", "."));
                } else {
                    localInput.valid = false;
                    localInput.message.add("error.rectangle.height");
                }

                if (validation.isDoubleOrZero(rotation.getText())) {
                    localInput.rotation = Double.parseDouble(rotation.getText().replace(",", "."));
                } else {
                    localInput.valid = false;
                    localInput.message.add("error.rectangle.rotation");
                }

                return null;
            }
        };

        String title = this.getTranslation().translate("element.rectangle." + type);
        Dialogs.DialogResponse response = Dialogs.showCustomDialog(
                this.getStage(),
                grid,
                title,
                title,
                Dialogs.DialogOptions.OK_CANCEL,
                myCallback
        );

        if (response == Dialogs.DialogResponse.OK) {
            this.input = localInput;
            return true;
        }

        return false;
    }
}
