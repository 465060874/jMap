package de.geisslerbenjamin.jkmap.circle;

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
 * Basic dialog configuration for a circle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
abstract public class AbstractCircleDialog extends AbstractDialog {
    protected String type;

    /**
     * Constructor.
     *
     * @param stage
     * @param translation
     * @param type
     */
    protected AbstractCircleDialog(Stage stage, TranslationInterface translation, String type) {
        super(stage, translation);
        this.type = type;
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

        // diameter
        final TextField diameter = new TextField();
        diameter.setPromptText(this.getTranslation().translate("element." + this.type + ".diameter"));
        diameter.setText(Double.toString(((InputParameter) this.input).diameter));
        grid.add(new Label(this.getTranslation().translate("element." + this.type + ".diameter") + ":"), 0, 1);
        grid.add(diameter, 1, 1);

        // rotation
        final TextField rotation = new TextField();
        rotation.setPromptText(this.getTranslation().translate("element." + this.type + ".rotation"));
        rotation.setText(Double.toString(((InputParameter) this.input).rotation));
        grid.add(new Label(this.getTranslation().translate("element." + this.type + ".rotation") + ":"), 0, 2);
        grid.add(rotation, 1, 2);

        final InputParameter localInput = new InputParameter();
        final String localType = this.type;
        localInput.valid = true;
        Callback<Void, Void> myCallback = new Callback<Void, Void>() {
            @Override
            public Void call(Void param) {
                localInput.id = ((InputParameter) input).id;

                // validate user input
                ValidateField validation = new ValidateField();
                if (validation.isDouble(diameter.getText())) {
                    localInput.diameter = Double.parseDouble(diameter.getText().replace(",", "."));
                } else {
                    localInput.valid = false;
                    localInput.message.add("error." + localType + ".diameter");
                }

                if (validation.isDoubleOrZero(rotation.getText())) {
                    localInput.rotation = Double.parseDouble(rotation.getText().replace(",", "."));
                } else {
                    localInput.valid = false;
                    localInput.message.add("error." + localType + ".rotation");
                }

                return null;
            }
        };

        String title = this.getTranslation().translate("element." + this.type + "." + type);
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
