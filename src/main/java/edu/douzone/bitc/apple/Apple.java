package edu.douzone.bitc.apple;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Apple {
    private int weight;
    private Color color;

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Apple{" +
            "weight=" + weight +
            ", color=" + color +
            '}';
    }
}
