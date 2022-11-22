package agh.ics.oop;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    void praseTest0() {
        String[] move =new String[]{"f","right","r","b","b","left","l","f","b","b"};
        MoveDirection[] tabMove =OptionsParser.parse(move);
        MoveDirection[] passMoves=new MoveDirection[]{MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.RIGHT,MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.LEFT,MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.BACKWARD};
        assertArrayEquals(tabMove,passMoves);
    }

    @Test
    void praseTest1() {//sprawdzam czy wyłapuje błąd
        String[] move =new String[]{"f","right","r","b","b","left","l","f","backword","xz", "legal","b","block","b"};
        int i = 1;
        try{
            OptionsParser.parse(move);
        }catch (IllegalArgumentException ex){
            i = 0;
        }
        assertEquals(0, i);
    }

    @Test
    void praseTest2() {//sprawdzam czy bez błedu tablica działa i nie wyrzuca error
        String[] move =new String[]{"f","right","r","b","b","left","l","f","b","b"};
        int i = 1;
        try{
            OptionsParser.parse(move);
        }catch (IllegalArgumentException ex){
            i = 0;
        }
        assertTrue(i == 1);
    }

}
