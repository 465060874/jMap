package de.geisslerbenjamin.jkmap.data;

import de.geisslerbenjamin.jkmap.data.interfaces.DataContainerInterface;
import de.geisslerbenjamin.jkmap.drawable.interfaces.DrawableInterface;

import java.util.ArrayList;

/**
 * Data container holding all drawables.
 *
 * @author Benjamin Geißler <benjamin.geissler@gmail.com>
 * @licence MIT
 */
public class DataContainer implements DataContainerInterface {
    private static DataContainer self;
    private ArrayList<DrawableInterface> drawables;

    private DataContainer() {
        this.clear();
    }

    public static DataContainer get() {
        if (self == null) {
            self = new DataContainer();
        }

        return self;
    }

    @Override
    public DataContainerInterface add(DrawableInterface drawable) {
        this.drawables.add(drawable);
        return this;
    }

    @Override
    public DataContainerInterface set(ArrayList<DrawableInterface> drawables) {
        this.drawables = drawables;
        return this;
    }

    @Override
    public ArrayList<DrawableInterface> all() {
        return this.drawables;
    }

    @Override
    public DrawableInterface get(int id) {
        for (DrawableInterface drawable : this.drawables) {
            if (drawable.getId() == id) {
                return drawable;
            }
        }

        return null;
    }

    @Override
    public void clear() {
        this.drawables = new ArrayList<>();
    }
}
