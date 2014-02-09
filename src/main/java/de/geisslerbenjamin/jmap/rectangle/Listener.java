package de.geisslerbenjamin.jmap.rectangle;

import de.geisslerbenjamin.jmap.data.interfaces.TableDataGatewayInterface;
import de.geisslerbenjamin.jmap.dialog.interfaces.EditDialogInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.ElementEventInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.EventInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.ListenerInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;

/**
 * Description.
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
        if (((ElementEventInterface) event).getConfiguration() instanceof Configuration) {
            switch (name) {
                case "element.edit":
                    this.dialog.edit(((ElementEventInterface) event).getConfiguration());
                    return true;
                case "element.moved":
                    return this.gateway.update("rectangle", ((ElementEventInterface) event).getConfiguration());
            }
        }

        return true;
    }
}
