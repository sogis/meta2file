package ch.so.agi.meta2file.libmain;

import ch.so.agi.meta2file.except.Meta2FileException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public enum BaseUrl {
    WGC("https://geo.so.ch/map"),
    WMS("https://geo.so.ch/api/wms"),
    WFS("https://geo.so.ch/api/wfs"),
    FILES("https://files.geo.so.ch"),
    LICENCE("https://geo.so.ch/licence"),
    DATA_SERVICE("https://geo.so.ch/api/data/v1"),
    DATA_APP("https://data.geo.so.ch");


    public final String urlString;

    private BaseUrl(String urlString) {
        this.urlString = urlString;
    }

    public URL getBaseUrl(){
        URL res = null;

        try {
            res = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new Meta2FileException(e);
        }

        return res;
    }

    public URI getBaseUrlAsUri(){
        URI res = null;

        try {
            res = getBaseUrl().toURI();
        } catch (URISyntaxException e) {
            throw new Meta2FileException(e);
        }

        return res;
    }
}
