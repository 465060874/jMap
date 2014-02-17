package de.geisslerbenjamin.jkmap.data.interfaces;

import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableInterface;

import java.util.ArrayList;

/**
 * A container holding all drawables.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public interface DataContainerInterface {
    public DataContainerInterface add(DrawableInterface drawable);

    public DataContainerInterface set(ArrayList<DrawableInterface> drawables);

    public ArrayList<DrawableInterface> all();

    public DrawableInterface get(int id);

    public void clear();
}
