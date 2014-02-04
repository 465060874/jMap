package de.geisslerbenjamin.jmap.rectangle;

import de.geisslerbenjamin.jmap.configuration.interfaces.TranslationInterface;
import de.geisslerbenjamin.jmap.data.interfaces.RowCreatorInterface;
import de.geisslerbenjamin.jmap.dialog.interfaces.CreateDialogInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import javafx.stage.Stage;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class CreateDialog extends AbstractDialog implements CreateDialogInterface {
    private DrawableConfigurationInterface standardConfig;
    private RowCreatorInterface creator;

    public CreateDialog(Stage stage, TranslationInterface translation, DrawableConfigurationInterface standardConfig, RowCreatorInterface creator) {
        super(stage, translation);

        this.standardConfig = standardConfig;
        this.creator = creator;
    }

    @Override
    public void create() {
        this.display(new InputParameter(), "create");
    }

    @Override
    protected void handleInput(InputParameter input) {
        this.creator.create(
                "rectangle",
                new Configuration(
                        this.standardConfig.getId(),
                        this.standardConfig.getX(),
                        this.standardConfig.getY(),
                        this.standardConfig.getColor(),
                        this.standardConfig.getInformation(),
                        input.width,
                        input.height,
                        input.rotation
                )
        );

        // Neues Element anlegen in Access
        // Neues Element anzeigen
    }
}
