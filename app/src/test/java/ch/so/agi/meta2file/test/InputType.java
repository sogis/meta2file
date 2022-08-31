package ch.so.agi.meta2file.test;

/**
 * Contains the keys for all main test case classes.
 *
 * The classes are oriented along the main characteristics of the
 * published data (Tabular with and without geometry, other (Raster, ...)
 */
public enum InputType {

    VEC_ALL, // Complete but small-sized example json containing two tables and two items
    VEC_MANDATORY, // One table with geometry (and bbox) but missing all optional values
    TAB_ALL, // One class without geometry, with complete attribute description
    TAB_MANDATORY, // One class without geometry, with complete attribute description
    OTHER_ALL,
    OTHER_MANDATORY;

    public String deferJsonFileName(){
        return deferPathPartName() + ".json";
    }

    public String deferPathPartName(){
        return name().toLowerCase();
    }
}