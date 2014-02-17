package de.geisslerbenjamin.jkmap.circle;

import de.geisslerbenjamin.jkmap.data.ParseDrawable;
import de.geisslerbenjamin.jkmap.dialog.interfaces.CreateDialogInterface;
import de.geisslerbenjamin.jkmap.dialog.interfaces.EditDialogInterface;
import de.geisslerbenjamin.jkmap.factory.interfaces.ElementFactoryInterface;
import de.geisslerbenjamin.jkmap.factory.interfaces.FactoryInterface;

/**
 * Factory for a circle.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Factory implements ElementFactoryInterface {
    private FactoryInterface factory;

    @Override
    public String getName() {
        return "circle";
    }

    @Override
    public ElementFactoryInterface setMainFactory(FactoryInterface mainFactory) {
        this.factory = mainFactory;
        return this;
    }

    @Override
    public ElementFactoryInterface registerListener() {
        new Listener(this.factory.getMediator(), this.getEditDialog(), this.factory.getTableDataGateway());
        return this;
    }

    @Override
    public CreateDialogInterface getCreateDialog() {
        return new CreateDialog(
                this.factory.getStage(),
                this.factory.getTranslate(),
                this.getName(),
                this.factory.getDrawableStandardConfig(),
                this.factory.getRowCreator()
        );
    }

    @Override
    public EditDialogInterface getEditDialog() {
        return new EditDialog(
                this.factory.getStage(),
                this.factory.getTranslate(),
                this.getName(),
                this.factory.getMediator(),
                this.factory.getTableDataGateway()
        );
    }

    @Override
    public ParseDrawable getParser() {
        return new RowParser(this.factory.getDrawableDisplayConfiguration(), this.factory.getMediator());
    }
}
