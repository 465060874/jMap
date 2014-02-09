package de.geisslerbenjamin.jmap.dialog;

import de.geisslerbenjamin.jmap.mediator.interfaces.ElementEventInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.EventInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.ListenerInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;
import javafx.scene.control.Dialogs;
import javafx.stage.Stage;

/**
 * Display the information's of an element in a simple ok dialog.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Information implements ListenerInterface {
    private Stage stage;
    private String description;
    private String title;

    /**
     * Constructor.
     *
     * @param stage
     * @param mediator
     * @param description
     * @param title
     */
    public Information(Stage stage, MediatorInterface mediator, String description, String title) {
        this.stage = stage;
        this.description = description;
        this.title = title;

        mediator
                .register("element.info", this);
    }

    @Override
    public boolean exec(String name, EventInterface event) {
        switch (name) {
            case "element.info":
                Dialogs.showInformationDialog(
                        this.stage,
                        ((ElementEventInterface) event).getConfiguration().getInformation(),
                        this.description + ((ElementEventInterface) event).getId(),
                        this.title
                );
                return true;
        }

        return false;
    }
}
