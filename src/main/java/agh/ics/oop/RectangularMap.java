package agh.ics.oop;

import java.util.HashMap;


public class RectangularMap extends AbstractWorldMap implements IWorldMap,IPositionChangeObserver {

    private final Vector2d downLeft;
    private final Vector2d uppRight;

    public RectangularMap(int width, int height) {
        this.downLeft = new Vector2d(0, 0);
        this.uppRight = new Vector2d(width - 1, height - 1);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (this.canMoveTo(newPosition)){
            IMapElement puszek = animals.get(oldPosition);
            this.animals.remove(oldPosition);
            this.animals.put(newPosition,puszek);
        }

    }
    @Override
    public Vector2d getDownLeft() {
        return downLeft;
    }

    @Override
    public Vector2d getUppRight() {
        return uppRight;
    }

    @Override
    public HashMap<Vector2d, IMapElement> getGrass() {
        return null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(this.downLeft) && position.precedes(this.uppRight) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())) {
            this.animals.put(animal.getPosition(),animal);
            animal.addObserver(this);
            return true;
        }else {
            throw new IllegalArgumentException(animal.getPosition() + " is not available position");
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.animals.get(position);
    }
}