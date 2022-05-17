package ch.so.agi.metabean2file;

import ch.so.agi.metabean2file.model.ThemePublication;

public interface StacCreator {
    public void create(String collectionFilePath, ThemePublication themePublication);
}
