package ch.so.agi.metabean2file;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.so.agi.metabean2file.model.Dataset;

public class Utils {
    public static List<Dataset> getDatasets() {
        var formatter = new SimpleDateFormat("yyyy-MM-dd");      

        var datasets = new ArrayList<Dataset>();
        var dataset = new Dataset();
        dataset.setId("60D2441A-A358-4F3E-A94A-369AE3B2F8A7");
        dataset.setModel("SO_AGI_AV_GB_Administrative_Einteilungen_Publikation_20180822");
        try {
            dataset.setLastEditingDate(formatter.parse("2022-04-05"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dataset.setTitle("Administrative Einteilungen der amtlichen Vermessung und des Grundbuchs");
        dataset.setShortDescription("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
        dataset.setKeywords("AGI,Grundbuch,AS,AV,Amtliche Vermessung,Vermessung,Einteilung");
        try {
            dataset.setFurtherInformation(new URI("http://google.ch"));
            dataset.setFurtherMetadata(new URI("http://geocat.ch"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        datasets.add(dataset);
        
        return datasets;
    }
}
