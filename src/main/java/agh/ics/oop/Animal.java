package agh.ics.oop;

public class Animal {

    private IWorldMap map;
    private MapDirection orientation;
    private Vector2d position;
    public Animal (IWorldMap map){
        this.map = map;
        this.orientation = MapDirection.NORTH;
    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
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
                    this.position = currentPosition;
                }
            }
            case BACKWARD -> {
                Vector2d currentPosition = this.position.subtract(this.orientation.toUnitVector());
                if (this.map.canMoveTo(currentPosition)) {
                    this.position = currentPosition;
                }
            }
        };
    }
}
