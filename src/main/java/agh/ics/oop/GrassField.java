package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
public class GrassField extends AbstractWorldMap implements IWorldMap{
    final int grassSample;

    public GrassField(int grassSample){
        this.grassSample = grassSample;
        this.grassSampleList = new ArrayList<Grass>();
        this.animals = new ArrayList<Animal>();
        this.downLeft = new Vector2d(0, 0);
        this.uppRight = new Vector2d((int)Math.sqrt(grassSample*10), (int)Math.sqrt(grassSample*10));
        int currentGrass = 0;
        while (currentGrass < grassSample) {
            putGrass();
            currentGrass++;
        }
        updateBounds();
    }

    public void putGrass(){
        Random randomSamplePlace = new Random();
        Vector2d newPosition;
        do {
            int x = randomSamplePlace.nextInt((int) Math.sqrt(10 * grassSample));
            int y = randomSamplePlace.nextInt((int) Math.sqrt(10 * grassSample));
            newPosition = new Vector2d(x, y);
        } while (isOccupied(newPosition));
        this.grassSampleList.add(new Grass(newPosition));
    }
    @Override
    public boolean canMoveTo(Vector2d position){
        Object object = objectAt(position);
        return !(object instanceof Animal);
    }
    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())) {
            this.animals.add(animal);
            return true;
        } else {
            if (moveAnimalOnGrass(animal.getPosition())){
                this.animals.add(animal);
                return true;
            }
        }
        return false;
    }

    public boolean moveAnimalOnGrass(Vector2d position){
        Object ob = objectAt(position);
        if(ob instanceof Grass){
            putGrass();
            this.grassSampleList.remove(ob);
            return true;
        }
        return false;
    }


}
