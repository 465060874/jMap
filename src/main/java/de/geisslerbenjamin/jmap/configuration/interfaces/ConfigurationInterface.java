/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.geisslerbenjamin.jmap.configuration.interfaces;

/**
 * @author Benjamin
 */
public interface ConfigurationInterface {
    public ImageInterface getImage();

    public DataSourceInterface getDataSource();

    public LanguageInterface getLanguage();
}
