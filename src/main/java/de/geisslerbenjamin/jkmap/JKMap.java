package de.geisslerbenjamin.jkmap;

import de.geisslerbenjamin.jkmap.factory.Factory;
import de.geisslerbenjamin.jkmap.mediator.DisplayConfigurationEvent;
import de.geisslerbenjamin.jkmap.mediator.ZoomEvent;
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
 * Initial and only stage.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class JKMap extends Application {

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
                    .addElementFactory(new de.geisslerbenjamin.jkmap.rectangle.Factory())
                            // add circle support
                    .addElementFactory(new de.geisslerbenjamin.jkmap.circle.Factory())
                            // add semi-circle support
                    .addElementFactory(new de.geisslerbenjamin.jkmap.semicircle.Factory())
            ;

            // create navigation menu
            MenuBar menuBar = new MenuBar();

            // load data
            Menu menuData = new Menu(factory.getTranslate().translate("menu.data"));
            MenuItem menuDataLoad = new MenuItem(factory.getTranslate().translate("menu.data.load"));
            menuDataLoad.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));
            menuDataLoad.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent mouseEvent) {
                    // display background image and load all elements
                    factory.getMediator().dispatch("configuration.changed");
                }
            });

            // save image
            MenuItem menuDataSave = new MenuItem(factory.getTranslate().translate("menu.data.save"));
            menuDataSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
            menuDataSave.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent mouseEvent) {
                    factory.getSaveDialog().dialog();
                }
            });

            // open Microsoft Access
            MenuItem menuDataAccess = new MenuItem(factory.getTranslate().translate("menu.data.access"));
            menuDataAccess.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
            menuDataAccess.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent mouseEvent) {
                    factory.openFile();
                }
            });

            // exit
            MenuItem menuDataExit = new MenuItem(factory.getTranslate().translate("menu.data.exit"));
            menuDataExit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
            menuDataExit.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    System.exit(0);
                }
            });

            menuData.getItems().addAll(menuDataLoad, menuDataSave, menuDataAccess, new SeparatorMenuItem(), menuDataExit);
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
            Menu menuImage = new Menu(factory.getTranslate().translate("menu.zoom"));

            MenuItem menuImageZoomIn = new MenuItem(factory.getTranslate().translate("menu.zoom.in"));
            menuImageZoomIn.setAccelerator(new KeyCodeCombination(KeyCode.UP, KeyCombination.CONTROL_DOWN));
            menuImageZoomIn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    factory.getMediator().dispatch(
                            "image.zoom.in",
                            new ZoomEvent(factory.getDrawableDisplayConfiguration().getZoom() + 0.1)
                    );
                }
            });
            MenuItem menuImageZoomOut = new MenuItem(factory.getTranslate().translate("menu.zoom.out"));
            menuImageZoomOut.setAccelerator(new KeyCodeCombination(KeyCode.DOWN, KeyCombination.CONTROL_DOWN));
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
            MenuItem menuImageZoomReset = new MenuItem(factory.getTranslate().translate("menu.zoom.reset"));
            menuImageZoomReset.setAccelerator(new KeyCodeCombination(KeyCode.DIGIT0, KeyCombination.CONTROL_DOWN));
            menuImageZoomReset.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    factory.getMediator().dispatch("image.zoom.out", new ZoomEvent(1.0));
                }
            });

            menuImage.getItems().addAll(menuImageZoomIn, menuImageZoomOut, menuImageZoomReset);
            menuBar.getMenus().add(menuImage);

            // control menu
            Menu menuControl = new Menu(factory.getTranslate().translate("menu.control"));
            MenuItem menuControlMove = new MenuItem(factory.getTranslate().translate("menu.control.move"));
            menuControlMove.setAccelerator(new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN));
            menuControlMove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    factory.getMediator().dispatch("display.move", new DisplayConfigurationEvent(true, false, false));
                }
            });
            MenuItem menuControlEdit = new MenuItem(factory.getTranslate().translate("menu.control.edit"));
            menuControlEdit.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
            menuControlEdit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    factory.getMediator().dispatch("display.edit", new DisplayConfigurationEvent(false, true, false));
                }
            });
            MenuItem menuControlInfo = new MenuItem(factory.getTranslate().translate("menu.control.info"));
            menuControlInfo.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
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
            menuConfigEdit.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
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
