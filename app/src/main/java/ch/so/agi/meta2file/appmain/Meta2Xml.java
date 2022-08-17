package ch.so.agi.meta2file.appmain;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;

public class Meta2Xml {
    /**
     * Exports the xml "config" file for the data search application.
     * @param con Connection to meta db.
     * @param xmlFile Destination path for the xml file.
     */
    public static void exportAppFile(Connection con, File xmlFile){

    }

    /**
     * Exports the iso-ch xml files for geocat.
     * Geocat needs one xml per ThemePublication.
     * @param con Connection to meta db.
     * @param xmlFolder Filesys folder to export the xml files into.
     */
    public static void exportGeocatFiles(Connection con, Path xmlFolder){

    }
}
