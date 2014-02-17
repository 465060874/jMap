package de.geisslerbenjamin.jkmap.factory;

import de.geisslerbenjamin.jkmap.configuration.Dialog;
import de.geisslerbenjamin.jkmap.configuration.Reader;
import de.geisslerbenjamin.jkmap.configuration.Translation;
import de.geisslerbenjamin.jkmap.configuration.Writer;
import de.geisslerbenjamin.jkmap.configuration.interfaces.ConfigurationInterface;
import de.geisslerbenjamin.jkmap.configuration.interfaces.DialogInterface;
import de.geisslerbenjamin.jkmap.configuration.interfaces.TranslationInterface;
import de.geisslerbenjamin.jkmap.data.DataContainer;
import de.geisslerbenjamin.jkmap.data.RowCreator;
import de.geisslerbenjamin.jkmap.data.TableDataGateway;
import de.geisslerbenjamin.jkmap.data.interfaces.DataContainerInterface;
import de.geisslerbenjamin.jkmap.data.interfaces.RowCreatorInterface;
import de.geisslerbenjamin.jkmap.data.interfaces.TableDataGatewayInterface;
import de.geisslerbenjamin.jkmap.dialog.Information;
import de.geisslerbenjamin.jkmap.dialog.interfaces.CreateDialogInterface;
import de.geisslerbenjamin.jkmap.drawable.*;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableConfigurationInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableDisplayConfigurationInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.SaveInterface;
import de.geisslerbenjamin.jkmap.factory.interfaces.ElementFactoryInterface;
import de.geisslerbenjamin.jkmap.factory.interfaces.FactoryInterface;
import de.geisslerbenjamin.jkmap.mediator.Mediator;
import de.geisslerbenjamin.jkmap.mediator.interfaces.EventInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.ListenerInterface;
import de.geisslerbenjamin.jkmap.mediator.interfaces.MediatorInterface;
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
        this.drawableStandardConfig = new DrawableConfiguration(0, 300, 300, 0, Color.RED, "");

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
        return DrawableDisplayConfiguration.get(
                1.0,
                this.config.getImage().getFactorForImageSize(),
                false,
                false,
                true,
                this.getMediator()
        );
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
    public SaveInterface getSaveDialog() {
        return new Save(this.stage, this.getImagePane(), this.config.getImage(), this.mediator, this.translate.translate("menu.data.save.dialog"));
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
