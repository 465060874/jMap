package de.geisslerbenjamin.jmap;

import de.geisslerbenjamin.jmap.factory.Factory;
import de.geisslerbenjamin.jmap.mediator.DisplayConfigurationEvent;
import de.geisslerbenjamin.jmap.mediator.ZoomEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

/**
 * Initial stage.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class JMap extends Application {

    @Override
    public void start(Stage primaryStage) {
        final Factory factory;
        try {
            // init basic display elements
            final VBox main = new VBox();
            final Pane root = new Pane();

            // get fabric to create objects
            factory = new Factory(primaryStage, root, new File(".").getCanonicalPath() + "/config/");
            factory
                    // add rectangle support
                    .addElementFactory(new de.geisslerbenjamin.jmap.rectangle.Factory());

            // create navigation menu
            MenuBar menuBar = new MenuBar();

            // load data
            Menu menuData = new Menu(factory.getTranslate().translate("data"));
            MenuItem load = new MenuItem(factory.getTranslate().translate("data.load"));
            load.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));
            load.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent mouseEvent) {
                    // display background image and load all elements
                    factory.getMediator().dispatch("configuration.changed");
                }
            });
            MenuItem exit = new MenuItem(factory.getTranslate().translate("exit"));
            exit.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    System.exit(0);
                }
            });

            menuData.getItems().addAll(load, new SeparatorMenuItem(), exit);
            menuBar.getMenus().add(menuData);

            // Menu to create new objects
            Menu menuElement = new Menu(factory.getTranslate().translate("menu.element.new"));
            for (final String elementName : factory.getElementNames()) {
                MenuItem menuElementCreate = new MenuItem(factory.getTranslate().translate("menu.element." + elementName));
                menuElementCreate.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        factory.getCreateDialog(elementName).create();
                    }
                });
                menuElement.getItems().add(menuElementCreate);
            }
            menuBar.getMenus().add(menuElement);

            // Menu to zoom in and out
            Menu menuImage = new Menu(factory.getTranslate().translate("image"));
            MenuItem menuImageZoomIn = new MenuItem(factory.getTranslate().translate("zoom.in"));
            menuImageZoomIn.setAccelerator(new KeyCodeCombination(KeyCode.PLUS, KeyCombination.CONTROL_DOWN));
            menuImageZoomIn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    factory.getMediator().dispatch(
                            "image.zoom.in",
                            new ZoomEvent(factory.getDrawableDisplayConfiguration().getZoom() + 0.1)
                    );
                }
            });
            MenuItem menuImageZoomOut = new MenuItem(factory.getTranslate().translate("zoom.out"));
            menuImageZoomOut.setAccelerator(new KeyCodeCombination(KeyCode.MINUS, KeyCombination.CONTROL_DOWN));
            menuImageZoomOut.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (factory.getDrawableDisplayConfiguration().getZoom() > 1.0) {
                        factory.getMediator().dispatch(
                                "image.zoom.out",
                                new ZoomEvent(factory.getDrawableDisplayConfiguration().getZoom() - 0.1)
                        );
                    }
                }
            });
            MenuItem menuImageZoomReset = new MenuItem(factory.getTranslate().translate("zoom.reset"));
            menuImageZoomReset.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    factory.getMediator().dispatch("image.zoom.out", new ZoomEvent(1.0));
                }
            });

            menuImage.getItems().addAll(menuImageZoomIn, menuImageZoomOut, menuImageZoomReset);
            menuBar.getMenus().add(menuImage);

            // control menu
            Menu menuControl = new Menu(factory.getTranslate().translate("control"));
            MenuItem menuControlMove = new MenuItem(factory.getTranslate().translate("control.move"));
            menuControlMove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    factory.getMediator().dispatch("display.move", new DisplayConfigurationEvent(true, false, false));
                }
            });
            MenuItem menuControlEdit = new MenuItem(factory.getTranslate().translate("control.edit"));
            menuControlEdit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    factory.getMediator().dispatch("display.edit", new DisplayConfigurationEvent(false, true, false));
                }
            });
            MenuItem menuControlInfo = new MenuItem(factory.getTranslate().translate("control.info"));
            menuControlInfo.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    factory.getMediator().dispatch("display.info", new DisplayConfigurationEvent(false, false, true));
                }
            });
            menuControl.getItems().addAll(menuControlMove, menuControlEdit, menuControlInfo);
            menuBar.getMenus().add(menuControl);

            // configuration menu
            Menu menuConfig = new Menu(factory.getTranslate().translate("menu.config"));
            MenuItem menuConfigEdit = new MenuItem(factory.getTranslate().translate("menu.config.edit"));
            menuConfigEdit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    factory.getConfigurationDialog().display();
                }
            });
            menuConfig.getItems().add(menuConfigEdit);
            menuBar.getMenus().add(menuConfig);

            // add menu and root pane to scene
            Scene scene = new Scene(main);
            main.getChildren().addAll(menuBar);
            main.getChildren().add(root);


            // maximize window
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            primaryStage.setX(bounds.getMinX());
            primaryStage.setY(bounds.getMinY());
            primaryStage.setWidth(bounds.getWidth());
            primaryStage.setHeight(bounds.getHeight());

            primaryStage.setTitle(factory.getTranslate().translate("title"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception error) {
            System.err.println(error.getMessage());
            System.exit(-1);
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
