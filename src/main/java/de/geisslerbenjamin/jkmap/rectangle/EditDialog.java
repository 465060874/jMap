package de.geisslerbenjamin.jkmap.rectangle;

import de.geisslerbenjamin.jkmap.configuration.interfaces.TranslationInterface;
import de.geisslerbenjamin.jkmap.data.interfaces.TableDataGatewayInterface;
import de.geisslerbenjamin.jkmap.dialog.interfaces.EditDialogInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jkmap.mediator.ElementEvent;
import de.geisslerbenjamin.jkmap.mediator.interfaces.MediatorInterface;
import de.geisslerbenjamin.jkmap.rectangle.interfaces.RectangleConfigurationInterface;
import javafx.stage.Stage;

/**
 * Display a dialog to edit the values of a rectangle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class EditDialog extends AbstractDialog implements EditDialogInterface {
    private MediatorInterface mediator;
    private RectangleConfigurationInterface config;
    private TableDataGatewayInterface gateway;

    public EditDialog(Stage stage, TranslationInterface translation, MediatorInterface mediator, TableDataGatewayInterface gateway) {
        super(stage, translation);

        this.gateway = gateway;
        this.mediator = mediator;
    }

    @Override
    protected void handleInput(InputParameter input) {
        // copy the changes into the configuration and update the database
        this.config
                .setWidth(input.width)
                .setHeight(input.height)
                .setRotation(input.rotation)
                .setId(input.id);

        this.gateway.update("rectangle", this.config);
        this.mediator.dispatch("element.edited", new ElementEvent(input.id, this.config));
    }

    @Override
    public void edit(DrawableConfigurationInterface configuration) {
        InputParameter input = new InputParameter();
        input.id = configuration.getId();
        input.width = ((RectangleConfigurationInterface) configuration).getWidth();
        input.height = ((RectangleConfigurationInterface) configuration).getHeight();
        input.rotation = configuration.getRotation();
        this.config = (Configuration) configuration;

        this.display(input, "edit");
    }
}
