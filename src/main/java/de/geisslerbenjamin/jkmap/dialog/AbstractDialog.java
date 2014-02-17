package de.geisslerbenjamin.jkmap.dialog;

import de.geisslerbenjamin.jkmap.configuration.interfaces.TranslationInterface;
import de.geisslerbenjamin.jkmap.dialog.interfaces.InputParameterInterface;
import javafx.scene.control.Dialogs;
import javafx.stage.Stage;

/**
 * Basic dialog class to display input and edit dialogs to change the values of drawables.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
abstract public class AbstractDialog {
    private Stage stage;
    private TranslationInterface translation;
    protected InputParameterInterface input;

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
     * Get the stage.
     *
     * @return
     */
    protected Stage getStage() {
        return this.stage;
    }

    /**
     * Get the translation.
     *
     * @return
     */
    protected TranslationInterface getTranslation() {
        return this.translation;
    }

    /**
     * Process the input data further.
     *
     * @param input
     */
    protected abstract void handleInput(InputParameterInterface input);

    /**
     * Displays the input dialog until a fields contain valid data or the process is canceled.
     *
     * @param inputData
     * @param type
     */
    protected void display(InputParameterInterface inputData, String type) {
        boolean showDialog = true;
        this.input = inputData;
        while (showDialog) {
            if (this.displayDialog(type)) {
                if (this.input.isValid()) {
                    this.handleInput(this.input);

                    showDialog = false;
                } else {
                    Dialogs.showErrorDialog(
                            this.stage,
                            this.translation.translate(this.input.getMessage()),
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
     * Displays a dialog to create or edit the parameters of a drawable.
     *
     * @param type
     * @return
     */
    abstract protected boolean displayDialog(String type);
}
