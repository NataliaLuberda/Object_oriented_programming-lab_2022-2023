package agh.ics.oop;

public class Grass implements IMapElement {

    private final Vector2d positione;

    public Grass(Vector2d positione) {
        this.positione = positione;
    }

    public Vector2d getPosition(){
        return this.positione;
    }

    @Override
    public String toString(){
        return "*";
    }
}
