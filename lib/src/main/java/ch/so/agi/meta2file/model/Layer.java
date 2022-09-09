package ch.so.agi.meta2file.model;

import javax.validation.constraints.NotNull;

public class Layer {
    /**
     * Identifier des Layers, z.B. "ch.so.awjf.forstreviere.forstkreis"
     */
    @NotNull
    private String identifier;
    
    /**
     * Prosanamen des Layers, z.B. "Wald - Forstkreis"
     */
    @NotNull
    private String title;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
