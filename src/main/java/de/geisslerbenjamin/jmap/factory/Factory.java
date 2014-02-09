package de.geisslerbenjamin.jmap.factory;

import de.geisslerbenjamin.jmap.configuration.Dialog;
import de.geisslerbenjamin.jmap.configuration.Reader;
import de.geisslerbenjamin.jmap.configuration.Translation;
import de.geisslerbenjamin.jmap.configuration.Writer;
import de.geisslerbenjamin.jmap.configuration.interfaces.ConfigurationInterface;
import de.geisslerbenjamin.jmap.configuration.interfaces.DialogInterface;
import de.geisslerbenjamin.jmap.configuration.interfaces.TranslationInterface;
import de.geisslerbenjamin.jmap.data.DataContainer;
import de.geisslerbenjamin.jmap.data.RowCreator;
import de.geisslerbenjamin.jmap.data.TableDataGateway;
import de.geisslerbenjamin.jmap.data.interfaces.DataContainerInterface;
import de.geisslerbenjamin.jmap.data.interfaces.RowCreatorInterface;
import de.geisslerbenjamin.jmap.data.interfaces.TableDataGatewayInterface;
import de.geisslerbenjamin.jmap.dialog.Information;
import de.geisslerbenjamin.jmap.dialog.interfaces.CreateDialogInterface;
import de.geisslerbenjamin.jmap.drawable.BackgroundImage;
import de.geisslerbenjamin.jmap.drawable.Display;
import de.geisslerbenjamin.jmap.drawable.DrawableConfiguration;
import de.geisslerbenjamin.jmap.drawable.DrawableDisplayConfiguration;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jmap.factory.interfaces.ElementFactoryInterface;
import de.geisslerbenjamin.jmap.factory.interfaces.FactoryInterface;
import de.geisslerbenjamin.jmap.mediator.Mediator;
import de.geisslerbenjamin.jmap.mediator.interfaces.EventInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.ListenerInterface;
import de.geisslerbenjamin.jmap.mediator.interfaces.MediatorInterface;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.PropertyConfigurator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Main factory to create new objects.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Factory implements FactoryInterface, ListenerInterface {
    private String configPath;
    private ConfigurationInterface config;
    private MediatorInterface mediator;
    private Stage stage;
    private Pane root;
    private ScrollPane imagePane;
    private TranslationInterface translate;
    private DataContainerInterface dataContainer;
    private Map<String, ElementFactoryInterface> factories;
    private DrawableConfigurationInterface drawableStandardConfig;


    public Factory(Stage stage, Pane root, String path) throws Exception {
        this.configPath = path + "config.yml";
        this.stage = stage;
        this.root = root;
        this.config = Reader.read(this.configPath);
        this.mediator = new Mediator();
        this.translate = new Translation(path + "translation_" + this.config.getLanguage().getLanguage() + ".yml");
        this.dataContainer = DataContainer.get();
        this.factories = new HashMap<>();

        // init the logger
        PropertyConfigurator.configure(path + "log4j.properties");

        // init information dialogs
        new Information(
                this.stage,
                this.mediator,
                this.translate.translate("info.id"),
                this.translate.translate("info.title")
        );

        // standard configuration for new elements
        this.drawableStandardConfig = new DrawableConfiguration(0, 300, 300, Color.RED, "");

        this.mediator
                .register("elements.reDraw", this)
                .register("configuration.changed", this);
    }

    @Override
    public FactoryInterface addElementFactory(ElementFactoryInterface factory) {
        this.factories.put(factory.getName(), factory);
        factory
                .setMainFactory(this)
                .registerListener();

        return this;
    }


    @Override
    public TranslationInterface getTranslate() {
        return this.translate;
    }

    @Override
    public MediatorInterface getMediator() {
        return mediator;
    }

    @Override
    public TableDataGatewayInterface getTableDataGateway() {
        TableDataGatewayInterface gateway = new TableDataGateway(
                this.config.getDataSource().getFile(),
                this.config.getDataSource().getTable()
        );

        for (ElementFactoryInterface factory : this.factories.values()) {
            gateway.addParser(factory.getName(), factory.getParser());
        }

        return gateway;
    }

    @Override
    public DataContainerInterface getDataContainer() {
        return dataContainer;
    }

    @Override
    public void createBackgroundImage() {
        this.imagePane = new BackgroundImage(this.config.getImage(), this.mediator).draw();
    }

    @Override
    public ScrollPane getImagePane() {
        return this.imagePane;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public DrawableDisplayConfigurationInterface getDrawableDisplayConfiguration() {
        return DrawableDisplayConfiguration.get(1.0, false, false, true, this.getMediator());
    }

    @Override
    public DrawableConfigurationInterface getDrawableStandardConfig() {
        return drawableStandardConfig;
    }

    @Override
    public CreateDialogInterface getCreateDialog(String element) {
        if (this.factories.containsKey(element)) {
            return this.factories.get(element).getCreateDialog();
        }

        return null;
    }

    @Override
    public RowCreatorInterface getRowCreator() {
        return new RowCreator(this.getTableDataGateway(), this.getMediator(), this.getDataContainer());
    }

    @Override
    public DialogInterface getConfigurationDialog() {
        return new Dialog(this.stage, this.config, this.translate, new Writer(this.mediator, this.configPath));
    }

    @Override
    public Set<String> getElementNames() {
        return this.factories.keySet();
    }

    @Override
    public boolean exec(String name, EventInterface event) {
        switch (name) {
            case "elements.reDraw":
                Display.draw(this.root, this.getImagePane(), this.getDataContainer().all());
                return true;
            case "configuration.changed":
                // display background image
                this.createBackgroundImage();

                // load all elements
                this.getDataContainer().set(this.getTableDataGateway().findAll());
                this.getMediator().dispatch("elements.reDraw");
                return true;
        }
        return false;
    }
}
