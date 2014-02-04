package de.geisslerbenjamin.jmap.rectangle;

import de.geisslerbenjamin.jmap.data.ParseDrawable;
import de.geisslerbenjamin.jmap.dialog.interfaces.CreateDialogInterface;
import de.geisslerbenjamin.jmap.dialog.interfaces.EditDialogInterface;
import de.geisslerbenjamin.jmap.factory.interfaces.ElementFactoryInterface;
import de.geisslerbenjamin.jmap.factory.interfaces.FactoryInterface;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class Factory implements ElementFactoryInterface {
    private FactoryInterface mainFactory;

    @Override
    public ElementFactoryInterface setMainFactory(FactoryInterface mainFactory) {
        this.mainFactory = mainFactory;
        return this;
    }

    @Override
    public ElementFactoryInterface registerListener() {
        new Listener(this.mainFactory.getMediator(), this.getEditDialog(), this.mainFactory.getTableDataGateway());

        return this;
    }

    @Override
    public String getName() {
        return "rectangle";
    }

    @Override
    public CreateDialogInterface getCreateDialog() {
        return new CreateDialog(
                this.mainFactory.getStage(),
                this.mainFactory.getTranslate(),
                this.mainFactory.getDrawableStandardConfig(),
                this.mainFactory.getRowCreator()
        );
    }

    public EditDialogInterface getEditDialog() {
        return new EditDialog(
                this.mainFactory.getStage(),
                this.mainFactory.getTranslate(),
                this.mainFactory.getMediator(),
                this.mainFactory.getTableDataGateway()
        );
    }

    @Override
    public ParseDrawable getParser() {
        return new RowParser(
                this.mainFactory.getDrawableDisplayConfiguration(),
                this.mainFactory.getMediator()
        );
    }
}
