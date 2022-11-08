package agh.ics.oop;

import java.util.ArrayList;


public class RectangularMap implements IWorldMap {
    private final Vector2d upCorner; //górny lewy róg
    private final Vector2d downCorner; //dolny prawy róg
    private final MapVisualiser mapVisualiser;

    private final ArrayList<Animal> animalList;

    public RectangularMap(int width, int height) {
        this.upCorner = new Vector2d(0, 0);
        this.downCorner = new Vector2d(width-1, height-1);
        this.animalList = new ArrayList<Animal>();
        this.mapVisualiser = new MapVisualiser(this);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(upCorner) && position.precedes(downCorner) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())) {
            this.animalList.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (this.animalList.size() != 0){
            return this.objectAt(position) != null;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animalList) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return this.mapVisualiser.draw(this.upCorner,this.downCorner);
    }
}

