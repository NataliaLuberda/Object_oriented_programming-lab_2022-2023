package agh.ics.oop;

public class OptionsParser{
    public static MoveDirection[] parse(String[] args) throws IllegalArgumentException{

        int size = args.length;
        int idx = 0;
        MoveDirection[] directions = new MoveDirection[size];

        for (String move : args){
            MoveDirection ans = switch (move){
                case "forward", "f" -> MoveDirection.FORWARD;
                case "backward", "b" -> MoveDirection.BACKWARD;
                case "left", "l" -> MoveDirection.LEFT;
                case "right", "r" -> MoveDirection.RIGHT;
                default -> throw new IllegalArgumentException(move + " is not legal move specification");
            };
            directions[idx] = ans;
            idx++;
        }

        return directions;
    }
}
