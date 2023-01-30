package ch.so.agi.meta2file.libmain;

import ch.so.agi.meta2file.except.Meta2FileException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public enum BaseUrl {
    WGC(new String[]{"https://geo.so.ch/map", "https://geo-i.so.ch/map", "https://geo-t.so.ch/map"}),
    WMS(new String[]{"https://geo.so.ch/api/wms","https://geo-i.so.ch/api/wms","https://geo-t.so.ch/api/wms"}),
    WFS(new String[]{"https://geo.so.ch/api/wfs","https://geo-i.so.ch/api/wfs","https://geo-t.so.ch/api/wfs"}),
    FILES(new String[]{"https://files.geo.so.ch","https://files-t.geo.so.ch","https://files-t.geo.so.ch"}),
    LICENCE(new String[]{"https://files.geo.so.ch/nutzungsbedingungen.html","https://files-t.geo.so.ch/nutzungsbedingungen.html","https://files-t.geo.so.ch/nutzungsbedingungen.html"}),
    DATA_SERVICE(new String[]{"https://geo.so.ch/api/data/v1","https://geo-i.so.ch/api/data/v1","https://geo-t.so.ch/api/data/v1"}),
    DATA_APP(new String[]{"https://data.geo.so.ch","https://data-i.geo.so.ch","https://data-t.geo.so.ch"});


    public static final int PROD_ENV = 0;
    public static final int INT_ENV = 1;
    public static final int TEST_ENV = 2;

    private final String[] urlString;

    private BaseUrl(String[] urlString) {
        this.urlString = urlString;
    }

    /*
    private URL getBaseUrl(){
        URL res = null;

        try {
            res = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new Meta2FileException(e);
        }

        return res;
    }*/

    public URI getBaseUrlAsUri(Environment env){
        URI res = null;

        try {
            URL u = new URL(urlString[env.getPropertyIdx()]);
            res = u.toURI();
        }
        catch (MalformedURLException e) {
            throw new Meta2FileException(e);
        }
        catch (URISyntaxException e) {
            throw new Meta2FileException(e);
        }

        return res;
    }

    /*
    public URI getBaseUrlAsUri(int env){
        URI res = null;

        try {
            URL u = new URL(urlString[env]);
            res = u.toURI();
        }
        catch (MalformedURLException e) {
            throw new Meta2FileException(e);
        }
        catch (URISyntaxException e) {
            throw new Meta2FileException(e);
        }

        return res;
    }*/
}
