/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.geisslerbenjamin.jmap;

import de.geisslerbenjamin.jmap.configuration.Reader;
import de.geisslerbenjamin.jmap.configuration.interfaces.ConfigurationInterface;
import de.geisslerbenjamin.jmap.data.DataContainer;
import de.geisslerbenjamin.jmap.data.Element;
import de.geisslerbenjamin.jmap.data.interfaces.ElementInterface;
import de.geisslerbenjamin.jmap.drawable.Rectangle;
import java.io.File;
import java.util.concurrent.ExecutionException;

import de.geisslerbenjamin.jmap.drawable.interfaces.ImageInterface;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Benjamin
 */
public class JMap extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        
        final VBox main = new VBox();       
        //final StackPane root = new StackPane();
        final Pane root = new Pane();

        /*
        final Canvas canvas = new Canvas(300, 300);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setLineWidth(5);
        gc.setStroke(Color.PURPLE);
        gc.strokeRect(30, 100, 40, 40);
        
        
        class Delta { double x, y;}
        final Delta movedBy = new Delta();
        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                // x, y
                movedBy.x = canvas.getLayoutX() - t.getSceneX();
                movedBy.y = canvas.getLayoutY() - t.getSceneY();
                canvas.setCursor(Cursor.HAND);
            }
        });

        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                canvas.setLayoutX(t.getSceneX() + movedBy.x);
                canvas.setLayoutY(t.getSceneY() + movedBy.y);
            }
        });
        
        root.getChildren().add(canvas);
        */
        
        final DataContainer data = DataContainer.get();
        
        MenuBar menuBar = new MenuBar();
        Menu menuData = new Menu("Daten");
        MenuItem load = new MenuItem("Daten laden");
        load.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent mouseEvent) {
                String pathToConfig;
                ConfigurationInterface config;


                try {
                    // parse config file and create config object
                    pathToConfig = new File (".").getCanonicalPath() +  "/config/config.yml";
                    config = Reader.read(pathToConfig);

                    // display background image
                    ImageInterface image = new de.geisslerbenjamin.jmap.drawable.Image(config.getImage());
                    root.getChildren().add(image.draw());

                    data
                        .addElement(new Element(1, new Rectangle(340.0, 269.0, 13, 15, Color.RED)))
                        .addElement(new Element(2, new Rectangle(328.0, 437.0, 25, 25, Color.GREEN)))
                        .addElement(new Element(3, new Rectangle(332.5, 362.5, 74, 25, Color.BLUE)))
                        .addElement(new Element(4, new Rectangle(357.5, 290.5, 70, 25, Color.VIOLET)));


                    for (ElementInterface element : data.getAll()) {
                        root.getChildren().add(element.getDisplay().draw());
                    }

                } catch (Exception exception) {
                    pathToConfig = exception.getMessage();

                }
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.setScene(
                        new Scene(
                                VBoxBuilder
                                    .create()
                                    .children(
                                            new Text(pathToConfig)
                                    )
                                    .build()
                        )
                );
                dialogStage.show();




            }
        });
        MenuItem exit = new MenuItem("Beenden");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        
        menuData.getItems().addAll(load, new SeparatorMenuItem(), exit);
        menuBar.getMenus().add(menuData);


        Menu menuElement = new Menu("Element");
        MenuItem menuElementCreate = new MenuItem("Erstellen");
        menuElementCreate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ElementInterface element = new Element(5, new Rectangle(500, 500, 25, 25, Color.AQUAMARINE));
                root.getChildren().add(element.getDisplay().draw());
            }
        });
        menuElement.getItems().add(menuElementCreate);
        menuBar.getMenus().add(menuElement);
        
        /*
        
        for (ElementInterface element : data.getAll()) {
            root.getChildren().add(element.getDisplay().draw());    
        }
        
        /*
        Rectangle next = new Rectangle(30, 100, 50, 50, Color.RED);        
        root.getChildren().add(next.draw());
        Rectangle second = new Rectangle(34, 10, 75, 25, Color.GREEN);
        root.getChildren().add(second.draw());
        */
        
        Scene scene = new Scene(main);
        main.getChildren().addAll(menuBar);
        main.getChildren().add(root);
        
        /*
        Image image = new Image("file:D:\\Projekte\\Java\\map\\Data\\map.jpg");        
        ImageView imgView = new ImageView(image);
        int width = 1000;
        double factor = image.getWidth() / width;

        imgView.fitHeightProperty().setValue(image.getHeight() / factor);
        imgView.fitWidthProperty().setValue(width);
           */



        //root.getChildren().add(imgView);


        
        //((VBox) scene.getRoot()).getChildren().addAll(menuBar);
        
        // maximize window
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
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
