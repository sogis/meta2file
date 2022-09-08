package ch.so.agi.meta2file.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ch.so.agi.meta2file.test.InputType.*;
import static ch.so.agi.meta2file.test.OutputType.*;

public class ValueOccurence {

    private static List<ValueOccurence> all;

    static {
        ValueOccurence[] arr = new ValueOccurence[]{
                create("$themePub.identifier$",
                        new InputType[]{VEC_ALL, VEC_MANDATORY, OTHER_ALL, OTHER_MANDATORY},
                        new OutputType[]{APP, GEOCAT}),
                create("$themePub.title$",
                        new InputType[]{VEC_ALL, VEC_MANDATORY, OTHER_ALL, OTHER_MANDATORY},
                        new OutputType[]{APP, SHEET, GEOCAT}),
                create("$themePub.iliModelName$",
                        new InputType[]{VEC_ALL, VEC_MANDATORY},
                        new OutputType[]{APP, SHEET}),
                create("$themePub.shortDescription$",
                        new InputType[]{VEC_ALL, VEC_MANDATORY, OTHER_ALL, OTHER_MANDATORY},
                        new OutputType[]{APP, SHEET, GEOCAT}),
                create("geo.so.ch/map?l=$themePub.identifier$",
                        new InputType[]{VEC_ALL, OTHER_ALL},
                        new OutputType[]{APP, GEOCAT}),


                create("$owner.agency$",
                        new InputType[]{VEC_MANDATORY, VEC_ALL, OTHER_ALL, OTHER_MANDATORY},
                        new OutputType[]{APP, SHEET, GEOCAT}),
                create("$owner.division$",
                        new InputType[]{VEC_ALL, OTHER_ALL},
                        new OutputType[]{APP, SHEET, GEOCAT}),

                create("$service.wmsLayer1$",
                        new InputType[]{VEC_ALL, OTHER_ALL},
                        new OutputType[]{APP, SHEET}),
                create("$service.wmsLayer2$",
                        new InputType[]{VEC_ALL, OTHER_ALL},
                        new OutputType[]{APP, SHEET}),

                create("$table1.title$",
                        new InputType[]{VEC_MANDATORY, VEC_ALL},
                        new OutputType[]{APP}),
                create("$table1.sqlName$",
                        new InputType[]{VEC_MANDATORY, VEC_ALL},
                        new OutputType[]{APP, SHEET}),
                create("$table1.attr1.name$",
                        new InputType[]{VEC_MANDATORY, VEC_ALL},
                        new OutputType[]{APP, SHEET}),
                create("$table1.attr1.alias$",
                        new InputType[]{VEC_ALL},
                        new OutputType[]{APP}),
                create("$table1.attr2.name$",
                        new InputType[]{VEC_ALL},
                        new OutputType[]{APP, SHEET}),
                create("$table1.attr2.alias$",
                        new InputType[]{VEC_ALL},
                        new OutputType[]{APP}),

                create("$table2.title$",
                        new InputType[]{VEC_ALL},
                        new OutputType[]{APP}),
                create("$table2.sqlName$",
                        new InputType[]{VEC_ALL},
                        new OutputType[]{APP, SHEET}),
                create("$table2.attr1.name$",
                        new InputType[]{VEC_ALL},
                        new OutputType[]{APP, SHEET}),
                create("$table2.attr1.alias$",
                        new InputType[]{VEC_ALL},
                        new OutputType[]{APP}),
                create("$table2.attr2.name$",
                        new InputType[]{VEC_ALL},
                        new OutputType[]{APP, SHEET}),
                create("$table2.attr2.alias$",
                        new InputType[]{VEC_ALL},
                        new OutputType[]{APP}),

                create("$key1$",
                        new InputType[]{VEC_ALL, OTHER_ALL},
                        new OutputType[]{APP, GEOCAT}),
                create("$key2$",
                        new InputType[]{VEC_ALL, OTHER_ALL},
                        new OutputType[]{APP, GEOCAT}),
                create("$syno1$",
                        new InputType[]{VEC_ALL, OTHER_ALL},
                        new OutputType[]{APP, GEOCAT}),
                create("$syno2$",
                        new InputType[]{VEC_ALL, OTHER_ALL},
                        new OutputType[]{APP, GEOCAT}),

                create("$fileFormat1$",
                        new InputType[]{VEC_MANDATORY, VEC_ALL, OTHER_ALL, OTHER_MANDATORY},
                        new OutputType[]{APP, GEOCAT}),
                create("$fileFormat2$",
                        new InputType[]{VEC_ALL},
                        new OutputType[]{APP, GEOCAT}),

                create("$item1.identifier$",
                        new InputType[]{VEC_MANDATORY, VEC_ALL, OTHER_ALL, OTHER_MANDATORY},
                        new OutputType[]{APP}),
                create("$item1.title$",
                        new InputType[]{VEC_MANDATORY, VEC_ALL, OTHER_ALL, OTHER_MANDATORY},
                        new OutputType[]{APP}),

                create("$item2.identifier$",
                        new InputType[]{VEC_ALL, OTHER_ALL},
                        new OutputType[]{APP}),
                create("$item2.title$",
                        new InputType[]{VEC_ALL, OTHER_ALL},
                        new OutputType[]{APP})
        };

        all = Arrays.asList(arr);
    }

    private String value;

    private OutputType[] outputFlags; //Array of Outtype-Keys for all outputs that must contain the value
    private InputType[] inputFlags; //Array of FileKey-Keys for all inputs that contain the value

    private boolean mandatory;
    private boolean inApp;
    private boolean inDescription;
    private boolean inGeocat;

    private ValueOccurence(String value, InputType[] inputFlags, OutputType[] outputFlags){
        this.value = value;
        this.inputFlags = inputFlags;
        this.outputFlags = outputFlags;
    }

    private static ValueOccurence create(String value, InputType[] inputFlags, OutputType[] outputFlags){
        return new ValueOccurence(value, inputFlags, outputFlags);
    }

    private boolean isFlaggedForInput(InputType input){
        boolean res = false;

        for(InputType flag : inputFlags){
            if(flag == input){
                res = true;
                break;
            }
        }
        return res;
    }

    private boolean isFlaggedForOutput(OutputType output){
        boolean res = false;

        for(OutputType flag : outputFlags){
            if(flag == output){
                res = true;
                break;
            }
        }
        return res;
    }

    private String getValue(){
        return value;
    }

    public static List<String> keysForTest(InputType inType, OutputType outType){
        List<String> keys = all.stream()
                .filter(val -> val.isFlaggedForInput(inType))
                .filter(val -> val.isFlaggedForOutput(outType))
                .map(ValueOccurence::getValue)
                .collect(Collectors.toList());

        return keys;
    }
}
