package agh.ics.oop;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OptionsParserTest {
    @Test
    void praseTest() {
        String[] move =new String[]{"f","right","r","b","b","left","l","f","backword","xz", "legal","b","block","b"};
        MoveDirection[] tabMove =OptionsParser.parse(move);
        MoveDirection[] passMoves=new MoveDirection[]{MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.RIGHT,MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.LEFT,MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.BACKWARD};
        assertArrayEquals(tabMove,passMoves);
    }
}


