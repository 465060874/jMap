package de.geisslerbenjamin.jkmap.configuration;

import de.geisslerbenjamin.jkmap.configuration.interfaces.ConfigurationInterface;
import de.geisslerbenjamin.jkmap.configuration.interfaces.DialogInterface;
import de.geisslerbenjamin.jkmap.configuration.interfaces.TranslationInterface;
import de.geisslerbenjamin.jkmap.configuration.interfaces.WriterInterface;
import de.geisslerbenjamin.jkmap.data.Validation;
import de.geisslerbenjamin.jkmap.dialog.ValidateField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.util.ArrayList;

/**
 * Display a dialog to change the configuration values.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Dialog implements DialogInterface {
    private Stage stage;
    private ConfigurationInterface config;
    private TranslationInterface translate;
    private WriterInterface writer;
    private boolean isValid;
    private ArrayList<String> errorMessages;

    /**
     * Constructor
     *
     * @param stage
     * @param config
     * @param translate
     * @param writer
     */
    public Dialog(Stage stage, ConfigurationInterface config, TranslationInterface translate, WriterInterface writer) {
        this.stage = stage;
        this.config = config;
        this.translate = translate;
        this.writer = writer;
    }

    /**
     * Display a dialog to change the configuration values.
     */
    public void display() {
        this.isValid = true;
        this.errorMessages = new ArrayList<>();

        if (this.displayDialog()) {
            if (isValid) {
                this.writer.updateConfiguration(this.config);
            } else {
                Dialogs.showErrorDialog(
                        stage,
                        translate.translate(errorMessages),
                        translate.translate("dialog.error"),
                        translate.translate("dialog.error")
                );
                this.display();
            }
        }
    }

    /**
     * Displays the dialog to change the configuration values.
     *
     * @return true if OK button is clicked, otherwise false
     */
    private boolean displayDialog() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // image file
        final TextField image = new TextField();
        image.setPromptText(this.translate.translate("config.image.path"));
        image.setText(this.config.getImage().getPath());
        image.setEditable(false);

        final FileChooser imageChooser = new FileChooser();
        imageChooser.setTitle(translate.translate("config.image.fileChooser"));
        imageChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(translate.translate("config.image.types"), "*.*"),
                new FileChooser.ExtensionFilter("jpg", "*.jpg"),
                new FileChooser.ExtensionFilter("png", "*.png")
        );

        Button imageButton = new Button();
        imageButton.setText(this.translate.translate("config.image.select"));
        imageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                File file = imageChooser.showOpenDialog(stage);
                if (file != null) {
                    image.setText(file.getPath());
                }
            }
        });

        grid.add(new Label(this.translate.translate("config.image.path") + ":"), 0, 0);
        grid.add(image, 1, 0);
        grid.add(imageButton, 2, 0);


        // select the access database
        final TextField database = new TextField();
        database.setPromptText(this.translate.translate("config.dataSource.file"));
        database.setText(this.config.getDataSource().getFile());
        database.setEditable(false);

        final FileChooser databaseChooser = new FileChooser();
        databaseChooser.setTitle(translate.translate("config.dataSource.fileChooser"));
        databaseChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("mdb", "*.mdb")
        );

        Button databaseButton = new Button();
        databaseButton.setText(this.translate.translate("config.dataSource.select"));
        databaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                File file = databaseChooser.showOpenDialog(stage);
                if (file != null) {
                    database.setText(file.getPath());
                }
            }
        });

        grid.add(new Label(this.translate.translate("config.dataSource.file") + ":"), 0, 1);
        grid.add(database, 1, 1);
        grid.add(databaseButton, 2, 1);

        // enter name of the access table
        final TextField table = new TextField();
        table.setPromptText(this.translate.translate("config.dataSource.table"));
        table.setText(this.config.getDataSource().getTable());

        grid.add(new Label(this.translate.translate("config.dataSource.table") + ":"), 0, 3);
        grid.add(table, 1, 3);

        // the factor
        final TextField factor = new TextField();
        factor.setPromptText(this.translate.translate("config.image.factor"));
        factor.setText(Double.toString(this.config.getImage().getFactor()));

        grid.add(new Label(this.translate.translate("config.image.factor")), 0, 4);
        grid.add(factor, 1, 4);

        // the width
        final TextField width = new TextField();
        width.setPromptText(this.translate.translate("config.image.width"));
        width.setText(Integer.toString(this.config.getImage().getWidth()));

        grid.add(new Label(this.translate.translate("config.image.width") + ":"), 0, 5);
        grid.add(width, 1, 5);

        // the height
        final TextField height = new TextField();
        height.setPromptText(this.translate.translate("config.image.height"));
        height.setText(Integer.toString(this.config.getImage().getHeight()));

        grid.add(new Label(this.translate.translate("config.image.height") + ":"), 0, 6);
        grid.add(height, 1, 6);

        Callback<Void, Void> myCallback = new Callback<Void, Void>() {
            @Override
            public Void call(Void param) {
                ValidateField validation = new ValidateField();

                if (!validation.isFile(image.getText())) {
                    errorMessages.add("config.error.image");
                    isValid = false;
                }

                if (!validation.isFile(database.getText())) {
                    errorMessages.add("config.error.database");
                    isValid = false;
                }

                if (table.getText().isEmpty()
                        || !new Validation().isTableInDatabase(database.getText(), table.getText())) {
                    errorMessages.add("config.error.table");
                    isValid = false;
                }

                if (!validation.isDouble(factor.getText())) {
                    errorMessages.add("config.error.factor");
                    isValid = false;
                }

                if (!validation.isInteger(width.getText())) {
                    errorMessages.add("config.error.width");
                    isValid = false;
                }

                if (!validation.isInteger(height.getText())) {
                    errorMessages.add("config.error.height");
                    isValid = false;
                }

                if (isValid) {
                    // change the configuration values
                    config.getImage()
                            .setPath(image.getText())
                            .setFactor(Double.parseDouble(factor.getText().replace(",", ".")))
                            .setWidth(Integer.parseInt(width.getText()))
                            .setHeight(Integer.parseInt(height.getText()));
                    config.getDataSource()
                            .setFile(database.getText())
                            .setTable(table.getText());
                }

                return null;
            }
        };

        String title = this.translate.translate("config.title");
        Dialogs.DialogResponse response = Dialogs.showCustomDialog(
                stage,
                grid,
                title,
                title,
                Dialogs.DialogOptions.OK_CANCEL,
                myCallback
        );

        return response == Dialogs.DialogResponse.OK;
    }
}
