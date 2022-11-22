package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected abstract Vector2d getDownLeft();
    protected abstract Vector2d getUppRight();
    private final MapVisualiser map= new MapVisualiser(this);

    abstract public boolean canMoveTo(Vector2d position);

    @Override
    public boolean isOccupied(Vector2d position)
    {
        return this.objectAt(position) != null;
    }

    @Override
    public String toString()
    {
        return map.draw(this.getDownLeft(),this.getUppRight());
    }
}

