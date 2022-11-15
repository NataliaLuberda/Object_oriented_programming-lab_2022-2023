package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {
    protected List<Animal> animals = new ArrayList<>();
    protected List<Grass> grassSampleList = new ArrayList<>();

    protected Vector2d downLeft;

    protected Vector2d uppRight;
    abstract public boolean canMoveTo(Vector2d position);
    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) {
                return true;
            }
        }
        for (Grass grass : grassSampleList) {
            if (grass.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public void updateBounds()//podaje granicę mojej  mapy
    {
        if (this.animals.size() != 0)
            for (Animal animal : this.animals)
            {
                this.downLeft= this.downLeft.lowerLeft(animal.getPosition());
                this.uppRight = this.uppRight.upperRight(animal.getPosition());
            }

        if (this.grassSampleList.size() != 0)
            for(Grass grass: this.grassSampleList)
            {
                this.downLeft = this.downLeft.lowerLeft(grass.getPosition());
                this.uppRight = this.uppRight.upperRight(grass.getPosition());
            }
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        for (Grass grass : grassSampleList) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualiser map = new MapVisualiser(this);
        updateBounds();
        return map.draw(downLeft, uppRight);
    }
}

