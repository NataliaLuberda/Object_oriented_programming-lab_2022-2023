package agh.ics.oop;

import java.util.*;

public class Animal implements IMapElement{

    private IWorldMap map;
    private MapDirection orientation;
    private Vector2d position;

    private final List<IPositionChangeObserver> observerList;

    public Animal (IWorldMap map){
        this(map,new Vector2d(2,2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
        this.observerList = new ArrayList<>();
        this.orientation = MapDirection.NORTH;
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    @Override
    public String toString(){
        return switch (this.orientation){
            case NORTH -> "N";
            case SOUTH -> "S";
            case WEST -> "W";
            case EAST -> "E";
        };
    }

    public MapDirection getOrientation() {
        return orientation;
    }
    public Vector2d getPosition(){ return this.position;}
    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                Vector2d currentPosition = this.position.add(this.orientation.toUnitVector());
                if (this.map.canMoveTo(currentPosition)) {
                    this.positionChanged(this.position,currentPosition);
                    this.position = currentPosition;
                }
            }
            case BACKWARD -> {
                Vector2d currentPosition = this.position.subtract(this.orientation.toUnitVector());
                if (this.map.canMoveTo(currentPosition)) {
                    this.positionChanged(this.position,currentPosition);
                    this.position = currentPosition;
                }
            }
        };
    }

    void addObserver(IPositionChangeObserver observer){
        this.observerList.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        this.observerList.remove(observer);
    }
    void positionChanged(Vector2d oldPosition,Vector2d newPosition) {
        for (IPositionChangeObserver observer : this.observerList) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
