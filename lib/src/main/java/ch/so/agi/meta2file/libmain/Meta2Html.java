package ch.so.agi.meta2file.libmain;

import ch.so.agi.meta2file.in.Read;
import ch.so.agi.meta2file.model.ThemePublication;
import ch.so.agi.meta2file.out.MetaBean2FileConverter;

import java.sql.Connection;
import java.util.UUID;

public class Meta2Html {
    /**
     * Renders the data description for a theme publication.
     * @param themePubId Theme publication id in meta db.
     * @param con Connection to meta db.
     */
    public static String renderDataSheet(UUID themePubId, Connection con, Environment env){
        ThemePublication tp = Read.fromMetaDb(con, themePubId, env);
        String html = MetaBean2FileConverter.runBean2Html(tp);
        return html;
    }

    /**
     *
     * @param inJson The json data content to be rendered
     * @return The rendered HTML-Page content as String
     */
    static String renderDataSheet(String inJson, Environment env){
        ThemePublication tp = Read.fromJson(inJson, env);
        String html = MetaBean2FileConverter.runBean2Html(tp);

        return html;
    }
}
