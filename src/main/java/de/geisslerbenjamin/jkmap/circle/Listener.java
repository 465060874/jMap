package de.geisslerbenjamin.jkmap.circle;

import de.geisslerbenjamin.jkmap.data.interfaces.TableDataGatewayInterface;
import de.geisslerbenjamin.jkmap.dialog.interfaces.EditDialogInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.ElementEventInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.EventInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.ListenerInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.MediatorInterface;

/**
 * Listener listing to the on-click-actions.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Listener implements ListenerInterface {
    private EditDialogInterface dialog;
    private TableDataGatewayInterface gateway;

    public Listener(MediatorInterface mediator, EditDialogInterface dialog, TableDataGatewayInterface gateway) {
        this.dialog = dialog;
        this.gateway = gateway;

        mediator
                .register("element.edit", this)
                .register("element.moved", this);
    }

    @Override
    public boolean exec(String name, EventInterface event) {
        if (((ElementEventInterface) event).getConfiguration() instanceof CircleConfiguration) {
            switch (name) {
                case "element.edit":
                    this.dialog.edit(((ElementEventInterface) event).getConfiguration());
                    return true;
                case "element.moved":
                    return this.gateway.update("circle", ((ElementEventInterface) event).getConfiguration());
            }
        }

        return true;
    }
}
