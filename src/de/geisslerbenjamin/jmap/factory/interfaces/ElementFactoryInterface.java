package de.geisslerbenjamin.jmap.factory.interfaces;

import de.geisslerbenjamin.jmap.data.ParseDrawable;
import de.geisslerbenjamin.jmap.dialog.interfaces.CreateDialogInterface;
import de.geisslerbenjamin.jmap.dialog.interfaces.EditDialogInterface;

/**
 * Description.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface ElementFactoryInterface {
    public String getName();

    public ElementFactoryInterface setMainFactory(FactoryInterface mainFactory);

    public ElementFactoryInterface registerListener();

    public CreateDialogInterface getCreateDialog();

    public EditDialogInterface getEditDialog();

    public ParseDrawable getParser();

}
