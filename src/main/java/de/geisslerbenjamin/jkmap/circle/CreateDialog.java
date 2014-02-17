package de.geisslerbenjamin.jkmap.circle;

import de.geisslerbenjamin.jkmap.configuration.interfaces.TranslationInterface;
import de.geisslerbenjamin.jkmap.data.interfaces.RowCreatorInterface;
import de.geisslerbenjamin.jkmap.dialog.interfaces.CreateDialogInterface;
import de.geisslerbenjamin.jkmap.dialog.interfaces.InputParameterInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableConfigurationInterface;
import javafx.stage.Stage;

/**
 * Displays a dialog to create a new circle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class CreateDialog extends AbstractCircleDialog implements CreateDialogInterface {
    private DrawableConfigurationInterface standardConfig;
    private RowCreatorInterface creator;

    /**
     * Constructor.
     *
     * @param stage
     * @param translation
     * @param standardConfig
     * @param creator
     */
    public CreateDialog(
            Stage stage,
            TranslationInterface translation,
            String type,
            DrawableConfigurationInterface standardConfig,
            RowCreatorInterface creator) {
        super(stage, translation, type);

        this.standardConfig = standardConfig;
        this.creator = creator;
    }

    @Override
    protected void handleInput(InputParameterInterface input) {
        this.creator.create(
                this.type,
                new CircleConfiguration(
                        this.standardConfig.getId(),
                        this.standardConfig.getX(),
                        this.standardConfig.getY(),
                        ((InputParameter) input).rotation,
                        this.standardConfig.getColor(),
                        this.standardConfig.getInformation(),
                        ((InputParameter) input).diameter
                )
        );
    }

    @Override
    public void create() {
        this.display(new InputParameter(), "create");
    }
}
