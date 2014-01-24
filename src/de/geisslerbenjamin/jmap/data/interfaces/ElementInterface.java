/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.geisslerbenjamin.jmap.data.interfaces;

import de.geisslerbenjamin.jmap.drawable.interfaces.DrawableInterface;

/**
 *
 * @author Benjamin
 */
public interface ElementInterface {
    public int getId();

    public DrawableInterface getDisplay();

    public DataInterface getData();
}
