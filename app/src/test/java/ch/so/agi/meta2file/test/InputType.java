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
    VEC_REALDATA_SINGLEREGION, // Json copied 1:1 from simi-db query. Has whole kanton as region
    VEC_REALDATA_MULTIREGION, // Json copied 1:1 from simi-db query. Has gemeinden as regions
    TAB_ALL, // Complete but small-sized example json containing two tables and two items without geometry
    TAB_MANDATORY, // One table without geometry, missing all optional values
    OTHER_ALL, // Complete but small-sized example of a raster dataset having two items
    OTHER_MANDATORY; // Complete but small-sized example of a raster dataset having two items, but missing all optional values

    public String deferJsonFileName(){
        return deferPathPartName() + ".json";
    }

    public String deferPathPartName(){
        return name().toLowerCase();
    }
}