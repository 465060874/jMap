package de.geisslerbenjamin.jmap.data;

import de.geisslerbenjamin.jmap.data.interfaces.DataContainerInterface;
import de.geisslerbenjamin.jmap.data.interfaces.RowCreatorInterface;
import de.geisslerbenjamin.jmap.data.interfaces.TableDataGatewayInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;

/**
 * Creates a new row in the access database and redraws all elements.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class RowCreator implements RowCreatorInterface {
    private TableDataGatewayInterface gateway;
    private MediatorInterface mediator;
    private DataContainerInterface data;

    public RowCreator(TableDataGatewayInterface gateway, MediatorInterface mediator, DataContainerInterface data) {
        this.gateway = gateway;
        this.mediator = mediator;
        this.data = data;
    }

    @Override
    public void create(String type, DrawableConfigurationInterface config) {
        DrawableInterface drawable = this.gateway.create(type, config);

        if (drawable != null) {
            this.data.add(drawable);
            this.mediator.dispatch("elements.reDraw");
        }
    }
}
