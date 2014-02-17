package de.geisslerbenjamin.jkmap.rectangle;

import de.geisslerbenjamin.jkmap.configuration.interfaces.TranslationInterface;
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
abstract public class AbstractDialog {
    private Stage stage;
    private TranslationInterface translation;
    private InputParameter input;

    /**
     * Constructor.
     *
     * @param stage
     * @param translation
     */
    public AbstractDialog(Stage stage, TranslationInterface translation) {
        this.stage = stage;
        this.translation = translation;
    }

    /**
     * Process the input data further.
     *
     * @param input
     */
    protected abstract void handleInput(InputParameter input);

    /**
     * Displays the input dialog until a fields contain valid data or the process is canceled.
     *
     * @param inputData
     * @param type
     */
    protected void display(InputParameter inputData, String type) {
        boolean showDialog = true;
        this.input = inputData;
        while (showDialog) {
            if (this.displayDialog(type)) {
                if (this.input.valid) {
                    this.handleInput(this.input);

                    showDialog = false;
                } else {
                    Dialogs.showErrorDialog(
                            this.stage,
                            this.translation.translate(this.input.message),
                            this.translation.translate("dialog.error"),
                            this.translation.translate("dialog.error")
                    );
                }
            } else {
                showDialog = false;
            }
        }
    }

    /**
     * Displays a dialog to change the id, width, height and rotation of a given or new rectangle.
     *
     * @param type
     * @return
     */
    private boolean displayDialog(String type) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // width
        final TextField width = new TextField();
        width.setPromptText(translation.translate("element.rectangle.width"));
        width.setText(Double.toString(this.input.width));
        grid.add(new Label(translation.translate("element.rectangle.width") + ":"), 0, 1);
        grid.add(width, 1, 1);

        // height
        final TextField height = new TextField();
        height.setPromptText(translation.translate("element.rectangle.height"));
        height.setText(Double.toString(this.input.height));
        grid.add(new Label(translation.translate("element.rectangle.height") + ":"), 0, 2);
        grid.add(height, 1, 2);

        // rotation
        final TextField rotation = new TextField();
        rotation.setPromptText(translation.translate("element.rectangle.rotation"));
        rotation.setText(Double.toString(this.input.rotation));
        grid.add(new Label(translation.translate("element.rectangle.rotation") + ":"), 0, 3);
        grid.add(rotation, 1, 3);

        final InputParameter localInput = new InputParameter();
        localInput.valid = true;
        Callback<Void, Void> myCallback = new Callback<Void, Void>() {
            @Override
            public Void call(Void param) {
                localInput.id = input.id;

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

        String title = translation.translate("element.rectangle." + type);
        Dialogs.DialogResponse response = Dialogs.showCustomDialog(
                stage,
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