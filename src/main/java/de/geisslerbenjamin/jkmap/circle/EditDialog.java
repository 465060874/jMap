package de.geisslerbenjamin.jkmap.circle;

import de.geisslerbenjamin.jkmap.circle.interfaces.CircleConfigurationInterface;
import de.geisslerbenjamin.jkmap.configuration.interfaces.TranslationInterface;
import de.geisslerbenjamin.jkmap.data.interfaces.TableDataGatewayInterface;
import de.geisslerbenjamin.jkmap.dialog.interfaces.EditDialogInterface;
import de.geisslerbenjamin.jkmap.dialog.interfaces.InputParameterInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jkmap.mediator.ElementEvent;
import de.geisslerbenjamin.jkmap.mediator.interfaces.MediatorInterface;
import javafx.stage.Stage;

/**
 * Display a dialog to edit the values of a circle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class EditDialog extends AbstractCircleDialog implements EditDialogInterface {
    private MediatorInterface mediator;
    private CircleConfigurationInterface config;
    private TableDataGatewayInterface gateway;

    /**
     * Constructor.
     *
     * @param stage
     * @param translation
     * @param type
     * @param mediator
     * @param gateway
     */
    public EditDialog(
            Stage stage,
            TranslationInterface translation,
            String type,
            MediatorInterface mediator,
            TableDataGatewayInterface gateway) {
        super(stage, translation, type);
        this.mediator = mediator;
        this.gateway = gateway;
    }

    @Override
    public void edit(DrawableConfigurationInterface configuration) {
        InputParameter input = new InputParameter();
        input.id = configuration.getId();
        input.diameter = ((CircleConfigurationInterface) configuration).getDiameter();
        input.rotation = configuration.getRotation();
        this.config = (CircleConfiguration) configuration;

        this.display(input, "edit");
    }

    @Override
    protected void handleInput(InputParameterInterface input) {
        // copy the changes into the configuration and update the database
        this.config
                .setDiameter(((InputParameter) input).diameter)
                .setRotation(((InputParameter) input).rotation)
                .setId(((InputParameter) input).id);

        this.gateway.update(this.type, this.config);
        this.mediator.dispatch("element.edited", new ElementEvent(((InputParameter) input).id, this.config));
    }
}
