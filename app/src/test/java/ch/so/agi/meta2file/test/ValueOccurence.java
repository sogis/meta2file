package ch.so.agi.meta2file.test;

import ch.so.agi.meta2file.model.ThemePublication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ValueOccurence {

    private static List<ValueOccurence> all;

    static {
        ValueOccurence[] arr = new ValueOccurence[]{
                mandatory("$themePub.identifier$", true, false, true),
                mandatory("$themePub.title$", true, true, true),
                mandatory("$themePub.iliModelName$", true, true, true),
                mandatory("$themePub.shortDescription$", true, true, true),

                optional("$owner.division$", true, true, true),
                mandatory("$owner.agency$", true, true, true),

                optional("$service.wmsLayer1$", true, true, true),
                optional("$service.wmsLayer2$", true, true, true),

                mandatory("$table1.title$", true, false, true),
                mandatory("$table1.sqlName$", true, true, true),
                mandatory("$table1.attr1.name$", true, true, true),
                optional("$table1.attr1.alias$", true, false, true),
                mandatory("$table1.attr2.name$", true, true, true),
                optional("$table1.attr2.alias$", true, false, true),
                mandatory("$table1.shortDescription$", true, true, true),

                mandatory("$table2.title$", true, false, true),
                mandatory("$table2.sqlName$", true, true, true),
                mandatory("$table2.attr1.name$", true, true, true),
                optional("$table2.attr1.alias$", true, false, true),
                mandatory("$table2.attr2.name$", true, true, true),
                optional("$table2.attr2.alias$", true, false, true),
                mandatory("$table2.shortDescription$", true, true, true),

                optional("$key1$", true, false, true),
                optional("$key2$", true, false, true),
                optional("$syno1$", true, false, true),
                optional("$syno2$", true, false, true),

                mandatory("$fileFormat1$", true, false, true),
                mandatory("$fileFormat2$", true, false, true),

                mandatory("$item.identifier$", true, false, false),
                mandatory("$item.title$", true, false, false),
        };

        all = Arrays.asList(arr);
    }

    private String value;
    private boolean mandatory;
    private boolean inApp;
    private boolean inDescription;
    private boolean inGeocat;

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getValue() {
        return value;
    }

    public boolean isInApp() {
        return inApp;
    }

    public boolean isInDescription() {
        return inDescription;
    }

    public boolean isInGeocat() {
        return inGeocat;
    }

    public static void setAll(List<ValueOccurence> all) {
        ValueOccurence.all = all;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setInApp(boolean inApp) {
        this.inApp = inApp;
    }

    public void setInDescription(boolean inDescription) {
        this.inDescription = inDescription;
    }

    public void setInGeocat(boolean inGeocat) {
        this.inGeocat = inGeocat;
    }

    private static ValueOccurence optional(String value, boolean inApp, boolean inDescription, boolean inGeocat){
        ValueOccurence res = new ValueOccurence();

        res.setMandatory(false);

        res.setValue(value);
        res.setInApp(inApp);
        res.setInDescription(inDescription);
        res.setInGeocat(inGeocat);

        return res;
    }

    private static ValueOccurence mandatory(String value, boolean inApp, boolean inDescription, boolean inGeocat){
        ValueOccurence res = new ValueOccurence();

        res.setMandatory(true);

        res.setValue(value);
        res.setInApp(inApp);
        res.setInDescription(inDescription);
        res.setInGeocat(inGeocat);

        return res;
    }

    public static List<String> descriptionMandatoryKeys(){
        List<String> keys = all.stream()
                .filter(val -> val.isInDescription())
                .filter(val -> val.isMandatory())
                .map(ValueOccurence::getValue)
                .collect(Collectors.toList());

        return keys;
    }

    public static List<String> descriptionAllKeys(){
        List<String> keys = all.stream()
                .filter(val -> val.isInDescription())
                .map(ValueOccurence::getValue)
                .collect(Collectors.toList());

        return keys;
    }

    public static List<String> appAllKeys(){
        List<String> keys = all.stream()
                .filter(val -> val.isInApp())
                .map(ValueOccurence::getValue)
                .collect(Collectors.toList());

        return keys;
    }
}
