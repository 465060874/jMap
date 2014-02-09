package de.geisslerbenjamin.jmap.rectangle;

import de.geisslerbenjamin.jmap.configuration.interfaces.TranslationInterface;
import de.geisslerbenjamin.jmap.data.interfaces.TableDataGatewayInterface;
import de.geisslerbenjamin.jmap.dialog.interfaces.EditDialogInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jmap.mediator.ElementEvent;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;
import de.geisslerbenjamin.jmap.rectangle.interfaces.RectangleConfigurationInterface;
import javafx.stage.Stage;

/**
 * Description.
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
        input.rotation = ((RectangleConfigurationInterface) configuration).getRotation();
        this.config = (Configuration) configuration;

        this.display(input, "edit");
    }
}
