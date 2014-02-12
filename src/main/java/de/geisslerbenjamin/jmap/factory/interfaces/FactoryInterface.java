package de.geisslerbenjamin.jmap.factory.interfaces;

import de.geisslerbenjamin.jmap.configuration.interfaces.DialogInterface;
import de.geisslerbenjamin.jmap.configuration.interfaces.TranslationInterface;
import de.geisslerbenjamin.jmap.data.interfaces.DataContainerInterface;
import de.geisslerbenjamin.jmap.data.interfaces.RowCreatorInterface;
import de.geisslerbenjamin.jmap.data.interfaces.TableDataGatewayInterface;
import de.geisslerbenjamin.jmap.dialog.interfaces.CreateDialogInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.SaveInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.util.Set;

/**
 * The main factory to create the necessary objects.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface FactoryInterface {
    /**
     * Add the factory for a drawable element to the list of factories.
     *
     * @param factory
     * @return
     */
    public FactoryInterface addElementFactory(ElementFactoryInterface factory);

    /**
     * Get the main stage.
     *
     * @return
     */
    public Stage getStage();

    /**
     * Get the translation object.
     *
     * @return
     */
    public TranslationInterface getTranslate();

    /**
     * Get the mediator.
     *
     * @return
     */
    public MediatorInterface getMediator();

    /**
     * Get the table-data-gateway to access the Microsoft Access database.
     *
     * @return
     */
    public TableDataGatewayInterface getTableDataGateway();

    /**
     * Get the container holding all drawable objects from the database.
     *
     * @return
     */
    public DataContainerInterface getDataContainer();

    /**
     * Create the background image.
     */
    public void createBackgroundImage();

    /**
     * Get the pane holding the image and the drawables.
     *
     * @return
     */
    public ScrollPane getImagePane();

    /**
     * Get the configuration for all drawables (zoom factor, on-click behavior...)
     *
     * @return
     */
    public DrawableDisplayConfigurationInterface getDrawableDisplayConfiguration();

    /**
     * Get the configuration for new drawables.
     *
     * @return
     */
    public DrawableConfigurationInterface getDrawableStandardConfig();

    /**
     * Get the dialog to create a new drawable of the given type.
     *
     * @param element
     * @return
     */
    public CreateDialogInterface getCreateDialog(String element);

    /**
     * Get the row creator object.
     *
     * @return
     */
    public RowCreatorInterface getRowCreator();

    /**
     * Access the dialog to edit the main configuration.
     *
     * @return
     */
    public DialogInterface getConfigurationDialog();

    /**
     * Get the names of all supported drawable types.
     *
     * @return
     */
    public Set<String> getElementNames();

    /**
     * Access the file save dialog.
     *
     * @return
     */
    public SaveInterface getSaveDialog();
}
