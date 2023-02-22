package ch.so.agi.meta2file;

import ch.so.agi.meta2file.model.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class TestUtils {
    public static HashMap<String,ThemePublication> getDatasets() {
        var df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        var themePublications = new HashMap<String,ThemePublication>();

        // bean2html_no_services_and_content_Ok 
        {
            var themePublication = new ThemePublication();
            themePublication.setIdentifier("ch.so.fantasy.island");
            themePublication.setModel("SO_AGI_AV_GB_Administrative_Einteilungen_Publikation_20180822");
            themePublication.setLastPublishingDate(LocalDate.parse("2023-01-05"));
            themePublication.setSecondToLastPublishingDate(LocalDate.parse("2022-01-17"));
            themePublication.setTitle("Fantasy Island");
            themePublication.setShortDescription("Lorem <b>ipsum</b> dolor sit amet, <blink>consetetur sadipscing</blink> elitr, <a href ='https://de.wikipedia.org/wiki/Rumours'>Warum nur...</a> sed diam nonumy eirmod tempor invidunt ut <acronym title='Founded in 2006'>Twitter</acronym> labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
            themePublication.setKeywordsList(Arrays.asList(new String[]{"AGI","Grundbuch","AS","AV","Amtliche Vermessung","Vermessung","Einteilung"}));
            
            Office owner = new Office();
            owner.setAgencyName("Amt für Umwelt");
            owner.setAbbreviation("AfU");
            owner.setDivision("Ich bin die Abteilung");
            try {
                owner.setOfficeAtWeb(new URI("https://afu.so.ch"));
                owner.setEmail(new URI("mailto:afu@bd.so.ch"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            owner.setPhone("032 627 12 34");
            themePublication.setOwner(owner);

            Office servicer = new Office();
            servicer.setAgencyName("Amt für Geoinformation");
            servicer.setAbbreviation("AGI");
            try {
                servicer.setOfficeAtWeb(new URI("https://agi.so.ch"));
                servicer.setEmail(new URI("mailto:agi@bd.so.ch"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            servicer.setPhone("032 627 75 96");
            themePublication.setServicer(servicer);
            
            try {
                themePublication.setFurtherInformation(new URI("http://google.ch/oder/wikipedia"));
                themePublication.setLicence(new URI("https://www.gl.ch/public/upload/assets/5053/ktgl-ogd-geo-20180601.pdf?fp=1"));
                themePublication.setDownloadHostUrl(new URI("https://files-t.geo.so.ch"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            
            List<FileFormat> fileFormats = new ArrayList<>();
            {
                FileFormat fileFormat = new FileFormat();
                fileFormat.setName("DXF");
                fileFormat.setAbbreviation("dxf");
                fileFormat.setMimetype("application/dxf+text");
                fileFormats.add(fileFormat);
            }        
            {
                FileFormat fileFormat = new FileFormat();
                fileFormat.setName("INTERLIS");
                fileFormat.setAbbreviation("ili");
                fileFormat.setMimetype("application/vnd.ili2+xml");
                fileFormats.add(fileFormat);
            }
            {
                FileFormat fileFormat = new FileFormat();
                fileFormat.setName("GeoPackage");
                fileFormat.setAbbreviation("gpkg");
                fileFormat.setMimetype("application/geopackage+sqlite3");
                fileFormats.add(fileFormat);
            }
            {
                FileFormat fileFormat = new FileFormat();
                fileFormat.setName("Shapefile");
                fileFormat.setAbbreviation("shp");
                fileFormat.setMimetype("application/vnd.esri_shapefile");
                fileFormats.add(fileFormat);
            }        
            themePublication.setFileFormats(fileFormats);
          
            
            themePublications.put(themePublication.getIdentifier(), themePublication);
        }
        
        
        
        // bean2html_Ok
        {
            var themePublication = new ThemePublication();
            themePublication.setIdentifier("ch.so.agi.av_gb_administrative_einteilung");
            themePublication.setModel("SO_AGI_AV_GB_Administrative_Einteilungen_Publikation_20180822");
            themePublication.setLastPublishingDate(LocalDate.parse("2023-01-05"));
            themePublication.setSecondToLastPublishingDate(LocalDate.parse("2022-01-17"));
            themePublication.setTitle("Administrative Einteilungen der amtlichen Vermessung und des Grundbuchs");
            themePublication.setShortDescription("Lorem <b>ipsum</b> dolor sit amet, <blink>consetetur sadipscing</blink> elitr, <a href ='https://de.wikipedia.org/wiki/Rumours'>Warum nur...</a> sed diam nonumy eirmod tempor invidunt ut <acronym title='Founded in 2006'>Twitter</acronym> labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
            themePublication.setKeywordsList(Arrays.asList(new String[]{"AGI","Grundbuch","AS","AV","Amtliche Vermessung","Vermessung","Einteilung"}));
            
            Office owner = new Office();
            owner.setAgencyName("Amt für Umwelt");
            owner.setAbbreviation("AfU");
            owner.setDivision("Ich bin die Abteilung");
            try {
                owner.setOfficeAtWeb(new URI("https://afu.so.ch"));
                owner.setEmail(new URI("mailto:afu@bd.so.ch"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            owner.setPhone("032 627 12 34");
            themePublication.setOwner(owner);

            Office servicer = new Office();
            servicer.setAgencyName("Amt für Geoinformation");
            servicer.setAbbreviation("AGI");
            try {
                servicer.setOfficeAtWeb(new URI("https://agi.so.ch"));
                servicer.setEmail(new URI("mailto:agi@bd.so.ch"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            servicer.setPhone("032 627 75 96");
            themePublication.setServicer(servicer);
            
            try {
                themePublication.setFurtherInformation(new URI("http://google.ch/oder/wikipedia"));
                themePublication.setLicence(new URI("https://www.gl.ch/public/upload/assets/5053/ktgl-ogd-geo-20180601.pdf?fp=1"));
                themePublication.setDownloadHostUrl(new URI("https://files-t.geo.so.ch"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            
            List<FileFormat> fileFormats = new ArrayList<>();
            {
                FileFormat fileFormat = new FileFormat();
                fileFormat.setName("DXF");
                fileFormat.setAbbreviation("dxf");
                fileFormat.setMimetype("application/dxf+text");
                fileFormats.add(fileFormat);
            }        
            {
                FileFormat fileFormat = new FileFormat();
                fileFormat.setName("INTERLIS");
                fileFormat.setAbbreviation("ili");
                fileFormat.setMimetype("application/vnd.ili2+xml");
                fileFormats.add(fileFormat);
            }
            {
                FileFormat fileFormat = new FileFormat();
                fileFormat.setName("GeoPackage");
                fileFormat.setAbbreviation("gpkg");
                fileFormat.setMimetype("application/geopackage+sqlite3");
                fileFormats.add(fileFormat);
            }
            {
                FileFormat fileFormat = new FileFormat();
                fileFormat.setName("Shapefile");
                fileFormat.setAbbreviation("shp");
                fileFormat.setMimetype("application/vnd.esri_shapefile");
                fileFormats.add(fileFormat);
            }        
            themePublication.setFileFormats(fileFormats);
            
            List<TableInfo> tablesInfo = new ArrayList<>();
            {
                var tableInfo = new TableInfo();
                tableInfo.setSqlName("zzzzz_fooo");
                tableInfo.setTitle("ZZZZZZ fooo");
                tableInfo.setShortDescription("Enthält die Fliessgewässer, unterteilt in kürzere Linien (Gewässerabschnitte) mit gleichen ökomorphologischen Eigenschaften.");

                var attributesInfo = new ArrayList<AttributeInfo>();
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("geometrie");
                    attributeInfo.setAlias("geometrie");
                    attributeInfo.setDatatype("LINESTRING");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("sohlenbreite");
                    attributeInfo.setAlias("Sohlenbreite [m]");
                    attributeInfo.setShortDescription("Mittlere Sohlenbreite [m].");
                    attributeInfo.setDatatype("Integer");
                    attributeInfo.setMandatory(false);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("uferbreitelinks");
                    attributeInfo.setAlias("rbreite links [m]");
                    attributeInfo.setShortDescription("MittMittlere Breite Uferbereich links [m].");
                    attributeInfo.setDatatype("Integer");
                    attributeInfo.setMandatory(false);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("minimaleruferbereich");
                    attributeInfo.setAlias("Uferbreite links [m]");
                    attributeInfo.setShortDescription("Mittlere Breite Uferbereich links [m].");
                    attributeInfo.setDatatype("Integer");
                    attributeInfo.setMandatory(false);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("minimaleruferbereich");
                    attributeInfo.setAlias("Minimaler Uferbereich [m]");
                    attributeInfo.setShortDescription("Minimaler Uferbereich");
                    attributeInfo.setDatatype("Integer");
                    attributeInfo.setMandatory(false);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("boeschungsfussverbaurechts_txt");
                    attributeInfo.setAlias("Verbauung Böschungsfuss rechts");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("ueberhvegetation_txt");
                    attributeInfo.setAlias("Überhängende Vegetation");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("beurteilungsuferbreiterechts_txt");
                    attributeInfo.setAlias("Beurteilung Uferbreite rechts");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("uferbreiterechts");
                    attributeInfo.setAlias("Uferbreite rechts [m]");
                    attributeInfo.setShortDescription("Mittlere Breite Uferbereich rechts [m].");
                    attributeInfo.setDatatype("Integer");
                    attributeInfo.setMandatory(false);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("raumbedarf");
                    attributeInfo.setAlias("Raumbedarf [m]");
                    attributeInfo.setShortDescription("Raumbedarf des Gewässers (Gewässerraum)");
                    attributeInfo.setDatatype("Integer");
                    attributeInfo.setMandatory(false);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("nutzungumlandlinks_txt");
                    attributeInfo.setAlias("Nutzung Umland links");
                    attributeInfo.setShortDescription("Mittlere Sohlenbreite [m].");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("boeschungsfussverbaulinks_txt");
                    attributeInfo.setAlias("Verbauung Böschungsfuss links");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("algenbewuchs_txt");
                    attributeInfo.setAlias("Algenbewuchs [m]");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("sohlenverbauung_txt");
                    attributeInfo.setAlias("Sohlenverbauung");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                tableInfo.setAttributesInfo(attributesInfo);
                tablesInfo.add(tableInfo);
            }
            
            {
                var tableInfo = new TableInfo();
                tableInfo.setSqlName("grundbuchkreise_grundbuchkreis");
                tableInfo.setTitle("Grundbuchkreis");
                tableInfo.setShortDescription("Grundbuchkreisaufteilung inkl. Anschrift etc. der einzelnen Kreise");
                
                var attributesInfo = new ArrayList<AttributeInfo>();
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("t_id");
                    attributeInfo.setDatatype("int8");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("aname");
                    attributeInfo.setAlias("Name");
                    attributeInfo.setShortDescription("Name des Grundbuches");
                    attributeInfo.setDatatype("text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("perimeter");
                    attributeInfo.setAlias("Perimeter");
                    attributeInfo.setShortDescription("Perimeter des Grundbuchkreises");
                    attributeInfo.setDatatype("POLYGON");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                tableInfo.setAttributesInfo(attributesInfo);
                tablesInfo.add(tableInfo);
            }
            
            {
                var tableInfo = new TableInfo();
                tableInfo.setSqlName("oekomorphologie");
                tableInfo.setTitle("Ökomorphologie");
                tableInfo.setShortDescription("Enthält die Fliessgewässer, unterteilt in kürzere Linien (Gewässerabschnitte) mit gleichen ökomorphologischen Eigenschaften.");

                var attributesInfo = new ArrayList<AttributeInfo>();
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("geometrie");
                    attributeInfo.setAlias("geometrie");
                    attributeInfo.setDatatype("LINESTRING");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("sohlenbreite");
                    attributeInfo.setAlias("Sohlenbreite [m]");
                    attributeInfo.setShortDescription("Mittlere Sohlenbreite [m].");
                    attributeInfo.setDatatype("Integer");
                    attributeInfo.setMandatory(false);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("uferbreitelinks");
                    attributeInfo.setAlias("rbreite links [m]");
                    attributeInfo.setShortDescription("MittMittlere Breite Uferbereich links [m].");
                    attributeInfo.setDatatype("Integer");
                    attributeInfo.setMandatory(false);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("minimaleruferbereich");
                    attributeInfo.setAlias("Uferbreite links [m]");
                    attributeInfo.setShortDescription("Mittlere Breite Uferbereich links [m].");
                    attributeInfo.setDatatype("Integer");
                    attributeInfo.setMandatory(false);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("minimaleruferbereich");
                    attributeInfo.setAlias("Minimaler Uferbereich [m]");
                    attributeInfo.setShortDescription("Minimaler Uferbereich");
                    attributeInfo.setDatatype("Integer");
                    attributeInfo.setMandatory(false);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("boeschungsfussverbaurechts_txt");
                    attributeInfo.setAlias("Verbauung Böschungsfuss rechts");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("ueberhvegetation_txt");
                    attributeInfo.setAlias("Überhängende Vegetation");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("beurteilungsuferbreiterechts_txt");
                    attributeInfo.setAlias("Beurteilung Uferbreite rechts");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("uferbreiterechts");
                    attributeInfo.setAlias("Uferbreite rechts [m]");
                    attributeInfo.setShortDescription("Mittlere Breite Uferbereich rechts [m].");
                    attributeInfo.setDatatype("Integer");
                    attributeInfo.setMandatory(false);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("raumbedarf");
                    attributeInfo.setAlias("Raumbedarf [m]");
                    attributeInfo.setShortDescription("Raumbedarf des Gewässers (Gewässerraum)");
                    attributeInfo.setDatatype("Integer");
                    attributeInfo.setMandatory(false);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("nutzungumlandlinks_txt");
                    attributeInfo.setAlias("Nutzung Umland links");
                    attributeInfo.setShortDescription("Mittlere Sohlenbreite [m].");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("boeschungsfussverbaulinks_txt");
                    attributeInfo.setAlias("Verbauung Böschungsfuss links");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("algenbewuchs_txt");
                    attributeInfo.setAlias("Algenbewuchs [m]");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("sohlenverbauung_txt");
                    attributeInfo.setAlias("Sohlenverbauung");
                    attributeInfo.setDatatype("Text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                tableInfo.setAttributesInfo(attributesInfo);
                tablesInfo.add(tableInfo);
            }
            
            themePublication.setTablesInfo(tablesInfo);
            
            List<Service> services = new ArrayList<>();
            {
                var service = new Service();
                try {
                    service.setEndpoint(new URI("https://geo.so.ch/api/wms"));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                service.setType(ServiceType.WMS);

                var l1 = new Layer();
                l1.setIdentifier("ch.so.awjf.forstreviere.forstkreis");
                l1.setTitle("Forstkreis (Ökomorphologie der Fliessgewässer)");

                var l2 = new Layer();
                l2.setIdentifier("ch.so.awjf.forstreviere.forstreviere");
                l2.setTitle("Forstreviere (Ökomorphologie der Fliessgewässer)");

                var layers = new ArrayList<Layer>();
                layers.add(l1);
                layers.add(l2);
                service.setLayers(layers);

                services.add(service);
            }
            {
                var service = new Service();
                try {
                    service.setEndpoint(new URI("https://geo.so.ch/map"));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                service.setType(ServiceType.WGC);

                var l1 = new Layer();
                l1.setIdentifier("ch.so.awjf.forstreviere.forstkreis");
                l1.setTitle("Forstkreis (Ökomorphologie der Fliessgewässer)");

                var l2 = new Layer();
                l2.setIdentifier("ch.so.awjf.forstreviere.forstreviere");
                l2.setTitle("Forstreviere (Ökomorphologie der Fliessgewässer)");

                var layers = new ArrayList<Layer>();
                layers.add(l1);
                layers.add(l2);
                service.setLayers(layers);

                services.add(service);
            }
            {
                var service = new Service();
                try {
                    service.setEndpoint(new URI("https://geo.so.ch/api/wms?SERVICE=WFS&REQUEST=GetCapabilities&VERSION=1.0.0"));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                service.setType(ServiceType.WFS);

                var l1 = new Layer();
                l1.setIdentifier("ch.so.awjf.forstreviere.forstkreis");
                l1.setTitle("Forstkreis");

                var l2 = new Layer();
                l2.setIdentifier("ch.so.awjf.forstreviere.forstreviere");
                l2.setTitle("Forstreviere");

                var layers = new ArrayList<Layer>();
                layers.add(l1);
                layers.add(l2);
                service.setLayers(layers);

                services.add(service);
            }
            {
                var service = new Service();
                try {
                    service.setEndpoint(new URI("https://geo.so.ch/api/data/v1/api"));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                service.setType(ServiceType.DATA);

                var l1 = new Layer();
                l1.setIdentifier("ch.so.awjf.forstreviere.forstkreis.data");
                l1.setTitle("Forstkreis");

                var l2 = new Layer();
                l2.setIdentifier("ch.so.awjf.forstreviere.forstreviere");
                l2.setTitle("Forstreviere");

                var l3 = new Layer();
                l3.setIdentifier("ch.so.awjf.forstreviere.fuubar");
                l3.setTitle("Forstreviere fuubar");

                var layers = new ArrayList<Layer>();
                layers.add(l1);
                layers.add(l2);
                layers.add(l3);
                service.setLayers(layers);

                services.add(service);
            }
            themePublication.setServices(services);
            
            var previewLayer = new Layer();
            previewLayer.setIdentifier("ch.so.awjf.forstreviere");
            themePublication.setWgcPreviewLayer(previewLayer);
            
            themePublications.put(themePublication.getIdentifier(), themePublication);
        }
        
        {
            var themePublication = new ThemePublication();
            themePublication.setIdentifier("ch.so.arp.richtplan");
            themePublication.setModel("SO_AGI_AV_GB_Administrative_Einteilungen_Publikation_20180822");
            themePublication.setLastPublishingDate(LocalDate.parse("2023-01-05"));
            themePublication.setTitle("Administrative Einteilungen der amtlichen Vermessung und des Grundbuchs");
            themePublication.setShortDescription("Lorem <b>ipsum</b> dolor sit amet, <blink>consetetur sadipscing</blink> elitr, <a href ='https://de.wikipedia.org/wiki/Rumours'>Warum nur...</a> sed diam nonumy eirmod tempor invidunt ut <acronym title='Founded in 2006'>Twitter</acronym> labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
            themePublication.setKeywordsList(Arrays.asList(new String[]{"AGI","Grundbuch","AS","AV","Amtliche Vermessung","Vermessung","Einteilung"}));
            
            Office owner = new Office();
            owner.setAgencyName("Amt für Umwelt");
            owner.setAbbreviation("AfU");
            owner.setDivision("Ich bin die Abteilung");
            try {
                owner.setOfficeAtWeb(new URI("https://afu.so.ch"));
                owner.setEmail(new URI("mailto:afu@bd.so.ch"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            owner.setPhone("032 627 12 34");
            themePublication.setOwner(owner);
    
            Office servicer = new Office();
            servicer.setAgencyName("Amt für Geoinformation");
            servicer.setAbbreviation("AGI");
            try {
                servicer.setOfficeAtWeb(new URI("https://agi.so.ch"));
                servicer.setEmail(new URI("mailto:agi@bd.so.ch"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            servicer.setPhone("032 627 75 96");
            themePublication.setServicer(servicer);
            
            try {
                themePublication.setFurtherInformation(new URI("http://google.ch/oder/wikipedia"));
                themePublication.setLicence(new URI("https://www.gl.ch/public/upload/assets/5053/ktgl-ogd-geo-20180601.pdf?fp=1"));
                themePublication.setDownloadHostUrl(new URI("https://files-t.geo.so.ch"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            
            List<FileFormat> fileFormats = new ArrayList<>();
            {
                FileFormat fileFormat = new FileFormat();
                fileFormat.setName("INTERLIS");
                fileFormat.setAbbreviation("ili");
                fileFormat.setMimetype("application/xml");
                fileFormats.add(fileFormat);
            }
            {
                FileFormat fileFormat = new FileFormat();
                fileFormat.setName("GeoPackage");
                fileFormat.setAbbreviation("gpkg");
                fileFormat.setMimetype("application/geopackage+sqlite3 ");
                fileFormats.add(fileFormat);
            }        
            themePublication.setFileFormats(fileFormats);
            
            List<TableInfo> tablesInfo = new ArrayList<>();
            {
                var tableInfo = new TableInfo();
                tableInfo.setSqlName("grundbuchkreise_grundbuchkreis");
                tableInfo.setShortDescription("Grundbuchkreisaufteilung inkl. Anschrift etc. der einzelnen Kreise");
                
                List<AttributeInfo> attributesInfo = new ArrayList<>();
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("t_id");
                    attributeInfo.setDatatype("int8");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("aname");
                    attributeInfo.setShortDescription("Name des Grundbuches");
                    attributeInfo.setDatatype("text");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                {
                    var attributeInfo = new AttributeInfo();
                    attributeInfo.setName("perimeter");
                    attributeInfo.setShortDescription("Perimeter des Grundbuchkreises");
                    attributeInfo.setDatatype("polygon");
                    attributeInfo.setMandatory(true);
                    attributesInfo.add(attributeInfo);
                }
                tableInfo.setAttributesInfo(attributesInfo);
                tablesInfo.add(tableInfo);
            }
            themePublication.setTablesInfo(tablesInfo);
            
            List<Service> services = new ArrayList<>();
            {
                var service = new Service();
                try {
                    service.setEndpoint(new URI("https://geo.so.ch/api/wms?SERVICE=WMS&REQUEST=GetCapabilities&VERSION=1.3.0"));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                service.setType(ServiceType.WMS);

                var l1 = new Layer();
                l1.setIdentifier("ch.so.awjf.forstreviere.forstkreis");
                l1.setTitle("Forstkreis");

                var l2 = new Layer();
                l2.setIdentifier("ch.so.awjf.forstreviere.forstreviere");
                l2.setTitle("Forstreviere");

                var layers = new ArrayList<Layer>();
                layers.add(l1);
                layers.add(l2);
                service.setLayers(layers);

                services.add(service);
            }
            themePublication.setServices(services);
            themePublications.put(themePublication.getIdentifier(), themePublication);
        }
        return themePublications;
    }
}
