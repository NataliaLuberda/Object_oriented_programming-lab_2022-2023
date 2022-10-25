package agh.ics.oop;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal(){
        this.position = new Vector2d(2,2);
        this.orientation = MapDirection.NORTH;
    }
    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    @Override
    public String toString(){
        return (this.position.toString() + ","+ this.orientation) ;
    }
    public void changePositionPlus(MapDirection z){
        Vector2d currentPosition = this.position.add(this.orientation.toUnitVector());
        if (currentPosition.y <=4 && currentPosition.y>= 0 && currentPosition.x <=4 && currentPosition.x>=0 ) {
            this.position = currentPosition;
        }
    }
    public void changePositionMinus(MapDirection z){
        Vector2d currentPosition = this.position.subtract(this.orientation.toUnitVector());
        if (currentPosition.y <=4 && currentPosition.y>= 0 && currentPosition.x <=4 && currentPosition.x>=0 ) {
            this.position = currentPosition;
        }
    }
    public Vector2d getPosition(){ return this.position;}
    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> changePositionPlus(this.orientation);
            case BACKWARD -> changePositionMinus(this.orientation);
        };
    }
}
