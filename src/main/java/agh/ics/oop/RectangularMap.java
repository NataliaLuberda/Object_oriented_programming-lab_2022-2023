package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;


public class RectangularMap extends AbstractWorldMap implements IWorldMap {


    public RectangularMap(int width, int height) {
        this.animals = new ArrayList<Animal>();
        this.downLeft = new Vector2d(0, 0);
        this.uppRight = new Vector2d(width - 1, height - 1);
    }
    public RectangularMap() {
        this.animals = new ArrayList<Animal>();
        this.downLeft = new Vector2d(0, 0);
        this.uppRight= new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(this.downLeft) && position.precedes(this.uppRight) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())) {
            this.animals.add(animal);
            return true;
        }
        return false;
    }
}

