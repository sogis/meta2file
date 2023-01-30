package ch.so.agi.meta2file.libmain;

public enum Environment {
    PROD("p", 0),
    INT("i", 1),
    TEST("t", 2);

    private String envChar;
    private int propertyIdx;

    private Environment(String envChar, int propertyIdx){
        this.envChar = envChar;
        this.propertyIdx = propertyIdx;
    }

    public static Environment forIdentifier(String identifier){
        if(identifier == null || identifier.length() == 0)
            throw new IllegalArgumentException("identifier must have length > 0");

        Environment res = null;

        String lower = identifier.toLowerCase();
        if(lower.startsWith(PROD.envChar))
            res = PROD;
        else if(lower.startsWith(INT.envChar))
            res = INT;
        else if(lower.startsWith(TEST.envChar))
            res = TEST;
        else
            throw new IllegalArgumentException("Expecting environment value to start with p,i,t. Given value: " + identifier);

        return res;
    }

    public int getPropertyIdx(){ return propertyIdx; }
}
