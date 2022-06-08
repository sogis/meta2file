package ch.so.agi.metabean2file.model;

import javax.validation.constraints.NotNull;

public class BoundingBox {
    @NotNull
    private double left = 2592550; //7.34;
    @NotNull
    private double bottom = 1213700; // 47.074;
    @NotNull
    private double right = 2644770; // 8.032;
    @NotNull
    private double top = 1261340; // 47.503;
    
    public double getLeft() {
        return left;
    }
    public void setLeft(double left) {
        this.left = left;
    }
    public double getBottom() {
        return bottom;
    }
    public void setBottom(double bottom) {
        this.bottom = bottom;
    }
    public double getRight() {
        return right;
    }
    public void setRight(double right) {
        this.right = right;
    }
    public double getTop() {
        return top;
    }
    public void setTop(double top) {
        this.top = top;
    }
}
