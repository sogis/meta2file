package ch.so.agi.metabean2file;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.time.LocalDate;

import ch.so.agi.metabean2file.model.AttributeInfo;
import ch.so.agi.metabean2file.model.Dataset;
import ch.so.agi.metabean2file.model.FileFormat;
import ch.so.agi.metabean2file.model.Office;
import ch.so.agi.metabean2file.model.Service;
import ch.so.agi.metabean2file.model.TableInfo;

public class TestUtils {
    public static List<Dataset> getDatasets() {
        var df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        var datasets = new ArrayList<Dataset>();
        var dataset = new Dataset();
        dataset.setId("60D2441A-A358-4F3E-A94A-369AE3B2F8A7");
        dataset.setModel("SO_AGI_AV_GB_Administrative_Einteilungen_Publikation_20180822");
        dataset.setLastEditingDate(LocalDate.parse("2022-04-05"));
        dataset.setTitle("Administrative Einteilungen der amtlichen Vermessung und des Grundbuchs");
        dataset.setShortDescription("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
        dataset.setKeywords("AGI,Grundbuch,AS,AV,Amtliche Vermessung,Vermessung,Einteilung");
        
        Office owner = new Office();
        owner.setName("Amt für Umwelt");
        owner.setAbbrevation("AfU");
        owner.setDivision("Ich bin die Abteilung");
        try {
            owner.setOfficeAtWeb(new URI("https://afu.so.ch"));
            owner.setEmail(new URI("mailto://afu@bd.so.ch"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        owner.setPhone("032 627 12 34");
        owner.setStreet("Werkhofstrasse");
        owner.setNumber("5");
        owner.setPostalCode("4509");
        owner.setCity("Solothurn");
        dataset.setOwner(owner);

        Office servicer = new Office();
        servicer.setName("Amt für Geoinformation");
        servicer.setAbbrevation("AGI");
        try {
            servicer.setOfficeAtWeb(new URI("https://agi.so.ch"));
            servicer.setEmail(new URI("mailto://agi@bd.so.ch"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        servicer.setPhone("032 627 75 96");
        servicer.setStreet("Rötistrasse");
        servicer.setNumber("4");
        servicer.setPostalCode("4509");
        servicer.setCity("Solothurn");
        dataset.setServicer(servicer);
        
        try {
            dataset.setFurtherInformation(new URI("http://google.ch"));
            dataset.setFurtherMetadata(new URI("http://geocat.ch"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        List<FileFormat> fileFormats = new ArrayList<>();
        {
            FileFormat fileFormat = new FileFormat();
            fileFormat.setName("INTERLIS");
            fileFormat.setAbbrevation("ili");
            fileFormat.setMimetype("application/xml");
            fileFormats.add(fileFormat);
        }
        {
            FileFormat fileFormat = new FileFormat();
            fileFormat.setName("GeoPackage");
            fileFormat.setAbbrevation("gpkg");
            fileFormat.setMimetype("application/geopackage+sqlite3 ");
            fileFormats.add(fileFormat);
        }        
        dataset.setFileFormats(fileFormats);
        
        List<TableInfo> tablesInfo = new ArrayList<>();
        {
            var tableInfo = new TableInfo();
            tableInfo.setName("grundbuchkreise_grundbuchkreis");
            tableInfo.setDescription("Grundbuchkreisaufteilung inkl. Anschrift etc. der einzelnen Kreise");
            
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
                attributeInfo.setDatatype("text");
                attributeInfo.setMandatory(true);
                attributesInfo.add(attributeInfo);
            }
            {
                var attributeInfo = new AttributeInfo();
                attributeInfo.setName("perimeter");
                attributeInfo.setDatatype("polygon");
                attributeInfo.setMandatory(true);
                attributesInfo.add(attributeInfo);
            }
            tableInfo.setAttributesInfo(attributesInfo);
            tablesInfo.add(tableInfo);
        }
        dataset.setTablesInfo(tablesInfo);
        
        List<Service> services = new ArrayList<>();
        {
            var service = new Service();
            try {
                service.setEndpoint(new URI("https://geo.so.ch/wms"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            service.setType("WMS");
            var layers = new ArrayList<String>();
            layers.add("ch.so.awjf.forstreviere.forstkreis");
            layers.add("ch.so.awjf.forstreviere.forstreviere");
            service.setLayerNames(layers);
            services.add(service);
        }
        dataset.setServices(services);
        
        datasets.add(dataset);
        
        return datasets;
    }
}
