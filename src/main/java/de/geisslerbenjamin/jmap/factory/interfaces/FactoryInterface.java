package de.geisslerbenjamin.jmap.factory.interfaces;

import de.geisslerbenjamin.jmap.configuration.interfaces.DialogInterface;
import de.geisslerbenjamin.jmap.configuration.interfaces.TranslationInterface;
import de.geisslerbenjamin.jmap.data.interfaces.DataContainerInterface;
import de.geisslerbenjamin.jmap.data.interfaces.RowCreatorInterface;
import de.geisslerbenjamin.jmap.data.interfaces.TableDataGatewayInterface;
import de.geisslerbenjamin.jmap.dialog.interfaces.CreateDialogInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.util.Set;

/**
 * Description.
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

    public Stage getStage();

    public TranslationInterface getTranslate();

    public MediatorInterface getMediator();

    public TableDataGatewayInterface getTableDataGateway();

    public DataContainerInterface getDataContainer();

    public void createBackgroundImage();

    public ScrollPane getImagePane();

    public DrawableDisplayConfigurationInterface getDrawableDisplayConfiguration();

    public DrawableConfigurationInterface getDrawableStandardConfig();

    public CreateDialogInterface getCreateDialog(String element);

    public RowCreatorInterface getRowCreator();

    /**
     * Access the dialog to edit the main configuration.
     *
     * @return
     */
    public DialogInterface getConfigurationDialog();

    public Set<String> getElementNames();
}
