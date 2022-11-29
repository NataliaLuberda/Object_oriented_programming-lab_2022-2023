package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap {

    protected Map<Vector2d, IMapElement> animals = new HashMap<>();
    private final MapVisualiser map= new MapVisualiser(this);

    public abstract Vector2d getDownLeft();
    public abstract Vector2d getUppRight();

    public Map<Vector2d, IMapElement> getAnimalsAndGrass() {
        return animals;
    }

    public abstract HashMap<Vector2d, IMapElement>  getGrass();

    public abstract boolean canMoveTo(Vector2d position);

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

